package zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.Random;

@Slf4j
public class CuratorDistributedDoubleBarrier {

	static final String ZK_ADDR = "192.168.75.100:2181";
	
	static final int TIME_OUT = 5000;//ms 
	
	public static void main(String[] args) throws Exception {

		for(int i = 0; i < 5; i++){
			new Thread(() -> {
				try {
					RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

					CuratorFramework cf = CuratorFrameworkFactory.builder()
								.connectString(ZK_ADDR)
								.sessionTimeoutMs(TIME_OUT)
								.retryPolicy(retryPolicy)
								.namespace("curator")
								.build();
					cf.start();

					/**
					 *
					 * 双重屏障  DistributedDoubleBarrier
					 *
					 * 在开始和结束点，分别设置 栅栏Barrier ： 一起进入 ， 一起退出 。
					 *
					 * Usage
					 * =================================
					 * Creating a DistributedBarrier
					 * public DistributedDoubleBarrier(CuratorFramework client,
					 *                                 String barrierPath,
					 *                                 int memberQty)
					 * Creates the barrier abstraction. memberQty is the number of members in the barrier. When enter() is called, it blocks until
					 * all members have entered. When leave() is called, it blocks until all members have left.
					 *
					 * Parameters:
					 * client - the client
					 * barrierPath - path to use
					 * memberQty - the number of members in the barrier
					 * ====================================================
					 * General Usage
					 *
					 * To enter on the barrier:
					 * public void     enter();
					 *
					 * To leave on the barrier:
					 * public void     leave();
					 * =====================================================
					 * Error Handling
					 * DistributedDoubleBarrier instances watch for connection loss and will throw an exception from enter() and/or leave().
					 */
					DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(cf, "/barrier", 5);
					log.info(Thread.currentThread().getName() + " ready......");

					barrier.enter();

					log.info(Thread.currentThread().getName() + " is running......");
					Thread.sleep(1000 * (new Random()).nextInt(3));

					barrier.leave();
					log.info(Thread.currentThread().getName() + " leave...");

				} catch (Exception e) {
					log.info(Thread.currentThread().getName()+" Exception...",e);
				}
			},"t" + i).start();
		}

	}

}