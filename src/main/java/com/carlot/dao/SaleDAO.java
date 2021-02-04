package com.carlot.dao;

import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;
import com.carlot.model.Offer;
import com.carlot.model.Payment;

public interface SaleDAO {
	
	public int createOffer (Offer offer)throws BusinessException;
	
	// Approve one offer, reject another offers for same car, update car status to sold
	public int approveOffer (long offerId, int carId)  throws BusinessException;
	public int createLoan (Loan loan)  throws BusinessException;
	// Create payment and update remaining loan
	public int createPayment (Payment payment) throws BusinessException;


}
