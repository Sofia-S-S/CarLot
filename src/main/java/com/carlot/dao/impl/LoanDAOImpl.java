package com.carlot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.carlot.dao.LoanDAO;
import com.carlot.dbutil.PostresqlConnection;
import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;

public class LoanDAOImpl implements LoanDAO {
	
	public static final Logger log = LogManager.getFormatterLogger(LoanDAOImpl.class); // v2 set up
	
	@Override
	public int createLoan(Loan loan) throws BusinessException {
		int c = 0;
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql = "insert into carlot.loan (car_id, amount, rate, term, interest, monthly_payment, remaining_balance, sale_date) values( ?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, loan.getCarId());
			preparedStatement.setDouble(2, loan.getAmount());
			preparedStatement.setDouble(3, loan.getRate());
			preparedStatement.setInt(4, loan.getTerm());
			preparedStatement.setDouble(5, loan.getInterest());
			preparedStatement.setDouble(6, loan.getMonthlyPayment());
			preparedStatement.setDouble(7, loan.getRemainingBalance());
			preparedStatement.setDate(8, new java.sql.Date(loan.getSaleDate().getTime()));
			c = preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e);

			throw new BusinessException("Some internal error occured. Please contact admin");
		}
		return c;
	}
}
