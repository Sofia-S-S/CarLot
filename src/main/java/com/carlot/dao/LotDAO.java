package com.carlot.dao;

import java.util.List;

import com.carlot.exception.BusinessException;
import com.carlot.model.Car;
import com.carlot.model.Offer;

public interface LotDAO {
	
	public List<Car> getCarsByStatus (String status) throws BusinessException;
	public Car getCarById (int id) throws BusinessException;
	
	public int createCar (Car car) throws BusinessException;
	public int deleteCar(int id) throws BusinessException;
	
	public List<Offer> getOffersByStatus (String status) throws BusinessException;
	public List<Offer> getOffersByCarId (int carId) throws BusinessException;
	public List<Offer> getOffersByCustomerId(int customerId) throws BusinessException;
	public int updateOfferStatusForReject (int offerId)  throws BusinessException;
	
}
