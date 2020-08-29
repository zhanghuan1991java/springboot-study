package com.didispace.mybatis.a_user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class A_UserController {

    Logger logger = LoggerFactory.getLogger(A_USER.class);

    @Autowired
    private A_USER_Mapper mapper;

    @RequestMapping("/findUser/{id}")
    public String findUserById(@PathVariable String id){
        if(StringUtils.isEmpty(id)){
            id = "";
        }else{
            id = id.trim();
        }
        A_USER user = mapper.selectUserById(id);
        logger.info(user.toString());
        return user.toString();
    };

}

