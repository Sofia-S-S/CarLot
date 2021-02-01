package com.carlot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.carlot.dao.SaleDAO;
import com.carlot.dbutil.PostresqlConnection;
import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;
import com.carlot.model.Offer;
import com.carlot.model.Payment;

public class SaleDAOImpl implements SaleDAO{

	@Override
	public int approveOffer(int offerId, int carId) throws BusinessException {
		int xyz = 0;
		try (Connection connection=PostresqlConnection.getConnection()){	

		String sqlAccept="update carlot.offer set status='accepted' where offerId=?";
		String sqlReject="update carlot.offer set status='rejected' where status='pending' and car_id=?";
		String sqlCar="update carlot.car set status='sold' where id=?";
		
		PreparedStatement preparedStatementAccept=connection.prepareStatement(sqlAccept);
		PreparedStatement preparedStatementReject=connection.prepareStatement(sqlReject);
		PreparedStatement preparedStatementCar=connection.prepareStatement(sqlCar);
		
		connection.setAutoCommit(false); // !!!
		
		preparedStatementAccept.setInt(1, offerId);
		int x = preparedStatementAccept.executeUpdate();
		
		preparedStatementReject.setInt(1, carId);
		int y = preparedStatementReject.executeUpdate();
		
		preparedStatementCar.setInt(1, carId);
		int z = preparedStatementCar.executeUpdate();
		
		connection.commit(); // !!!
		
		xyz = x+y+z;
	} catch (ClassNotFoundException | SQLException e) {
		
		
		throw new BusinessException("Some internal error occured. Please contact admin");
		
	}
	return xyz;}

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

	@Override
	public int createOffer(Offer offer) throws BusinessException {
		int c = 0;
		try (Connection connection=PostresqlConnection.getConnection()){
			String sql = "insert into carlot.offer (offer_id, car_id, amount, status, customer_id, date) values( ?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);

			preparedStatement.setLong(1, offer.getOfferId());
			preparedStatement.setInt(2, offer.getCarId());
			preparedStatement.setDouble(3, offer.getAmount());
			preparedStatement.setString(4, offer.getStatus());
			preparedStatement.setInt(5, offer.getCustomerId());
			preparedStatement.setDate(6, new java.sql.Date(offer.getDate().getTime()));

			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			
			throw new BusinessException("Some internal error occured. Please contact admin");
		}
		return c;
	}

}
