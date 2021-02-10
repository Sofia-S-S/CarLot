package com.carlot.service.impl;

import java.util.List;

import com.carlot.dao.OfferDAO;
import com.carlot.dao.impl.OfferDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.model.Offer;
import com.carlot.service.OfferService;

public class OfferServiceImpl implements OfferService {
	
	private OfferDAO dao = new OfferDAOImpl();
	
	@Override
	public int createOffer(Offer offer) throws BusinessException {
		int c = 0;
		if (offer.getAmount()>100) {
			c = dao.createOffer(offer);
		} else {
			throw new BusinessException("Offered amount $" + offer.getAmount() + " is Too Low......");
		}
		return c;

}
	
	@Override
	public List<Offer> getOffersByStatus(String status) throws BusinessException {
		List<Offer> offersList = null;
		if(status.equals("pending") || status.equals("rejected") || status.equals("approved")) {
			offersList = dao.getAllOffers();
			offersList.removeIf((o) -> !o.getStatus().equals(status));
		} else {
			throw new BusinessException("Status " + status + " is INVALID......");
		}
		return offersList;
	}

	@Override
	public List<Offer> getOffersByCarId(int carId) throws BusinessException {
		List <Offer> offersList = null;
		if (carId > 0 && carId < 2147483647) {		
			offersList = dao.getAllOffers();
			offersList.removeIf((o) -> o.getCarId() != carId);
		} else {
			throw new BusinessException("Car id " + carId + " is INVALID......");
		}
		return offersList;
	}

	@Override
	public List<Offer> getOffersByCustomerId(int customerId) throws BusinessException {
		List <Offer> offersList = null;
		if (customerId > 0 && customerId < 2147483647) {		
			offersList = dao.getAllOffers();
			offersList.removeIf((o) -> o.getCustomerId() != customerId);
		} else {
			throw new BusinessException("Customer id " + customerId + " is INVALID......");
		}
		return offersList;
	}

	@Override
	public int rejectOfferById(long offerId) throws BusinessException {
		int x= 0;
		if(offerId>10000L) {
			x = dao.rejectOfferById(offerId);
		} else {
			throw new BusinessException("Offer id " + offerId + " is INVALID......");
		}
		return x;
	}

	@Override
	public Offer getOfferById(long offerId) throws BusinessException {
		Offer offer = null;
		if(offerId>10000L) {
			offer = dao.getOfferById(offerId);
		} else {
			throw new BusinessException("Offer id " + offerId + " is INVALID......");
		}
		return offer;
	}
	@Override
	public int approveOffer(long offerId, int carId) throws BusinessException {
		int a = 0;
		if ((offerId > 10000L)&& carId>0) {
			a = dao.approveOffer(offerId, carId);
			dao.rejectOfferByCarId(carId);
			
		} else {
			throw new BusinessException("Offer id " + offerId + " is INVALID......");
		}
	
		return a;
	}
}
