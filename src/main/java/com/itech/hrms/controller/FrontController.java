package com.itech.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.itech.hrms.model.Employee;
import com.itech.hrms.model.Status;
import com.itech.hrms.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class FrontController {
	
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status registration(@RequestBody Employee employee){
		try{
			//test123
			System.out.println("Inside registration()");
			empService.employeeRegister(employee);
	        System.out.println("EmpDao ::"+empService);
	        return new Status("Registered Successfully! ");
		}catch(Exception e){
			return new Status("Wrong Inputs! ");			
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Status checkLogin(@RequestBody Employee employee){
		try{
			boolean status=empService.authLogin(employee.getId(),employee.getPassword());
			if(!status){
				return new Status("Invalid Username or Password!");
			}
			return new Status("You are Logged in.... ");
		}catch(Exception e){
			return new Status(e.toString());
		}
	}
	
	@RequestMapping(value = "/change_password", method = RequestMethod.GET)
	public @ResponseBody Status changePassword(@RequestParam("id") int id,@RequestParam("password")String password,@RequestParam("old_password")String old_password){
		try{
			boolean status=empService.setPassword(id, password, old_password);
			if(!status){
				return new Status("Invalid Id or Password!");
			}
			return new Status("Password changed.... ");
		}catch(Exception e){
			return new Status(e.toString());
		}
	}
}
