package zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

@Slf4j
public class CuratorAtomicInteger {

    static final String ZK_ADDR = "192.168.75.100:2181";

    static final int TIME_OUT = 5000;//ms

    public static void main(String[] args) throws Exception {

        //1 重试策略：间隔1秒，重试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        //2 通过工厂创建连接
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDR)
                .sessionTimeoutMs(TIME_OUT)
                .retryPolicy(retryPolicy)
                .namespace("curator")
                .build();

        //3 开启连接
        cf.start();

		/**
		 *4 使用DistributedAtomicInteger
		 * 分布式原子自增计数器
		 */
        DistributedAtomicInteger atomicIntger =
				new DistributedAtomicInteger(
						cf,
						"/atom",
						new RetryNTimes(3, 1000)
				);

        AtomicValue<Integer> value = atomicIntger.add(1);
        log.info("succeeded:" + value.succeeded()); //true
        log.info("postValue:" + value.postValue()); //1
        log.info("preValue:" + value.preValue());   //0

    }
}