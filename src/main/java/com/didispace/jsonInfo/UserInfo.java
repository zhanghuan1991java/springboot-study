package com.didispace.jsonInfo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UserInfo {
	private String name;
	private int age;
	private String showName;
	
	@JsonIgnore
	private String desc;//忽略此字段
	
	@JsonInclude(Include.ALWAYS)
	private String haveNullValue;//为空时，设置是否显示
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date date;//格式化日期显示
	
	public String getHaveNullValue() {
		return haveNullValue;
	}
	public void setHaveNullValue(String haveNullValue) {
		this.haveNullValue = haveNullValue;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	
	
}
