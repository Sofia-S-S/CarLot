package com.carlot.dao;

import java.util.List;

import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;
import com.carlot.model.Payment;

public interface PaymentDAO {

	// Approve one offer,  update car status to sold
	int createLoan(Loan loan) throws BusinessException;

	// Create payment and update remaining loan
	int createPayment(Payment payment) throws BusinessException;

	List<Payment> getAllPaynemts() throws BusinessException;

}
