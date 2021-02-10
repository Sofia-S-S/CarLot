package com.carlot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carlot.dao.OfferDAO;
import com.carlot.dbutil.PostresqlConnection;
import com.carlot.exception.BusinessException;
import com.carlot.model.Offer;

public class OfferDAOImpl implements OfferDAO {

	
	@Override
	public int createOffer(Offer offer) throws BusinessException {
		int c = 0;
		try (Connection connection=PostresqlConnection.getConnection()){
			String sql = "insert into carlot.offer (car_id, amount, status, customer_id, date) values( ?,?,?,?,?)";
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);

			preparedStatement.setInt(1, offer.getCarId());
			preparedStatement.setDouble(2, offer.getAmount());
			preparedStatement.setString(3, offer.getStatus());
			preparedStatement.setInt(4, offer.getCustomerId());
			preparedStatement.setDate(5, new java.sql.Date(offer.getDate().getTime()));

			c = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			
			throw new BusinessException("Some internal error occured. Please contact admin");
		}
		return c;
	}
//	@Override
//	public List<Offer> getOffersByStatus(String status) throws BusinessException {
//		List<Offer> offersList = new ArrayList<>();
//		try (Connection connection = PostresqlConnection.getConnection()) {
//			String sql="select offer_id, car_id, amount, customer_id, date from carlot.offer where status = ? order by customer_id";
//			PreparedStatement preparedStatement=connection.prepareStatement(sql);
//			preparedStatement.setString(1, status);
//			ResultSet resultSet=preparedStatement.executeQuery();
//			while(resultSet.next()) {
//				Offer offer =new Offer();
//				offer.setOfferId(resultSet.getInt("offer_id"));
//				offer.setCarId(resultSet.getInt("car_id"));
//				offer.setCustomerId(resultSet.getInt("customer_id"));
//				offer.setAmount(resultSet.getDouble("amount"));
//				offer.setDate(resultSet.getDate("date"));
//				offersList.add(offer);
//			}
//			if(offersList.size()==0)
//			{
//				throw new BusinessException("No " + status +" offers found");
//			}
//		}catch (ClassNotFoundException | SQLException e) {
//
//			throw new BusinessException("Internal error occured contact admin ");
//		}
//		return offersList;
//	}
//
//	@Override
//	public List<Offer> getOffersByCarId(int carId) throws BusinessException {
//		List<Offer> offersList = new ArrayList<>();
//		try (Connection connection = PostresqlConnection.getConnection()) {
//			String sql="select offer_id, amount, customer_id, date, status from carlot.offer where car_id = ? order by date";
//			PreparedStatement preparedStatement=connection.prepareStatement(sql);
//			preparedStatement.setInt(1, carId);
//			ResultSet resultSet=preparedStatement.executeQuery();
//			while(resultSet.next()) {
//				Offer offer =new Offer();
//				offer.setOfferId(resultSet.getInt("offer_id"));
//				offer.setCustomerId(resultSet.getInt("customer_id"));
//				offer.setAmount(resultSet.getDouble("amount"));
//				offer.setDate(resultSet.getDate("date"));
//				offer.setStatus(resultSet.getString("status"));
//				offersList.add(offer);
//			}
//			if(offersList.size()==0)
//			{
//				throw new BusinessException("No offers found for a car with id "+ carId);
//			}
//		}catch (ClassNotFoundException | SQLException e) {
//
//			throw new BusinessException("Internal error occured contact admin ");
//		}
//		return offersList;
//	}
//
//	@Override
//	public List<Offer> getOffersByCustomerId(int customerId) throws BusinessException {
//		List<Offer> offersList = new ArrayList<>();
//		try (Connection connection = PostresqlConnection.getConnection()) {
//			String sql="select offer_id, car_id, amount, date, status from carlot.offer where customer_id = ? order by date";
//			PreparedStatement preparedStatement=connection.prepareStatement(sql);
//			preparedStatement.setInt(1, customerId);
//			ResultSet resultSet=preparedStatement.executeQuery();
//			while(resultSet.next()) {
//				Offer offer =new Offer();
//				offer.setOfferId(resultSet.getInt("offer_id"));
//				offer.setCarId(resultSet.getInt("car_id"));
//				offer.setAmount(resultSet.getDouble("amount"));
//				offer.setDate(resultSet.getDate("date"));
//				offer.setStatus(resultSet.getString("status"));
//				offersList.add(offer);
//			}
//			if(offersList.size()==0)
//			{
//				throw new BusinessException("You did not make any offers yet");
//			}
//		}catch (ClassNotFoundException | SQLException e) {
//
//			throw new BusinessException("Internal error occured contact admin ");
//		}
//		return offersList;
//	}

	@Override
	public int rejectOfferById (long offerId) throws BusinessException {
		int up = 0;
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql="update carlot.offer set status='rejected' where offer_id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setLong(1, offerId);
			up = preparedStatement.executeUpdate();

		}catch (SQLException e) {

			throw new BusinessException("Internal error occured contact admin ");
		}
		return up;
	}
	
	@Override
	public int rejectOfferByCarId(int carId) throws BusinessException {
		int up = 0;
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql="update carlot.offer set status='rejected' where status='pending' and car_id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setLong(1, carId);
			up = preparedStatement.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();

			throw new BusinessException("Internal error occured contact admin ");

		}
		return up;
	}


	@Override
	public Offer getOfferById(long offerId) throws BusinessException {
		Offer offer = null;
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql="select car_id, amount, date, status, customer_id from carlot.offer where offer_id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setLong(1, offerId);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				offer =new Offer();
				offer.setCarId(resultSet.getInt("car_id"));
				offer.setAmount(resultSet.getDouble("amount"));
				offer.setDate(resultSet.getDate("date"));
				offer.setStatus(resultSet.getString("status"));
				offer.setCustomerId(resultSet.getInt("customer_id"));

			} else {
				throw new BusinessException("No offer found with id "+offerId);
			}
		}catch (SQLException e) {

			throw new BusinessException("Internal error occured contact admin ");
		}
		return offer;
	}
	
	@Override
	public int approveOffer(long offerId, int carId) throws BusinessException {
		int xy = 0;
		try (Connection connection=PostresqlConnection.getConnection()){	

		String sqlAccept="update carlot.offer set status='accepted' where offer_id=?";
		String sqlCar="update carlot.car set status='sold' where id=?";
		
		PreparedStatement preparedStatementAccept=connection.prepareStatement(sqlAccept);
		PreparedStatement preparedStatementCar=connection.prepareStatement(sqlCar);
		
		connection.setAutoCommit(false); // !!!
		
		preparedStatementAccept.setLong(1, offerId);
		int x = preparedStatementAccept.executeUpdate();
		
		preparedStatementCar.setInt(1, carId);
		int y = preparedStatementCar.executeUpdate();
		
		connection.commit(); // !!!
		
		xy = x+y;
	} catch (SQLException e) {
		e.printStackTrace();
		
		throw new BusinessException("Some internal error occured. Please contact admin");
		
		
	}
	return xy;}
	@Override
	public List<Offer> getAllOffers() throws BusinessException {
		List<Offer> offersList = new ArrayList<>();
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql="select offer_id, car_id, amount, status, customer_id, date from carlot.offer";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Offer offer =new Offer();
				offer.setOfferId(resultSet.getInt("offer_id"));
				offer.setCarId(resultSet.getInt("car_id"));
				offer.setAmount(resultSet.getDouble("amount"));
				offer.setCustomerId(resultSet.getInt("customer_id"));
				offer.setStatus(resultSet.getString("status"));
				offer.setDate(resultSet.getDate("date"));
				offersList.add(offer);
			}
			if(offersList.size()==0)
			{
				throw new BusinessException("You did not make any offers yet");
			}
		}catch (SQLException e) {

			throw new BusinessException("Internal error occured contact admin ");
		}
		return offersList;
	}

}
