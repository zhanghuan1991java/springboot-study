package com.didispace.mybatis.a_user;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface A_USER_Mapper {

    public  A_USER selectUserById(@Param("id") String id);

    public  A_USER selectUserByName(@Param("name") String name);

    public Integer insertUser(@Param("user") A_USER user);
}
