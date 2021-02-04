package com.carlot.dao;

import java.util.List;

import com.carlot.exception.BusinessException;
import com.carlot.model.Offer;

public interface OfferDAO {
	
	public int createOffer (Offer offer)throws BusinessException;
	public List<Offer> getOffersByStatus (String status) throws BusinessException;
	public List<Offer> getOffersByCarId (int carId) throws BusinessException;
	public List<Offer> getOffersByCustomerId(int customerId) throws BusinessException;
	public int updateOfferStatusForReject (long offerId)  throws BusinessException;
	public int approveOffer (long offerId, int carId)  throws BusinessException;
	public Offer getOfferById (long offerId) throws BusinessException;
}
