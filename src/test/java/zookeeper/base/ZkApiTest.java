package zookeeper.base;

import com.didispace.App;
import com.didispace.zookeeper.base.WatcherApi;
import com.didispace.zookeeper.base.ZkApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class ZkApiTest {

    @Autowired
    private ZkApi zk;

    @Test
    public void testExist(){
        Stat stat = zk.exists("/zk",false);
        log.info("stat : " + stat.toString());
        Assert.assertNotNull(stat);
    }

    @Test
    public void testExist_2(){
        Stat stat = zk.exists("/zk",null);
        log.info("stat : " + stat.toString());
        Assert.assertNotNull(stat);
    }

    @Test
    public void testExist_3(){
        Stat stat = zk.exists("/zk",new WatcherApi());
        log.info("stat : " + stat.toString());
        Assert.assertNotNull(stat);
    }

    @Test
    public void testCreateNode(){

        if(zk.exists("/zkTest",false) == null){

            boolean ret  = zk.createNode("/zkTest","Test java connect data");

            Assert.assertTrue("创建 /zkTest 成功",ret);
        }

    }

    @Test
    public void testUpdateNode(){
        boolean ret = zk.updateNode("/zkTest","update String by testUpdateNode");
        log.info("update status : " + ret);
        Assert.assertTrue("update data success",ret);
    }

    @Test
    public void testDeleteNode(){
        boolean ret = zk.deleteNode("/zkTest");
        log.info("testDeleteNode status : " + ret);
        Assert.assertTrue("delete node success",ret);
    }



}