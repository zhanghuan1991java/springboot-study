package com.didispace.mybatis.a_user_image;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface A_USER_IMAGE_Mapper {

    public Integer insertUserImage(@Param("userImage") A_USER_IMAGE userImage);

}
