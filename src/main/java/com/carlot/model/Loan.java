package com.carlot.model;

import java.util.Date;

public class Loan {
	
	private int carId;
	private double amount;
	private double rate;
	private int term;
	private double interest;
	private double monthlyPayment;
	private double remainingBalance;
	private Date saleDate;
	
	public Loan() {}

	public Loan(int carId, double amount, double rate, int term, double interest, double monthlyPayment,
			double remainingBalance, Date saleDate) {
		super();
		this.carId = carId;
		this.amount = amount;
		this.rate = rate;
		this.term = term;
		this.interest = interest;
		this.monthlyPayment = monthlyPayment;
		this.remainingBalance = remainingBalance;
		this.saleDate = saleDate;
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
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

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	@Override
	public String toString() {
		return "car id : " + carId + " | amount : $" + amount + " | rate : " + rate + "% | term " + term + "years | interest : $"
				+ interest + " | monthly payment : $" + monthlyPayment + " | remaining balance : $" + remainingBalance
				+ " | sale date : " + saleDate;
	}

}
