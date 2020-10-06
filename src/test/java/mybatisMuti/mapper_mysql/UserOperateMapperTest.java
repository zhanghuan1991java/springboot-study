package mybatisMuti.mapper_mysql;

import com.didispace.Application;
import com.didispace.mybatisMuti.beans.UserOperate;
import com.didispace.mybatisMuti.mapper_mysql.UserOperateMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserOperateMapperTest {

    @Autowired
    private UserOperateMapper mapper;

    @Test
    public void getAllUserOperate(){
        List<UserOperate> list = mapper.getAllUserOperate();
        log.info(list.size()+":  "+list.get(0).toString());

    }
}
