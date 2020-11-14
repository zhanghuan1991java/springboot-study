package com.didispace.mybatis.a_user_image;

import cn.hutool.json.JSONUtil;
import lombok.Data;

@Data
public class A_USER_IMAGE {
    private String user_id ;
    private byte[] image;

    @Override
    public String toString() {
        return JSONUtil.parseObj(this).toStringPretty();
    }
}
