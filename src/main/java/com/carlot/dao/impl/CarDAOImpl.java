package com.carlot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.carlot.dao.CarDAO;
import com.carlot.dbutil.PostresqlConnection;
import com.carlot.exception.BusinessException;
import com.carlot.model.Car;

public class CarDAOImpl implements CarDAO{
	
	public static final Logger log = LogManager.getFormatterLogger(CarDAOImpl.class); // v2 set up

	@Override
	public List<Car> getCarsByStatus(String status) throws BusinessException {
		List<Car> carsList = new ArrayList<>();
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql="select id, body, make, model, year, color, mileage, vin from carlot.car where status = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Car car =new Car();
				car.setId(resultSet.getInt("id"));
				car.setBody(resultSet.getString("body"));
				car.setMake(resultSet.getString("make"));
				car.setModel(resultSet.getString("model"));
				car.setYear(resultSet.getInt("year"));
				car.setColor(resultSet.getString("color"));
				car.setMileage(resultSet.getFloat("mileage"));
				car.setVin(resultSet.getString("vin"));
				carsList.add(car);
			}
			if(carsList.size()==0)
			{
				throw new BusinessException("No " + status +" cars found");
			}
		}catch (ClassNotFoundException | SQLException e) {

			throw new BusinessException("Internal error occured contact admin ");
		}
		return carsList;
	}



	@Override
	public Car getCarById(int id) throws BusinessException {
		Car car = null;
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql="select body, make, model, year, color, mileage, vin , status from carlot.car where id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if (resultSet.next()) {
				car = new Car();
				car.setBody(resultSet.getString("body"));
				car.setMake(resultSet.getString("make"));
				car.setModel(resultSet.getString("model"));
				car.setYear(resultSet.getInt("year"));
				car.setColor(resultSet.getString("color"));
				car.setMileage(resultSet.getFloat("mileage"));
				car.setVin(resultSet.getString("vin"));
				car.setStatus(resultSet.getString("status"));
			}else {
				throw new BusinessException("No car found with id "+id);
			}
		}catch (ClassNotFoundException | SQLException e) {

			throw new BusinessException("Internal error occured contact admin ");
		}
		return car;
	}

	@Override
	public int createCar(Car car) throws BusinessException {
		int c = 0;
		try (Connection connection=PostresqlConnection.getConnection()){
			
			String sql="insert into carlot.car(id, body, make, model, year, color, mileage, vin , status) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, car.getId());
			preparedStatement.setString(2, car.getBody());
			preparedStatement.setString(3, car.getMake());
			preparedStatement.setString(4, car.getModel());
			preparedStatement.setInt(5, car.getYear());
			preparedStatement.setString(6, car.getColor());
			preparedStatement.setFloat(7, car.getMileage());
			preparedStatement.setString(8, car.getVin());
			preparedStatement.setString(9, car.getStatus());
			
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException | NumberFormatException e) {
			
			
			throw new BusinessException("Some internal error occured. Please contact admin");
		}
		return c;
	}

	@Override
	public int deleteCar(int id) throws BusinessException {
		int d = 0;
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql="delete from carlot.car where id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			d = preparedStatement.executeUpdate();

		}catch (ClassNotFoundException | SQLException e) {

			throw new BusinessException("Internal error occured contact admin ");
		}
		return d;
	}



	@Override
	public List<Car> getCarsByCustomer(int customerId) throws BusinessException {
		List<Car> carsList = new ArrayList<>();
		try (Connection connection = PostresqlConnection.getConnection()) {
			String sql="select car.id, body, make, model, year, color, mileage, vin from carlot.car "
					+ "inner join carlot.offer on carlot.car.id = carlot.offer.car_id "
					+ "where offer.customer_id=? and offer.status='accepted'";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Car car =new Car();
				car.setId(resultSet.getInt("id"));
				car.setBody(resultSet.getString("body"));
				car.setMake(resultSet.getString("make"));
				car.setModel(resultSet.getString("model"));
				car.setYear(resultSet.getInt("year"));
				car.setColor(resultSet.getString("color"));
				car.setMileage(resultSet.getFloat("mileage"));
				car.setVin(resultSet.getString("vin"));
				carsList.add(car);
			}
			if(carsList.size()==0)
			{
				throw new BusinessException("You do not have any cars");
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.debug(e);

			throw new BusinessException("Internal error occured contact admin ");
		}
		return carsList;
	}


	

}
