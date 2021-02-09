package com.carlot.model;

import java.util.Date;

public class Payment {
	
	private long paymentId;
	private int carId;
	private double amount;
	private Date date;
	
	public Payment() {}

	public Payment(long paymentId, int carId, double amount, Date date) {
		super();
		this.paymentId = paymentId;
		this.carId = carId;
		this.amount = amount;
		this.date = date;
	}
	public Payment(int carId, double amount, Date date) {
		super();
		this.carId = carId;
		this.amount = amount;
		this.date = date;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "payment id : " + paymentId + " | car id : " + carId + " | amount : " + amount + " | date | " + date;
	}




	
	

}
