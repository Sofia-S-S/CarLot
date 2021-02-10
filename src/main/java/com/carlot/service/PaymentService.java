package com.carlot.service;

import java.util.List;

import com.carlot.exception.BusinessException;

import com.carlot.model.Payment;

public interface PaymentService {

	// Create payment and update remaining loan
	int createPayment (Payment payment) throws BusinessException;
	
	List<Payment> getAllPayments() throws BusinessException;
	List<Payment> getAllPaymentsByCarId(int carId) throws BusinessException;
}
