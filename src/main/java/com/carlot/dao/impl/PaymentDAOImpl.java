package com.carlot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.carlot.dao.PaymentDAO;
import com.carlot.dbutil.PostresqlConnection;
import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;

import com.carlot.model.Payment;

public class PaymentDAOImpl implements PaymentDAO{

	
	@Override
	public int createLoan(Loan loan) throws BusinessException {
		int c = 0;
		try (Connection connection=PostresqlConnection.getConnection()){
			String sql = "insert into carlot.loan (car_id, amount, sale_date, lengths, monthly_payment, remaining_balance) values( ?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);

			preparedStatement.setInt(1, loan.getCarId());
			preparedStatement.setDouble(2, loan.getAmount());
			preparedStatement.setDate(3, new java.sql.Date(loan.getSaleDate().getTime()));
			preparedStatement.setInt(4, loan.getMonths());
			preparedStatement.setDouble(5, loan.getMonthlyPayment());
			preparedStatement.setDouble(6, loan.getRemainingBalance());
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			
			throw new BusinessException("Some internal error occured. Please contact admin");
		}
		return c;
	}

	@Override
	public int createPayment(Payment payment) throws BusinessException {
		int c = 0;
		try (Connection connection=PostresqlConnection.getConnection()){
			String sql = "insert into carlot.payment (payment_id, car_id, amount, date) values(?,?,?,?)";
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);

			preparedStatement.setLong(1, payment.getPaymentId());
			preparedStatement.setInt(2, payment.getCarId());
			preparedStatement.setDouble(3, payment.getAmount());
			preparedStatement.setDate(4, new java.sql.Date(payment.getDate().getTime()));

			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			
			throw new BusinessException("Some internal error occured. Please contact admin");
		}
		return c;
	}

	

}
