package com.carlot.dao;

import java.util.List;

import com.carlot.exception.BusinessException;
import com.carlot.model.Car;


public interface CarDAO {
	
	public List<Car> getCarsByStatus (String status) throws BusinessException;

	public Car getCarById (int id) throws BusinessException;
	
	public int createCar (Car car) throws BusinessException;
	public int deleteCar(int id) throws BusinessException;
	
	

	
}
