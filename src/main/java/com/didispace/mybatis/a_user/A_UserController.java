package com.didispace.mybatis.a_user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class A_UserController {

    Logger logger = LoggerFactory.getLogger(A_USER.class);

    @Autowired
    private A_USER_Mapper mapper;

    @RequestMapping("/findUser")
    public String findUserById(){
        A_USER user = mapper.selectUserById("202008081520000100000");
        logger.info(user.toString());
        return user.toString();
    };

}

