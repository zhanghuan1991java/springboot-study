package mybatisMuti.mapper_mysql;

import cn.hutool.core.date.DateUtil;
import com.didispace.App;
import com.didispace.mybatisMuti.beans.A0001_UserBehaviorReport;
import com.didispace.mybatisMuti.mapper_mysql.A0001_UserBehaviorReportMapper;
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
public class A0001_UserBehaviorReportMapperTest {

    @Autowired
    private A0001_UserBehaviorReportMapper mapper;

    @Test
    public void testInsert(){
        A0001_UserBehaviorReport report = new A0001_UserBehaviorReport();
        report.setUser_id("admin");
        report.setOperate_time(DateUtil.now());
        report.setPage("test.html");
        report.setOperate("click test btn");
        int ret = mapper.insert(report);
        Assert.assertEquals(1,ret);
    }
}
