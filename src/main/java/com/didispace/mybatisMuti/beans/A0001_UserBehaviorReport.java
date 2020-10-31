package com.didispace.mybatisMuti.beans;

import cn.hutool.json.JSONUtil;
import lombok.Data;

@Data
public class A0001_UserBehaviorReport {

    private String id ;

    private String user_id;

    private String operate_time;

    private String page;

    private String operate;

    @Override
    public String toString() {
        return JSONUtil.parseObj(this).toString();
    }

    public String toStringPretty(){
        return JSONUtil.parseObj(this).toStringPretty();
    }

}
