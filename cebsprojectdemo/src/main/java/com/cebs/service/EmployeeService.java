package com.cebs.service;

import java.util.List;

import com.cebs.model.Employee;

public interface EmployeeService {

	public void addEmployee(Employee employee);
	
	public void deleteEmployee(Employee employee);
	
	public List<Employee> listofemployee();
	
	public Employee getEmployee(int empid);
}
