package com.carlot.service.impl;

import java.time.Year;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.carlot.dao.CarDAO;
import com.carlot.dao.impl.CarDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.model.Car;
import com.carlot.service.CarService;

public class CarServiceImpl implements CarService{
	
	private CarDAO dao = new CarDAOImpl();
	
	public static final Logger log = LogManager.getFormatterLogger(CarServiceImpl.class); // v2 set up


//--------------------------------------------CREATE------------------------------------------------------------------
	@Override
	public int createCar(Car car) throws BusinessException {
		int c = 0;
		if (car.getBody().matches("[a-zA-Z]{4,12}")) {
			if (car.getMake().matches("[a-zA-Z]{4,12}")) {
				if (car.getModel().matches("[a-zA-Z]{4,12}")) {
					//Check if a car was made between 1886 and current year
					if (car.getYear()>1886 && car.getYear()< Year.now().getValue()) {
						if (car.getColor().matches("[a-zA-Z]{4,12}")) {
							if (car.getMileage()>=0 && car.getMileage()< 3000000f ) {
								if (car.getVin().matches("[a-zA-Z0-9]{17}") ) {
									c = dao.createCar(car);
								}  else {
									throw new BusinessException("VIN number" + car.getVin() + " is INVALID");
								}
							}  else {
								throw new BusinessException("Mileage " + car.getMileage() + " is INVALID");
							}
						}  else {
							throw new BusinessException("Color " + car.getColor() + " is INVALID");
						}
					}  else {
						throw new BusinessException("Year " + car.getYear() + " is INVALID");
					}
				}  else {
					throw new BusinessException("Model " + car.getModel() + " is INVALID");
				}
			}  else {
				throw new BusinessException("Make " + car.getMake() + " is INVALID");
			}
		}  else {
			throw new BusinessException("Body " + car.getBody() + " is INVALID");
		}

		return c;
	}

	//---------------------------------------------------DELETE------------------------------------------------
	@Override
	public int deleteCar(int id) throws BusinessException {
		int d = 0;
		if (id > 0 && id < 2147483647) {
			d = dao.deleteCar(id);
		} else {
			throw new BusinessException("Car id " + id + " is INVALID......");
		}
		return d;
	}

	//-------------------------------------------FIND ONE---------------------------------------------------------------
	@Override
	public Car getCarById(int id) throws BusinessException {
		Car car = null;
		if (id > 0 && id < 2147483647) {
			car = dao.getCarById(id);
		} else {
			throw new BusinessException("Car id " + id + " is INVALID......");
		}
		return car;
	}
	
	//---------------------------------------LISTS-------------------------------------------------------------------------------
	@Override
	public List<Car> getCarsByCustomer(int customerId) throws BusinessException {
		List<Car> carsList = null;
		if(customerId> 9 && customerId < 2147483647) {
		carsList = dao.getCarsByCustomer(customerId);
		} else {
			throw new BusinessException("Customer id " + customerId + " is INVALID......");
		}
		return carsList;
	}
	
	@Override
	public List<Car> getCarsByStatus(String status) throws BusinessException {
		List<Car> carsList = null;
		if(status.equals("for sale") || status.equals("sold")) {
			carsList = dao.getAllCars();
			carsList.removeIf((c) -> !c.getStatus().equals(status));		
		
		} else {
			throw new BusinessException("Status " + status + " is INVALID......");
		}
		return carsList;
	}


	

}
