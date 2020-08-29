
import com.alibaba.fastjson.JSON;
import com.didispace.Application;
import com.didispace.mybatis.a_user.A_USER;
import org.junit.Assert;
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
    public void testUserIndex() throws Exception {
        int ret = mvc.perform(MockMvcRequestBuilders.get("/userIndex")).andReturn().getResponse().getStatus();
        logger.info("/userIndex 访问状态:"+ret);
        Assert.assertEquals("访问页面状态",200,ret);
    }

    @Test
    public void testUserPage() throws Exception {
        int ret = mvc.perform(MockMvcRequestBuilders.get("/userPage")).andReturn().getResponse().getStatus();
        logger.info("/userPage 访问状态:"+ret);
        Assert.assertEquals("访问页面状态",200,ret);
    }

    @Test
    public void testUser_register_html() throws Exception {
        int ret = mvc.perform(MockMvcRequestBuilders.get("/user_register_html")).andReturn().getResponse().getStatus();
        logger.info("/user_register_html 访问状态:"+ret);
        Assert.assertEquals("访问页面状态",200,ret);
    }

    @Test
    public void testRegisterUserByFrom() throws Exception {

        int status = mvc.perform(MockMvcRequestBuilders
                .post("/registerUserByForm")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name","今晚")
                .param("other_name","小金")
                .param("addr","秦国2")
                .param("phone","12345678")
                ).andReturn().getResponse().getStatus();
        logger.info("/registerUserByForm 访问状态:"+status);
        Assert.assertEquals(200,status);
    }

    @Test
    public void testRegisterUserByJson() throws Exception {
        A_USER user = new A_USER();
        user.setName("今晚");
        user.setOther_name("小金");
        user.setAddr("秦国1");
        user.setPhone("0755-12345678");
        String userStr = JSON.toJSONString(user);
        int status = mvc.perform(MockMvcRequestBuilders
                .post("/registerUserByJson")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userStr)).andReturn().getResponse().getStatus();
        logger.info("/registerUserByJson 访问状态:"+status);
        Assert.assertEquals(200,status);
    }

    @Test
    public void testQueryUser() throws Exception {
        int status = mvc.perform(MockMvcRequestBuilders.post("/queryUser"))
                .andReturn().getResponse().getStatus();
        logger.info("/registerUserByJson 访问状态:"+status);
        Assert.assertEquals(200,status);
    }

}