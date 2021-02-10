package com.carlot.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.carlot.dao.PaymentDAO;
import com.carlot.dao.impl.PaymentDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;
import com.carlot.model.Payment;
import com.carlot.service.LoanService;
import com.carlot.service.PaymentService;

public class PaymentServiceImpl implements PaymentService{
	
	public static final Logger log = LogManager.getFormatterLogger(PaymentServiceImpl.class); // v2 set up
	
	PaymentDAO dao = new PaymentDAOImpl();
	LoanService loanService = new LoanServiceImpl();

	@Override
	public int createPayment(Payment payment) throws BusinessException {
		int xy = 0;
		// check if load for giving car exists
		Loan loan = loanService.getLoanById(payment.getCarId());
		log.debug("Loan : "+loan);
		if (loan.getMonthlyPayment() !=0 && payment.getAmount()>=loan.getMonthlyPayment()) {
			Double balance = loan.getRemainingBalance() - payment.getAmount();
			xy = dao.createPayment(payment, balance);
		} else {
			throw new BusinessException("Payment amount of $" + payment.getAmount() + " is INVALID......");
		}
		return xy;
	}

	@Override
	public List<Payment> getAllPayments() throws BusinessException {
		List<Payment> paymentsList = null;

			paymentsList = dao.getAllPayments();

		return paymentsList;
	}

	@Override
	public List<Payment> getAllPaymentsByCarId(int carId) throws BusinessException {
		List<Payment> paymentsList = null;

		paymentsList = dao.getAllPayments();
		
		//filter all payment and return only for a car with giving id
		paymentsList.removeIf((c) -> c.getCarId() != carId);
		

	return paymentsList;
	}

}
