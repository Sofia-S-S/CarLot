package com.carlot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.carlot.dao.PaymentDAO;
import com.carlot.dbutil.PostresqlConnection;
import com.carlot.exception.BusinessException;


import com.carlot.model.Payment;

public class PaymentDAOImpl implements PaymentDAO {

	public static final Logger log = LogManager.getFormatterLogger(LoanDAOImpl.class); // v2 set up

	@Override
	public int createPayment(Payment payment, double balance) throws BusinessException {
		int xy = 0;
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sqlPayment = "insert into carlot.payment (car_id, amount, date) values(?,?,?)";
			String sqlLoan = "update carlot.loan set remaining_balance = ? where car_id = ?";

			PreparedStatement preparedStatementPayment = connection.prepareStatement(sqlPayment);
			PreparedStatement preparedStatementLoan = connection.prepareStatement(sqlLoan);
			
			connection.setAutoCommit(false); // !!!

			preparedStatementPayment.setInt(1, payment.getCarId());
			preparedStatementPayment.setDouble(2, payment.getAmount());
			preparedStatementPayment.setDate(3, new java.sql.Date(payment.getDate().getTime()));
			int x = preparedStatementPayment.executeUpdate();
			
			preparedStatementLoan.setDouble(1, balance);
			preparedStatementLoan.setInt(2, payment.getCarId());
			int y = preparedStatementLoan.executeUpdate();
			
			connection.commit(); // !!!
			
			xy = x+y;

		} catch (SQLException e) {
			
			log.debug(e);

			throw new BusinessException("Some internal error occured. Please contact admin");
		}
		return xy;
	}

	@Override
	public List<Payment> getAllPayments() throws BusinessException {
		List<Payment> paymentsList = new ArrayList<>();
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql="select payment_id, car_id, amount, date from carlot.payment";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Payment pay =new Payment();
				pay.setPaymentId(resultSet.getLong("payment_id"));
				pay.setCarId(resultSet.getInt("car_id"));
				pay.setAmount(resultSet.getDouble("amount"));
				pay.setDate(resultSet.getDate("date"));
				paymentsList.add(pay);
			}
			if(paymentsList.size()==0)
			{
				throw new BusinessException("No payments found");
			}
		}catch (SQLException e) {
			
			log.debug(e);

			throw new BusinessException("Internal error occured contact admin ");
		}
		return paymentsList;
	}

}
