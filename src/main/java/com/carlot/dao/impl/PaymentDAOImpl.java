package com.carlot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carlot.dao.PaymentDAO;
import com.carlot.dbutil.PostresqlConnection;
import com.carlot.exception.BusinessException;


import com.carlot.model.Payment;

public class PaymentDAOImpl implements PaymentDAO {



	@Override
	public int createPayment(Payment payment) throws BusinessException {
		int c = 0;
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql = "insert into carlot.payment (payment_id, car_id, amount, date) values(?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

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

	@Override
	public List<Payment> getAllPaynemts() throws BusinessException {
		List<Payment> paymentsList = new ArrayList<>();
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql="select payment_id, car_id, amount, model, date";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Payment pay =new Payment();
				pay.setPaymentId(resultSet.getLong("paymentId"));
				pay.setCarId(resultSet.getInt("CarId"));
				pay.setAmount(resultSet.getDouble("amount"));
				pay.setDate(resultSet.getDate("date"));
				paymentsList.add(pay);
			}
			if(paymentsList.size()==0)
			{
				throw new BusinessException("No payments found");
			}
		}catch (ClassNotFoundException | SQLException e) {

			throw new BusinessException("Internal error occured contact admin ");
		}
		return paymentsList;
	}

}
