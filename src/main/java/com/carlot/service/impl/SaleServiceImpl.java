package com.carlot.service.impl;

import com.carlot.dao.SaleDAO;
import com.carlot.dao.impl.SaleDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.model.Loan;
import com.carlot.model.Offer;
import com.carlot.model.Payment;
import com.carlot.service.SaleService;

public class SaleServiceImpl implements SaleService{
	
	SaleDAO dao = new SaleDAOImpl();

	@Override
	public int approveOffer(long offerId, int carId) throws BusinessException {
		int a = 0;
		if ((offerId > 100000L)&& carId>0) {
			a = dao.approveOffer(offerId, carId);
		} else {
			throw new BusinessException("Offer id " + offerId + " is INVALID......");
		}
		return a;
	}

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

	@Override
	public int createOffer(Offer offer) throws BusinessException {
		int c = 0;
		if (offer.getAmount()>100) {
			c = dao.createOffer(offer);
		} else {
			throw new BusinessException("Offered amount $" + offer.getAmount() + " is Too Low......");
		}
		return c;

}}
