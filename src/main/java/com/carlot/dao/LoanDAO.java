package com.carlot.dao;

import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;

public interface LoanDAO {
	
	int createLoan(Loan loan) throws BusinessException;
	Loan getLoanById (int carId) throws BusinessException;
}
