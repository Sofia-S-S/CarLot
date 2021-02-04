package com.carlot.service;

import java.util.List;

import com.carlot.exception.BusinessException;
import com.carlot.model.Offer;

public interface OfferService {
	
	int createOffer (Offer offer)throws BusinessException;
	List<Offer> getOffersByStatus (String status) throws BusinessException;
	List<Offer> getOffersByCarId (int carId) throws BusinessException;
	List<Offer> getOffersByCustomerId(int customerId) throws BusinessException;
	int updateOfferStatusForReject (long offerId)  throws BusinessException;
	int approveOffer (long offerId, int carId)  throws BusinessException;
	Offer getOfferById (long offerId) throws BusinessException;

}
