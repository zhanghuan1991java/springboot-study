package mybatis.a_user;

import com.didispace.Application;
import com.didispace.mybatis.a_user.A_USER;
import com.didispace.mybatis.a_user.A_USER_Mapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class A_USER_MapperTest {

    @Autowired
    private  A_USER_Mapper mapper;

    @Test
    void selectById() {

        A_USER user = mapper.selectUserById("202008081520000100000");

    }

    @Test
    void selectByName() {
    }


}