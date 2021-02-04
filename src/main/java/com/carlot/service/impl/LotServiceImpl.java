package com.carlot.service.impl;

import java.time.Year;
import java.util.List;

import com.carlot.dao.LotDAO;
import com.carlot.dao.impl.LotDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.model.Car;
import com.carlot.model.Offer;
import com.carlot.service.LotService;

public class LotServiceImpl implements LotService{
	
	private LotDAO dao = new LotDAOImpl();

	@Override
	public List<Car> getCarsByStatus(String status) throws BusinessException {
		List<Car> carsList = null;
		if(status.equals("for sale") || status.equals("sold")) {
			carsList = dao.getCarsByStatus(status);
		} else {
			throw new BusinessException("Status " + status + " is INVALID......");
		}
		return carsList;
	}

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
								if (car.getVin().matches("[a-zA-z0-9]{17}") ) {
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

	@Override
	public List<Offer> getOffersByStatus(String status) throws BusinessException {
		List<Offer> offersList = null;
		if(status.equals("pending") || status.equals("rejected") || status.equals("approved")) {
			offersList = dao.getOffersByStatus(status);
		} else {
			throw new BusinessException("Status " + status + " is INVALID......");
		}
		return offersList;
	}

	@Override
	public List<Offer> getOffersByCarId(int carId) throws BusinessException {
		List <Offer> offersList = null;
		if (carId > 0 && carId < 2147483647) {		
			offersList = dao.getOffersByCarId(carId);
		} else {
			throw new BusinessException("Car id " + carId + " is INVALID......");
		}
		return offersList;
	}

	@Override
	public List<Offer> getOffersByCustomerId(int customerId) throws BusinessException {
		List <Offer> offersList = null;
		if (customerId > 0 && customerId < 2147483647) {		
			offersList = dao.getOffersByCustomerId(customerId);
		} else {
			throw new BusinessException("Customer id " + customerId + " is INVALID......");
		}
		return offersList;
	}

	@Override
	public int updateOfferStatusForReject(long offerId) throws BusinessException {
		int x= 0;
		if(offerId>100000L) {
			x = dao.updateOfferStatusForReject(offerId);
		} else {
			throw new BusinessException("Offer id " + offerId + " is INVALID......");
		}
		return x;
	}

	@Override
	public Offer getOfferById(long offerId) throws BusinessException {
		Offer offer = null;
		if(offerId>100000) {
			offer = dao.getOfferById(offerId);
		} else {
			throw new BusinessException("Offer id " + offerId + " is INVALID......");
		}
		return offer;
	}

}
