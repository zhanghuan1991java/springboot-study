package mybatis.a_user_image;

import com.didispace.App;
import com.didispace.mybatis.a_user_image.A_USER_IMAGE;
import com.didispace.mybatis.a_user_image.A_USER_IMAGE_Mapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Transactional
@Rollback(false)
@Slf4j
public class A_USER_IMAGE_MapperTest {

    @Autowired
    private A_USER_IMAGE_Mapper mapper;

    @Test
    public void insertUserImage(){
        A_USER_IMAGE a = new A_USER_IMAGE();
        a.setUser_id("123456");
        a.setImage("Hello Image".getBytes());
        int ret = mapper.insertUserImage(a);
        Assert.assertEquals("新增一条数据",1,ret);
    }

    @Test
    public void selectUserImage(){
        Map map = mapper.selectUserImage("25dfd04c-a123-4bea-a482-a0654709793a");
        Assert.assertNotNull(map);
    }
}
