package com.carlot.model;

import java.util.Date;

public class Loan {
	
	private int carId;
	private Date saleDate;
	private double amount;
	private int months;
	private double monthlyPayment;
	private double remainingBalance;
	
	public Loan() {}
	
	public Loan(int carId, Date saleDate, double amount, int months, double monthlyPayment, double remainingBalance) {
		super();
		this.carId = carId;
		this.saleDate = saleDate;
		this.amount = amount;
		this.months = months;
		this.monthlyPayment = monthlyPayment;
		this.remainingBalance = remainingBalance;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public double getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}
	@Override
	public String toString() {
		return "Loan [carId=" + carId + ", saleDate=" + saleDate + ", amount=" + amount + ", months=" + months
				+ ", monthlyPayment=" + monthlyPayment + ", RemainingBalance=" + remainingBalance + "]";
	}
	

}
