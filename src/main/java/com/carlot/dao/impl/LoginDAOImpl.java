package com.carlot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.carlot.dao.LoginDAO;
import com.carlot.dbutil.PostresqlConnection;
import com.carlot.exception.BusinessException;
import com.carlot.model.Customer;
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

	@Override
	public int createCustomerAndLogin(Customer customer, CustomerLogin customerLogin) throws BusinessException {
		int cl = 0;
		try (Connection connection=PostresqlConnection.getConnection()){
			String sqlCust = "insert into carlot.customer (first_name, last_name, dob, dl, ssn, contact, address) values( ?,?,?,?,?,?,?)";
			String sqlLog = "insert into carlot.customer_login (login, password) values( ?,?)";
			
			PreparedStatement preparedStatementCust=connection.prepareStatement(sqlCust);
			PreparedStatement preparedStatementLog=connection.prepareStatement(sqlLog);
			
			connection.setAutoCommit(false); // !!!

			preparedStatementCust.setString(1, customer.getFirstName());
			preparedStatementCust.setString(2, customer.getLastName());
			preparedStatementCust.setDate(3, new java.sql.Date(customer.getDob().getTime()));
			preparedStatementCust.setString(4, customer.getDl());
			preparedStatementCust.setLong(5, customer.getSsn());
			preparedStatementCust.setLong(6, customer.getContact());
			preparedStatementCust.setString(7, customer.getAddress());

			int c = preparedStatementCust.executeUpdate();

			preparedStatementLog.setString(1, customerLogin.getLogin());
			preparedStatementLog.setString(2, customerLogin.getPassword());
			
			int l = preparedStatementLog.executeUpdate();
			
			connection.commit(); // !!!
			
			cl= c+l;
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println(e);
			throw new BusinessException("Some internal error occured. Please contact admin");
		}
		return cl;
	}

}
