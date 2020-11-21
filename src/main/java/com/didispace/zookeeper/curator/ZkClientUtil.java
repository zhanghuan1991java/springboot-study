package com.didispace.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

public class ZkClientUtil {

    private static final int BASE_SLEEP_TIME_MS = 5000; //定义失败重试间隔时间 单位:毫秒
    private static final int MAX_RETRIES = 3; //定义失败重试次数
    private static final int SESSION_TIME_OUT = 20000; //定义会话存活时间,根据业务灵活指定 单位:毫秒
    private static final String ZK_URI = "192.168.75.100:2181";//IP + 端口
    private static final String NAMESPACE = "jackZhanG";//根目录

    private ZkClientUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 通过curator建立与zk的连接
     *
     * @return
     */
    public static CuratorFramework build() {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME_MS, MAX_RETRIES);//重试策略

        return CuratorFrameworkFactory.builder()
                .connectString(ZK_URI)
                .retryPolicy(retryPolicy)
                .namespace(NAMESPACE)
                .sessionTimeoutMs(SESSION_TIME_OUT)
                .build();

    }

    /**
     * 新增节点
     */
    public static void insertNode(String path) throws Exception {
        CuratorFramework client = ZkClientUtil.build();
        client.start();

        client.create()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(path);

    }


}