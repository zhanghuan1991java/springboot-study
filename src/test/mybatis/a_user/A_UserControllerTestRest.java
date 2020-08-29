import com.didispace.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class A_UserControllerTestRest {
    /**
     * 初始化的  RestTemplate  写在启动类 Application 中， 也可以用一个单独的配置类处理
     */
    @Autowired
    private RestTemplate  template;

    @Test
    public void findUserById() throws Exception {
        //此种方式需要 先启动项目才能测试
//        String user =template.getForObject("http://localhost:8080/findUser/202008081520000100000",String.class);
//
//        Assert.assertNotNull(user);
    }

}
