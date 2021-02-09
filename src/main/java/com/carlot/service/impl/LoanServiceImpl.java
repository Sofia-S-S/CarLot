package com.carlot.service.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.carlot.dao.LoanDAO;
import com.carlot.dao.impl.LoanDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;
import com.carlot.service.LoanService;

public class LoanServiceImpl implements LoanService  {
	
	public static final Logger log = LogManager.getFormatterLogger(LoanServiceImpl.class); // v2 set up
	
	LoanDAO dao = new LoanDAOImpl();

	@Override
	public int createLoan(Loan loan) throws BusinessException {
		int c = 0;

			c = dao.createLoan(loan);

		return c;
	}
	
//	Rates by Credit Score for 48 months: 
//	781-850: 3.80% APR
//	661-780: 5.48% APR
//	601-660: 10.10% APR
//	501-600: 16.27% APR
//	300-500: 19.32% APR

	@Override
	public int calculateLoan(int carId, Double amount, int term, int creditScore) throws BusinessException {
		
		Double rate = null;
		if (creditScore >= 780) {
			 rate = 3.80;
		} else if (creditScore >= 660 && creditScore < 780) {
			 rate = 5.48;
		} else if (creditScore >= 600 && creditScore < 660) {
			 rate = 10.10;
		} else if (creditScore >= 500 && creditScore < 600) {
			 rate = 16.27;
		} else if (creditScore >= 300 && creditScore < 600) {
			 rate = 19.32;
		} else {log.info("Creadit Score is too loo for loan");
		}
		Double interest = amount*rate/100;
	
		Double remaining = amount + interest;
		Double monthly = remaining/( term * 12);
		
		log.debug("interest = "+interest);
		log.debug("monthly = "+monthly);
		
		Loan loan = new Loan(carId,amount,rate,term,interest,monthly,remaining, new Date());
		int c = dao.createLoan(loan);
		return c;
	}

}
