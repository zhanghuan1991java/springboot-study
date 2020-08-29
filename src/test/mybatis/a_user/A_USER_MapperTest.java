import com.didispace.Application;
import com.didispace.mybatis.a_user.A_USER;
import com.didispace.mybatis.a_user.A_USER_Mapper;
import com.didispace.mybatis.pageInfo.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Rollback(true)
public class A_USER_MapperTest {

    @Autowired
    private A_USER_Mapper mapper;

    @Test
    public void selectById() {
        A_USER user = mapper.selectUserById("202008081520000100000");
        Assert.assertNotNull(user);
    }

    @Test
    public void selectByName() {
        A_USER user = mapper.selectUserByName("zh123sdf");
        Assert.assertNotNull(user);
    }

    @Test
    public void insertUser() {
        A_USER user = new A_USER();
        user.setName("李四");
        user.setOther_name("尼古拉斯");
        user.setPhone("13512341234");
        user.setAddr("中国深圳TestRollBack");
        user.setIdentity_code("420562198501231212");
        Integer ret = mapper.insertUser(user);
        Assert.assertEquals(1,1);
    }

    @Test
    public void getAllUser() {
        Page page = new Page();
        List<A_USER> list = mapper.getAllUser(page);
        Assert.assertNotNull(list);
    }

    @Test
    public void getCountNum() {
        Integer ret = mapper.getCountNum();
        System.out.println("user表总数据条数:"+ret);
        Assert.assertNotNull(ret);
    }

    @Test
    public void deleteById() {
        mapper.deleteUserById("202008081520000100020");

    }
}