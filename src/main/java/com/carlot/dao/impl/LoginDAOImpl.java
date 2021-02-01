package com.carlot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.carlot.dao.LoginDAO;
import com.carlot.dbutil.PostresqlConnection;
import com.carlot.exception.BusinessException;
import com.carlot.model.CustomerLogin;
import com.carlot.model.EmployeeLogin;

public class LoginDAOImpl implements LoginDAO {

//--------------------------------------Customer Log In -------------------------------------
	@Override
	public CustomerLogin letCustomerLogin(String login, String password) throws BusinessException {
		CustomerLogin customerLogin = null;
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql = "select customer_id from carlot.customer_login where login=? AND password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				customerLogin = new CustomerLogin();
				customerLogin.setLogin(login);
				customerLogin.setPassword(password);
				customerLogin.setCustomerId(resultSet.getInt("customer_id"));

			} else {

				throw new BusinessException("No customer found with such login or password");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("exception in DAO");

			throw new BusinessException("Internal error occured contact admin ");
		}
		return customerLogin;
	}
	
//--------------------------------------Employee Log In -------------------------------------
	@Override
	public EmployeeLogin letEmployeeLogin(String employeeId, String password) throws BusinessException {
		EmployeeLogin employeeLogin = null;
		try (Connection connection=PostresqlConnection.getConnection()){	
			String sql = "select name from carlot.employee_login where employee_id=? and password=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, employeeId);
			preparedStatement.setString(2, password);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				//if we found an employee -> instantiate player object and set values to it
				employeeLogin = new EmployeeLogin();
				employeeLogin.setEmployee_id(employeeId);
				employeeLogin.setPassword(password);
				employeeLogin.setName(resultSet.getString("name"));
			
			}else {
				throw new BusinessException("No employee found with id and password ");
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Some internal error occured. Please contact admin");
		
			
		} 
		return employeeLogin;
	}

}
