package zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CuratorBase {

    static final String CONNECT_URI = "192.168.75.100:2181";

    static final int TIME_OUT = 5000;

    public static void main(String[] args) throws Exception {

        //1 重试策略：重试间隔为1s 重试10次 , 查看RetryPolicy类，可选择不同的重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

        //2 通过工厂创建连接
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_URI)
                .sessionTimeoutMs(TIME_OUT)
                .retryPolicy(retryPolicy)
                .namespace("curator") //根目录
                .build();
        //3 开启连接
        cf.start();

        log.info(CuratorFrameworkState.STARTED + "");
        log.info(cf.getState() + "");

        /**
         * 4、创建永久自增节点 ；默认节点类型（永久节点 CreateMode.PERSISTENT）、路径、数据内容
         * 若设置为 ： CreateMode.PERSISTENT_SEQUENTIAL ，则会自动在节点后添加序号
         * creatingParentsIfNeeded()自动创建父节点
         */
        cf.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                .forPath("/c1/c2", "c1c2内容".getBytes())
        ;

        //判断指定节点是否存在
        Stat stat = cf.checkExists().forPath("/c1/c2");
        if (stat == null) {
            cf.create().creatingParentsIfNeeded().forPath("/c1/c2", "C2 Message".getBytes());
        }

        //5、创建永久节点
        createPersistentNode(cf, "/super/c1", "super,c1内容");
        createPersistentNode(cf, "/super/c2", "super,c2内容");

        //6、读取节点
        String ret1 = new String(cf.getData().forPath("/super/c2"));
        log.info("/super/c2的内容：{}", ret1);

        //7、修改节点
        cf.setData().forPath("/super/c2", "修改c2内容".getBytes());
        String ret2 = new String(cf.getData().forPath("/super/c2"));
        log.info("修改c2内容:{}", ret2);

        //8、绑定回调函数
        ExecutorService executor = Executors.newCachedThreadPool();
        cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework cf, CuratorEvent ce) throws Exception {
                        System.out.println("code:" + ce.getResultCode());
                        System.out.println("type:" + ce.getType());
                        System.out.println("线程为:" + Thread.currentThread().getName());
                    }
                }, executor)
                .forPath("/super/c3", "c3内容".getBytes());

		//9、读取子节点getChildren方法
		List<String> supList = cf.getChildren().forPath("/super");
		supList.stream().forEach(log::info);

		executor.shutdown();

		//10、清空nameSpace下的所有节点
//        clearNode(cf, "/");
    }

    /**
     * 创建永久节点
     * @param cf
     * @param path
     * @param content
     */
    private static void createPersistentNode(CuratorFramework cf, String path, String content) {
        try {
            if (cf.checkExists().forPath(path) != null) {
				log.info("节点[{}]已存在",path);
                return;
            }

            cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, content.getBytes());
        } catch (Exception e) {
            log.info("createPersistentNode失败", e);
        }
    }

    /**
     * 清空path下所有的节点
     * 若为 / ，则代表整个nameSpace
     *
     * @param cf
     * @throws Exception
     */
    private static void clearNode(CuratorFramework cf, String path) throws Exception {
        //1、获取 path 下的所有子节点
        List<String> c1 = cf.getChildren().forPath(path);

        //2、全部删除
        c1.stream().forEach(t -> {
            log.info("clear path :{}", t);
            try {
                cf.delete().deletingChildrenIfNeeded().forPath("/" + t);
            } catch (Exception e) {
                log.info("path not exist {}", t);
            }
        });
    }

}