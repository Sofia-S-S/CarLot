package com.carlot.model;

import java.util.Date;

public class Offer {
	

	private long offerId;
	private int carId;
	private double amount;
	private String status;
	private int customerId;
	private Date date;
	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Offer(long offerId, int carId, double amount, String status, int customerId, Date date) {
		super();
		this.offerId = offerId;
		this.carId = carId;
		this.amount = amount;
		this.status = status;
		this.customerId = customerId;
		this.date = date;
	}
	public Offer(int carId, double amount, String status, int customerId, Date date) {
		super();
		this.carId = carId;
		this.amount = amount;
		this.status = status;
		this.customerId = customerId;
		this.date = date;
	}
	public long getOfferId() {
		return offerId;
	}
	public void setOfferId(long offerId) {
		this.offerId = offerId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + carId;
		result = prime * result + customerId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (offerId ^ (offerId >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (carId != other.carId)
			return false;
		if (customerId != other.customerId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (offerId != other.offerId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "offer id : " + offerId + " | car id : " + carId + " | amount : " + amount + " | status : " + status
				+ " | customer id : " + customerId + " | date " + date;
	}

}
