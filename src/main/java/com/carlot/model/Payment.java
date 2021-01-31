package com.carlot.model;

import java.util.Date;

public class Payment {
	
	private int carId;
	private double amount;
	private Date date;
	
	public Payment() {}

	public Payment(int carId, double amount, Date date) {
		super();
		this.carId = carId;
		this.amount = amount;
		this.date = date;
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
		return "Payment [carId=" + carId + ", amount=" + amount + ", date=" + date + "]";
	}
	
	
	
	

}
