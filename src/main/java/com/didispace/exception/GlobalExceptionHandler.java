package com.didispace.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	public static final String ERROR_VIEW = "error";
	
	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest request ,HttpServletResponse response ,Exception e)  throws Exception {
		
		e.printStackTrace();
		
		
		if(isAjax(request)) {
			/**
			 * 返回一个json对象，供前台ajax请求获取使用
			 */
			return "{\"success\":\"false\"}";
		}
		else {
			ModelAndView  mv  = new ModelAndView();
			mv.addObject("exception", e);
			mv.addObject("url", request.getRequestURL());
			mv.setViewName(ERROR_VIEW);
			return mv;
		}
		
		
	}
	
	/**
	 * 判断请求是否是ajax请求
	 */
	private boolean isAjax(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return request.getHeader("X-Requested-With") !=null  && 
				"XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
	
}
