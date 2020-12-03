package zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

@Slf4j
public class CuratorDistributedBarrier {

    static final String ZK_ADDR = "192.168.75.100:2181";

    static final int TIME_OUT = 5000;

    static DistributedBarrier barrier = null;

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

                    CuratorFramework cf = CuratorFrameworkFactory.builder()
                            .connectString(ZK_ADDR)
                            .sessionTimeoutMs(TIME_OUT)
                            .retryPolicy(retryPolicy)
                            .namespace("curator")
                            .build();

                    cf.start();
                    /**
                     * 此类 类似于 CyclicBarrier 对象，
                     *
                     * 栅栏Barrier 满足条件时，同时开始执行。
                     *
                     * http://curator.apache.org/curator-recipes/barrier.html
                     *
                     * Creating a DistributedBarrier
                     * ==========================
                     * public DistributedBarrier(CuratorFramework client,
                     *                           String barrierPath)
                     * Parameters:
                     * client - client
                     * barrierPath - path to use as the barrier
                     * =========================
                     * General Usage
                     *
                     * To wait on the barrier:
                     * public void waitOnBarrier()
                     *
                     * There are utilities for setting/removing the barrier:
                     * setBarrier();
                     * removeBarrier();
                     * =========================
                     * Error Handling
                     * DistributedBarrier instances watch for connection loss and will throw an exception from waitOnBarrier().
                     */
                    barrier = new DistributedBarrier(cf, "/barrier");
                    log.info(Thread.currentThread().getName() + " set barrier!");

                    //设置
                    barrier.setBarrier();

                    //等待
                    barrier.waitOnBarrier();

                    log.info(Thread.currentThread().getName() + " running......");
                } catch (Exception e) {
                    log.info(Thread.currentThread().getName() + " Exception...", e);
                }
            }, "t" + i).start();
        }

        Thread.sleep(5000);

        //释放
        barrier.removeBarrier();
    }
}