package com.carlot.service;

import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;
import com.carlot.model.Payment;

public interface PaymentService {
	
	// Approve one offer, reject another offers for same car, update car status to sold

	public int createLoan (Loan loan)  throws BusinessException;
	// Create payment and update remaining loan
	public int createPayment (Payment payment) throws BusinessException;
}
