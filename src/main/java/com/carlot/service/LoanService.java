package com.carlot.service;

import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;

public interface LoanService {
	
	int createLoan (Loan loan)  throws BusinessException;
	int calculateLoan (int carId, Double amount, int term, int creditScore) throws BusinessException;
	Loan getLoanById(int carId) throws BusinessException;

}
