package com.carlot.service.impl;

import com.carlot.dao.PaymentDAO;
import com.carlot.dao.impl.PaymentDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;
import com.carlot.model.Payment;
import com.carlot.service.PaymentService;

public class PaymentServiceImpl implements PaymentService{
	
	PaymentDAO dao = new PaymentDAOImpl();



	@Override
	public int createLoan(Loan loan) throws BusinessException {
		int c = 0;

			c = dao.createLoan(loan);

		return c;
	}

	@Override
	public int createPayment(Payment payment) throws BusinessException {
		int c = 0;
		if (payment.getAmount()>0) {
			c = dao.createPayment(payment);
		} else {
			throw new BusinessException("Payment amount of $" + payment.getAmount() + " is INVALID......");
		}
		return c;
	}

}
