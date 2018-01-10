package com.cebs.dao;

import java.util.List;

import com.cebs.model.Employee;

public interface EmployeeDao {

	public void addEmployee(Employee employee);
	
	public List<Employee> listofEmployee();
	
	public Employee getEmployee(int empid);
	
	public void deleteEmployee(Employee employee);
}
