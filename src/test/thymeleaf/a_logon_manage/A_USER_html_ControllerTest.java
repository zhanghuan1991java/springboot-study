
import com.alibaba.fastjson.JSON;
import com.didispace.Application;
import com.didispace.mybatis.a_user.A_USER;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@Transactional
@Rollback(true)
public class A_USER_html_ControllerTest {

    Logger logger = LoggerFactory.getLogger(A_USER_html_ControllerTest.class);

    @Autowired
    private MockMvc mvc;

    @Test
    public void testRegisterUser() throws Exception {
        A_USER user = new A_USER();
        user.setName("李斯");
        user.setOther_name("小斯");
        user.setAddr("秦国");
        user.setPhone("0755-123456");

        String  userStr = JSON.toJSONString(user);

        logger.info(userStr);

        String result = mvc.perform(MockMvcRequestBuilders
                .post("/registerUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userStr)).andReturn().getResponse().getContentAsString();
        logger.info("newUserByObject : "+result);
    }


    @Test
    public void testMain() throws Exception {
        String ret = mvc.perform(MockMvcRequestBuilders.get("/main")).andReturn().getResponse().getContentAsString();
        logger.info("测试访问main页面" +ret);
    }

    @Test
    public void testRegisterUser1() throws Exception {
        A_USER user = new A_USER();
        user.setName("今晚");
        user.setOther_name("小金");
        user.setAddr("秦国1");
        user.setPhone("0755-12345678");
        String userStr = JSON.toJSONString(user);
        String result = mvc.perform(MockMvcRequestBuilders
                .post("/registerUser1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userStr)).andReturn().getResponse().getContentAsString();
        logger.info("newUserByObject : "+result);
    }

    @Test
    public void testQueryUser() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.post("/queryUser"))
                .andReturn().getResponse().getContentAsString();
        logger.info("newUserByObject : "+result);
    }

}