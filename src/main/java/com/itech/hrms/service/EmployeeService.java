package com.itech.hrms.service;

import com.itech.hrms.model.Employee;

public interface EmployeeService {
	public boolean authLogin(int id, String password);
	public Employee employeeRegister(Employee employee);
//	public boolean setPassword(int id, String old_password, String new_password);
	public boolean setPassword(int id, String password, String old_password);
}
