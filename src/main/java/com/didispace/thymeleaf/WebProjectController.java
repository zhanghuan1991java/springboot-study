package com.didispace.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebProjectController {
	
	Logger logger = LoggerFactory.getLogger(WebProjectController.class);
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "许三多");
        mv.addObject("hasLogon","false");
        mv.setViewName("/thymeleaf/index");
        return mv;
    }
	
	@RequestMapping(value = "/case_",method = RequestMethod.GET)
    public ModelAndView case_() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "许三多");
        mv.addObject("hasLogon","false");
        mv.setViewName("/thymeleaf/case_");
        return mv;
    }
	
	@RequestMapping(value = "/about",method = RequestMethod.GET)
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", "许三多");
		mv.addObject("hasLogon","false");
		mv.setViewName("/thymeleaf/about");
		return mv;
	}
	
	@RequestMapping(value = "/information",method = RequestMethod.GET)
	public ModelAndView information() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", "许三多");
		mv.addObject("hasLogon","false");
		mv.setViewName("/thymeleaf/information");
		return mv;
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", "许三多");
		mv.addObject("hasLogon","false");
		mv.setViewName("/thymeleaf/register");
		return mv;
	}
	
	/**
	 * 接收页面的参数，只需  变量名    与页面  name  的名称保持一致即可
	 * @param username
	 * @param userpassword
	 * @param useremail
	 * @return
	 */
	@RequestMapping(value = "/newUser",method = RequestMethod.POST)
	public ModelAndView newUser(String username,String userpassword,String useremail) {
		logger.info("username" +"————>"+ username);
		logger.info("userpassword"+"————>"+ userpassword);
		logger.info("useremail"+"————>"+ useremail);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("retMsg", "注册成功!");
		mv.addObject("hasLogon","true");
		mv.addObject("username","用户名:"+username);
		mv.addObject("useremail","注册使用邮件:" +useremail);
		
		mv.setViewName("/thymeleaf/newUser");
		return mv;
	}
	
	@RequestMapping(value = "/employees",method = RequestMethod.GET)
	public ModelAndView employees() {
		ModelAndView mv =  new ModelAndView();
		mv.addObject("hasLogon","true");
		mv.setViewName("/thymeleaf/employees");
		return mv;
	}
	

	
	
}
