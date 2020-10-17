package com.didispace.mybatis.a_user_image;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface A_USER_IMAGE_Mapper {

    public Integer insertUserImage(@Param("userImage") A_USER_IMAGE userImage);

    @Select("select * from (select i.image from a_user_image i where i.id = #{id} order by i.create_time desc) where rownum=1")
    public Map selectUserImage(String id);
}
