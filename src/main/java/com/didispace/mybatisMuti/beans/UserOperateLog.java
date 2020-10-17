package com.didispace.mybatisMuti.beans;

import cn.hutool.json.JSONUtil;
import lombok.Data;

@Data
public class UserOperateLog {
    private String id;
    private String user_id;
    private String oper_log;

    @Override
    public String toString() {
        return JSONUtil.parseObj(this).toString();
    }

    public String toStringPretty(){
        return JSONUtil.parseObj(this).toStringPretty();
    }
}
