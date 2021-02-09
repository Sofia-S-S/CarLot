package com.carlot.dao;

import java.util.List;

import com.carlot.exception.BusinessException;
import com.carlot.model.Payment;

public interface PaymentDAO {

	// Create payment and update remaining loan
	int createPayment(Payment payment) throws BusinessException;

	List<Payment> getAllPaynemts() throws BusinessException;

}
