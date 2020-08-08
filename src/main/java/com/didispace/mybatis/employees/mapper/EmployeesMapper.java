package com.didispace.mybatis.employees.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.didispace.mybatis.employees.bean.Employees;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public interface EmployeesMapper {
	public List<Employees> selectById(@Param(value="employeeID") String employeeID);
}
