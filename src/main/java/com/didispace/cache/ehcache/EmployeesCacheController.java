package com.didispace.cache.ehcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.mybatis.employees.bean.Employees;

@RestController
public class EmployeesCacheController {
	
	@Autowired
	private EmployeeEhcacheService service;
	
	@RequestMapping(value="/cache/{employee_id}")
	public  Employees getCacheEmployee(@PathVariable("employee_id") String employee_id) {
		return service.getCacheByID(employee_id);
	}
	
}