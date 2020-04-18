package com.didispace.exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestExceptionController {
	
	@RequestMapping("/test")
	public  String testException() {
		
		int i = 1/0;
		
		return "";
	}
}
