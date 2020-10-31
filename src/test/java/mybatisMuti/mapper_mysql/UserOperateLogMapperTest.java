package mybatisMuti.mapper_mysql;

import cn.hutool.core.util.IdUtil;
import com.didispace.App;
import com.didispace.mybatisMuti.beans.UserOperateLog;
import com.didispace.mybatisMuti.mapper_mysql.UserOperateLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class UserOperateLogMapperTest {

    @Autowired
    private UserOperateLogMapper logMapper;

    @Test
    public void  insertUserOperateLog(){
        UserOperateLog operateLog = new UserOperateLog();
        operateLog.setId(IdUtil.randomUUID());
        operateLog.setUser_id("TestUser");
        operateLog.setOper_log(operateLog.toString());
        log.info(operateLog.toStringPretty());
        int ret = logMapper.insertUserOperateLog(operateLog);

        Assert.assertEquals(1,ret);
    }
}
