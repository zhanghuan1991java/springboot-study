package zookeeper.base;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

@Slf4j
public class PersonalUseZKTest {

    static final String CONNECT_ADDR = "192.168.75.100:2181";

    static final int SESSION_OUTTIME = 2000;

    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    @Test
    public void testZK() throws Exception{
        ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, new Watcher(){
            @Override
            public void process(WatchedEvent event) {
                //获取事件的状态
                Watcher.Event.KeeperState keeperState = event.getState();
                Event.EventType eventType = event.getType();
                //如果是建立连接
                if(Event.KeeperState.SyncConnected == keeperState){
                    if(Event.EventType.None == eventType){
                        //如果建立连接成功，则发送信号量，让后续阻塞程序向下执行
                        connectedSemaphore.countDown();
                        log.info("zk 建立连接");
                    }
                }
            }
        });

        //进行阻塞
        connectedSemaphore.await();

        log.info("main thread running ... ");
        
        //创建父节点
		zk.create("/testRoot", "testRoot".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //创建子节点
		zk.create("/testRoot/children", "children data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //获取节点洗信息
        Stream.of(zk.getData("/testRoot", false, null))
                .map(t->{
                    return "获取节点信息————" + new String(t);
                })
                .forEach(log::info)
        ;

		zk.getChildren("/testRoot", false)
                .stream()
                .map(t->{
                    return "获取节点信息————" + String.valueOf(t);
                })
                .forEach(log::info)
        ;

        //修改节点的值
		zk.setData("/testRoot", ("change root Data:" + IdUtil.fastSimpleUUID()).getBytes(), -1);
		byte[] data = zk.getData("/testRoot", false, null);
		log.info("修改节点的值————"+new String(data));

        //判断节点是否存在 , 注意，这里返回的是对象
        Stat stat = zk.exists("/testRoot/children", false);
		log.info("判断节点是否存在————" + JSONUtil.parse(stat).toStringPretty());

        //删除子节点
		zk.delete("/testRoot/children", -1);
		log.info("删除子节点————" + zk.exists("/testRoot/children", false) + "");

		//删除父节点
        zk.delete("/testRoot",-1);
        log.info("删除父节点节点————" + zk.exists("/testRoot", false) + "");

        zk.close();
    }



}
