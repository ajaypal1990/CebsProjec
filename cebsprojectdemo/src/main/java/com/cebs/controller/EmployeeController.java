package com.cebs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cebs.bean.EmployeeBean;
import com.cebs.model.Employee;
import com.cebs.service.EmployeeService;

@Controller 
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeservice;

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("command")EmployeeBean employeebean,BindingResult result) {
		Employee employee=PrepareModel(employeebean);
		employeeservice.addEmployee(employee);
		return new ModelAndView("redirect:/add.html");
		
	}
	
	@RequestMapping(value="/employees",method=RequestMethod.GET)
	public ModelAndView listEmployees() {
		
		Map<String,Object> model=new HashMap<String,Object>();
		model.put("employees",prepareListofBean(employeeservice.listofemployee()));
		
		return new ModelAndView("employeeList",model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	 public ModelAndView addEmployee(@ModelAttribute("command")EmployeeBean employeeBean,
	   BindingResult result) {
	  Map<String, Object> model = new HashMap<String, Object>();
	  model.put("employees",prepareListofBean(employeeservice.listofemployee()));
	  return new ModelAndView("addEmployee", model);
	 }
	

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
	  return new ModelAndView("index");
	 }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(@ModelAttribute("command")EmployeeBean employeeBean,
	   BindingResult result) {
	  employeeservice.deleteEmployee(PrepareModel(employeeBean));
	  Map<String, Object> model = new HashMap<String, Object>();
	  model.put("employee", null);
	  model.put("employees",prepareListofBean(employeeservice.listofemployee()));
	  return new ModelAndView("addEmployee", model);
	 }
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEmployee(@ModelAttribute("command")EmployeeBean employeeBean,
	   BindingResult result) {
	  Map<String, Object> model = new HashMap<String, Object>();
	  model.put("employee", prepareEmployeeBean(employeeservice.getEmployee(employeeBean.getId())));
	  model.put("employees",  prepareListofBean(employeeservice.listofemployee()));
	  return new ModelAndView("addEmployee", model);
	 }
	
	
	
	

	private List<EmployeeBean> prepareListofBean(List<Employee> employees){
		  List<EmployeeBean> beans = null;
		  if(employees != null && !employees.isEmpty()){
		   beans = new ArrayList<EmployeeBean>();
		   EmployeeBean bean = null;
		   for(Employee employee : employees){
		    bean = new EmployeeBean();
		    bean.setName(employee.getEmpName());
		    bean.setId(employee.getEmpId());
		    bean.setAddress(employee.getEmpAddress());
		    bean.setSalary(employee.getSalary());
		    bean.setAge(employee.getEmpAge());
		    bean.setEmail(employee.getEmail());
		    beans.add(bean);
		   }
		  }
		  return beans;
		 }

	private Employee PrepareModel(EmployeeBean employeebean) {
		Employee employee=new Employee();
		employee.setEmpAddress(employeebean.getAddress());
		employee.setEmpAge(employeebean.getAge());
		employee.setEmpName(employeebean.getName());
		employee.setSalary(employeebean.getSalary());
		employee.setEmpId(employeebean.getId());
		employee.setEmail(employeebean.getEmail());
		employeebean.setId(null);
		return employee;
	}
	
	 private EmployeeBean prepareEmployeeBean(Employee employee){
		  EmployeeBean bean = new EmployeeBean();
		  bean.setAddress(employee.getEmpAddress());
		  bean.setAge(employee.getEmpAge());
		  bean.setName(employee.getEmpName());
		  bean.setSalary(employee.getSalary());
		  bean.setId(employee.getEmpId());
		  bean.setEmail(employee.getEmail());
		  return bean;
		 }
}
