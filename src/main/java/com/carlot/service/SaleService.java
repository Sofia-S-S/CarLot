package com.carlot.service;

import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;
import com.carlot.model.Offer;
import com.carlot.model.Payment;

public interface SaleService {
	
	public int approveOffer (int offerId, int carId)  throws BusinessException;
	public int createLoan (Loan loan)  throws BusinessException;
	public int createPayment (Payment payment) throws BusinessException;

	public int createOffer(Offer offer) throws BusinessException;
}
