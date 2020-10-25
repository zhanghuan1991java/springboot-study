package mybatis.a_user;

import com.didispace.App;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class A_UserControllerTestMoc {
    Logger logger = LoggerFactory.getLogger(A_UserControllerTestMoc.class);
    @Autowired
    private MockMvc mvc;

    @Test
    public void findUserById() throws Exception {
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/findUser/202008081520000100000"));
        MvcResult result = ra.andReturn();
        Assert.assertEquals(result.getResponse().getStatus(),200);
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void findUserById2() throws Exception {
        try{
            ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/findUser/ "));
            MvcResult result = ra.andReturn();
            Assert.assertEquals(result.getResponse().getStatus(),200);
        }catch (TooManyResultsException e){
            logger.info("案例正常，测试反例，"+e.getMessage());
        }

    }
}