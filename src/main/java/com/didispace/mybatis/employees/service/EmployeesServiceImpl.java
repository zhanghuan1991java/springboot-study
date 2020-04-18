package com.didispace.mybatis.employees.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didispace.mybatis.employees.bean.Employees;
import com.didispace.mybatis.employees.mapper.EmployeesMapper;

@Service
public class EmployeesServiceImpl implements EmployeesService {
	
	private static Logger logger =LoggerFactory.getLogger(EmployeesServiceImpl.class);
	
	@Autowired
	private EmployeesMapper mapper;
	
	@Override
	public List<Employees> selectById(String employeeID) {
		List<Employees> employees = mapper.selectById(employeeID);
		logger.info(employees.size()+"+++++");
		return employees;
	}

}
