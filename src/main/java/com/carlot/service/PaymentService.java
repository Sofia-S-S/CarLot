package com.carlot.service;

import java.util.List;

import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;
import com.carlot.model.Payment;

public interface PaymentService {
	
	// Approve one offer, reject another offers for same car, update car status to sold

	int createLoan (Loan loan)  throws BusinessException;
	// Create payment and update remaining loan
	int createPayment (Payment payment) throws BusinessException;
	
	List<Payment> getAllPaynemts() throws BusinessException;
	List<Payment> getAllPaynemtsByCarId(int carId) throws BusinessException;
}
