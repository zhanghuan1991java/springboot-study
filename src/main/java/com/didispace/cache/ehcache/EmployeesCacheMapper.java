package com.didispace.cache.ehcache;

import org.apache.ibatis.annotations.Param;

import com.didispace.mybatis.employees.bean.Employees;

public interface EmployeesCacheMapper {
	public Employees getCacheByID(@Param(value="employeeID") String employeeID);
}