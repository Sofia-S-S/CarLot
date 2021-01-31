package com.carlot.service.impl;

import com.carlot.dao.LoginDAO;
import com.carlot.dao.impl.LoginDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.model.CustomerLogin;
import com.carlot.model.EmployeeLogin;
import com.carlot.service.LoginService;

public class LoginServiceImpl implements LoginService{
	
	private LoginDAO dao = new LoginDAOImpl();


	@Override
	public CustomerLogin letCustomerLogin(String login, String password) throws BusinessException {
		CustomerLogin customerLogin = null;
		if(login!=null && login.matches("[a-zA-Z0-9]{4,12}")&& password!=null && password.matches("[a-zA-Z0-9]{4,12}")) {
			customerLogin = dao.letCustomerLogin(login, password);
		} else {
				throw new BusinessException("Entered loging or password is INVALID. Please try again");
			}
		return customerLogin;
	}

	@Override
	public EmployeeLogin letEmployeeLogin(String employeeId, String password) throws BusinessException {
		EmployeeLogin employeeLogin =null;
		if(employeeId!=null && employeeId.matches("[a-zA-Z]{2}[0-9]{4}")&& password!=null && password.matches("[a-zA-Z0-9]{4,12}")) {

			employeeLogin =dao.letEmployeeLogin(employeeId, password);
		}else {
			throw new BusinessException("Entered loging or password is INVALID. Please try again");
		}
		return employeeLogin;
	}

}
