package com.carlot.service;

import com.carlot.exception.BusinessException;
import com.carlot.model.Customer;
import com.carlot.model.CustomerLogin;
import com.carlot.model.EmployeeLogin;

public interface LoginService {
	
	CustomerLogin letCustomerLogin(String login,String password) throws BusinessException;
	EmployeeLogin letEmployeeLogin(String employeeId,String password) throws BusinessException;
	
	int createCustomerAndLogin(Customer customer, CustomerLogin customerLogin) throws BusinessException;
	Customer getCustomerById(int id) throws BusinessException;

}
