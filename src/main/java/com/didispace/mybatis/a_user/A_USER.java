package com.didispace.mybatis.a_user;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;

@Data
public class A_USER{

    private String id;
    private String name;
    private String other_name;
    private String phone;
    private String addr;
    private String identity_code;
    private String create_time;
    private String update_time;
/*
    public String getName() {
        return hideSensitiveMsg(name,1,1,1);
    }
    public String getPhone() {
        return hideSensitiveMsg(phone,1,3,4);
    }
    public String getAddr() {
        return hideSensitiveMsg(addr,1,2,4);
    }
    public String getIdentity_code() {
        return hideSensitiveMsg(identity_code,2,4,8);
    }


    public String getRealName(){
        return name;
    }
    public String getRealPhone() {
        return phone;
    }
    public String getRealAddr() {
        return addr;
    }
    public String getRealIdentity_code() {
        return identity_code;
    }*/

    /**
     * 设置字段脱敏显示
     * 保留 首尾 ，中间用 * 替代
     */
//    private String hideSensitiveMsg(String info,int first,int last,int starCount) {
//        if(StrUtil.isEmpty(info)){
//            return "";
//        }
//
//        if(info.length() < Math.max(first,last)){
//            return info.substring(0,1)+"*";
//        }
//
//        String star = "";
//        for(int i =0 ; i < starCount ; i ++){
//            star+="*";
//        }
//
//        return info.substring(0,first)+star+info.substring(info.length()-last);
//    }



    @Override
    public String toString() {
        return JSONUtil.parseObj(this).toStringPretty();
    }
}
