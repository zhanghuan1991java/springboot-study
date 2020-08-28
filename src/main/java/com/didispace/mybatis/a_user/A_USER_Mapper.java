package com.didispace.mybatis.a_user;

import com.didispace.mybatis.pageInfo.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface A_USER_Mapper {

    public  A_USER selectUserById(@Param("id") String id);

    public  A_USER selectUserByName(@Param("name") String name);

    public Integer insertUser(@Param("user") A_USER user);

    public List<A_USER> getAllUser(@Param("page") Page page);

    public Integer getCountNum();
}
