package com.cebs.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cebs.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao{

	@Autowired
	private SessionFactory sessionfactory;
	
	public void addEmployee(Employee employee) {
		
		sessionfactory.getCurrentSession().saveOrUpdate(employee);
		
	}

	@SuppressWarnings("unchecked")
	public List<Employee> listofEmployee() { 
		// TODO Auto-generated method stub
		return sessionfactory.getCurrentSession().createCriteria(Employee.class).list();
	}

	public Employee getEmployee(int empid) {
		// TODO Auto-generated method stub
		return (Employee) sessionfactory.getCurrentSession().get(Employee.class, empid);
	}

	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().createQuery("DELETE FROM Employee where empId="+employee.getEmpId()).executeUpdate();
		
	}

}
