package com.didispace.mybatis.employees.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Employees implements Serializable{
	
	private static final long serialVersionUID = -3334251549369902227L;
	
	private String employeeID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String hireDate;
	private String jobID;
	private BigDecimal salary;
	private BigDecimal commissionPct;
	private int managerID ;
	private int departmentID;
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getJobID() {
		return jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public BigDecimal getCommissionPct() {
		return commissionPct;
	}
	public void setCommissionPct(BigDecimal commissionPct) {
		this.commissionPct = commissionPct;
	}
	public int getManagerID() {
		return managerID;
	}
	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	@Override
	public String toString() {
		return "Employees [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", jobID=" + jobID
				+ ", salary=" + salary + ", commissionPct=" + commissionPct + ", managerID=" + managerID
				+ ", departmentID=" + departmentID + "]";
	}
	
}
