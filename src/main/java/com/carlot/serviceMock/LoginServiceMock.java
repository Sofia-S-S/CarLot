package com.carlot.serviceMock;

import com.carlot.exception.BusinessException;

public interface LoginServiceMock {
	
	public boolean letCustomerLogin(String login,String password) throws BusinessException;
	public boolean letEmployeeLogin(String employeeId,String password) throws BusinessException;


}
