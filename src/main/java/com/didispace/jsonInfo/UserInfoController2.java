package com.didispace.jsonInfo;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用RestController
 * 
 * @author zhanghuan
 *
 */
@RestController
public class UserInfoController2 {
	/**
	 * springboot解决跨域问题
	 * CrossOrigin
	 * @return
	 */
	@CrossOrigin("http://localhost:9000")
	@RequestMapping(value="/json2",method=RequestMethod.GET)
	public UserInfo getUser() {
		UserInfo u = new UserInfo();
		u.setAge(50);
		u.setName("测试");
		u.setShowName("Hello json2");
		u.setDesc("这是JsonIgnore字段，不会出现在返回json串里面！");
		u.setDate(new Date());
		return u;
	}
	
	
}
