package com.carlot.service;

import com.carlot.exception.BusinessException;
import com.carlot.model.CustomerLogin;
import com.carlot.model.EmployeeLogin;

public interface LoginService {
	
	public CustomerLogin letCustomerLogin(String login,String password) throws BusinessException;
	public EmployeeLogin letEmployeeLogin(String employeeId,String password) throws BusinessException;

}
