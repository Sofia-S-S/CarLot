package com.carlot.service.impl;

import java.text.DecimalFormat;
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
	


	@Override
	public int calculateLoan(int carId, Double amount, int term, int creditScore) throws BusinessException {
		
		double rate = 0;
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
		double interest = amount*rate/100;
	
		double remaining = amount + interest;
		double monthly = remaining/( term * 12);
		DecimalFormat df = new DecimalFormat("#.##");
		double monthlyPayment = Double.parseDouble(df.format(monthly));
		
		log.debug("interest = "+interest);
		log.debug("monthly = "+monthly);
		
		Loan loan = new Loan(carId,amount,rate,term,interest,monthlyPayment,remaining, new Date());
		int c = dao.createLoan(loan);
		return c;
	}



	@Override
	public Loan getLoanById(int carId) throws BusinessException {
		Loan loan = dao.getLoanById(carId);
		return loan;
	}

}
