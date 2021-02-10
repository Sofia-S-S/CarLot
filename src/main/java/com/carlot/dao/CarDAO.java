package com.carlot.dao;

import java.util.List;

import com.carlot.exception.BusinessException;
import com.carlot.model.Car;


public interface CarDAO {
	
	List<Car> getAllCars () throws BusinessException;

	List <Car> getCarsByCustomer (int customerId) throws BusinessException;

	Car getCarById (int id) throws BusinessException;
	
	int createCar (Car car) throws BusinessException;
	int deleteCar(int id) throws BusinessException;
	
	

	
}
