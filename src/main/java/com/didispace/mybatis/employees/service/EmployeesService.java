package com.didispace.mybatis.employees.service;

import java.util.List;

import com.didispace.mybatis.employees.bean.Employees;

public interface EmployeesService {
	 public List<Employees> selectById(String employeeID);
}
