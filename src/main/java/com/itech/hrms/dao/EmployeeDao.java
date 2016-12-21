package com.itech.hrms.dao;

import com.itech.hrms.model.Employee;

public interface EmployeeDao {
	public boolean authLoginService(int id, String password);
	public Employee employeeRegisterService(Employee employee);
//	public boolean setPasswordService(int id, String old_password, String new_password);
	public boolean setPasswordService(int id, String password, String old_password);
}
