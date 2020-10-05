package com.didispace.kafka.beans;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import lombok.Data;

@Data
public class Message {

    private String id;
    private transient String msg;//transient 字段，不会被转换为json 显示
    private String sendTime;

    @Override
    public String toString() {
        return JSONUtil.parseObj(this, JSONConfig.create().setOrder(true).setTransientSupport(true)).toString();
    }

    public String toStringPretty() {
        return JSONUtil.parseObj(this, JSONConfig.create().setOrder(true).setTransientSupport(true)).toStringPretty();
    }
}