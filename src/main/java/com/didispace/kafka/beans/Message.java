package com.didispace.kafka.beans;

import cn.hutool.json.JSONUtil;

public class Message {

    private String id;

    private String msg;

    private String sendTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return JSONUtil.parseObj(this,true).toString();
    }

    public String toStringPretty(){
        return JSONUtil.parseObj(this,true).toStringPretty();
    }
}