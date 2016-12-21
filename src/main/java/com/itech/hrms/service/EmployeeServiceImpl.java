package com.itech.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.itech.hrms.dao.EmployeeDao;
import com.itech.hrms.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao empDao;
	
	public Employee employeeRegister(Employee employee) {
		return empDao.employeeRegisterService(employee);
	}

	public boolean authLogin(int id, String password) {
		return empDao.authLoginService(id, password);
	}

	public boolean setPassword(int id, String password, String old_password) {
		return empDao.setPasswordService(id, password,old_password);
	}
}
