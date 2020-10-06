package com.didispace.mybatisMuti.beans;

import cn.hutool.json.JSONUtil;
import lombok.Data;

@Data
public class UserOperate {

    private String id;
    private String name;
    private String operate;

    @Override
    public String toString() {
        return JSONUtil.parseObj(this,true).toStringPretty();
    }

}
