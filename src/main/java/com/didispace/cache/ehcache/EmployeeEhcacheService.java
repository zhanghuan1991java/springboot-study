package com.didispace.cache.ehcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.didispace.mybatis.employees.bean.Employees;

@Service
@CacheConfig(cacheNames = "employee_cache")
public class EmployeeEhcacheService {
	
	@Autowired
	private EmployeesCacheMapper mapper;
	
	@Cacheable
	public Employees getCacheByID(String employeeID) {
		return mapper.getCacheByID(employeeID);
	};
	
}
