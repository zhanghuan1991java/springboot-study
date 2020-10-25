package zookeeper;

import com.didispace.App;
import com.didispace.zookeeper.WatcherApi;
import com.didispace.zookeeper.ZkApi;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class ZkApiTest {
    Logger logger = LoggerFactory.getLogger(ZkApiTest.class);

    @Autowired
    private  ZkApi zk;

    @Test
    public void testExist(){
        Stat stat = zk.exists("/zk",false);
        logger.info("stat : " + stat.toString());
        Assert.assertNotNull(stat);
    }

    @Test
    public void testExist_2(){
        Stat stat = zk.exists("/zk",null);
        logger.info("stat : " + stat.toString());
        Assert.assertNotNull(stat);
    }

    @Test
    public void testExist_3(){
        Stat stat = zk.exists("/zk",new WatcherApi());
        logger.info("stat : " + stat.toString());
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
        logger.info("update status : " + ret);
        Assert.assertTrue("update data success",ret);
    }

    @Test
    public void testDeleteNode(){
        boolean ret = zk.deleteNode("/zkTest");
        logger.info("testDeleteNode status : " + ret);
        Assert.assertTrue("delete node success",ret);
    }



}
