package com.cebs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cebs.dao.EmployeeDao;
import com.cebs.model.Employee;

@Service("employeeservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.addEmployee(employee);
		
	}

	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
		employeeDao.deleteEmployee(employee);
		
	}

	public List<Employee> listofemployee() {
		// TODO Auto-generated method stub
		return employeeDao.listofEmployee();
	}

	public Employee getEmployee(int empid) {
		// TODO Auto-generated method stub
		return employeeDao.getEmployee(empid);
	}

	

}
