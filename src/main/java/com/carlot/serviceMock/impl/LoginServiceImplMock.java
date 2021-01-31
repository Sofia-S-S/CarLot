package com.carlot.serviceMock.impl;

import com.carlot.dao.impl.LoginDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.serviceMock.LoginServiceMock;

public class LoginServiceImplMock implements LoginServiceMock {
	
	LoginDAOImpl loginDAO;

	@Override
	public boolean letCustomerLogin(String login, String password) throws BusinessException {
		boolean b = false;
		loginDAO.letCustomerLogin(login, password);
		if(login!=null && login.matches("[a-zA-Z0-9]{4,12}")&& password!=null && password.matches("[a-zA-Z0-9]{4,12}")) {
			b = true;
      }
		return b;
	}

	@Override
	public boolean letEmployeeLogin(String employeeId, String password) throws BusinessException {
		boolean b = false;
		loginDAO.letEmployeeLogin(employeeId, password);
		if(employeeId!=null && employeeId.matches("[a-zA-Z]{2}[0-9]{4}") && password!=null && password.matches("[a-zA-Z0-9]{4,12}")) {
			b = true;
		}
		return b;
	}

}
