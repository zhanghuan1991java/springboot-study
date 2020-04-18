package com.didispace.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.didispace.mybatis.employees.service.EmployeesService;

@RestController
public class EmployeesController {
	
	Logger logger = LoggerFactory.getLogger(EmployeesController.class);
	
	@Autowired
	private EmployeesService service;
	
	@RequestMapping("/getEmployees")
	public Object getEmployees(String employeeID) {
		
		ModelAndView mv =  new ModelAndView();
		mv.addObject("hasLogon","true");
		mv.addObject("employees", service.selectById(employeeID));
		mv.setViewName("/thymeleaf/employees");
		
		return mv;
	}
}
