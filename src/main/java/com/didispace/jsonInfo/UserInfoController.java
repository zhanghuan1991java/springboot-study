package com.didispace.jsonInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用原始的  @Controller  + @ResponseBody
 * @author zhanghuan
 *
 */
@Controller
public class UserInfoController {
	
	@RequestMapping(value="/json",method=RequestMethod.GET)
	@ResponseBody
	public UserInfo getUser() {
		UserInfo u = new UserInfo();
		u.setAge(50);
		u.setName("测试");
		u.setShowName("Hello json");
		return u;
	}
}
