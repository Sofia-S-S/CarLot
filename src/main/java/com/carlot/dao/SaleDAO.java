package com.carlot.dao;

import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;
import com.carlot.model.Payment;

public interface SaleDAO {
	
	// Approve one offer, reject another offers for same car, update car status to sold
	public void approveOffer (int offerId)  throws BusinessException;
	public int createLoan (Loan loan)  throws BusinessException;
	// Create payment and update remaining loan
	public int createPayment (Payment payment) throws BusinessException;


}
