package com.carlot.model;

import java.util.Date;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private Date dob;
	private String dl;
	private long ssn;
	private long contact;
	private String address;
	
	public Customer() {}
	
	public Customer(int id, String firstName, String lastName, Date dob, String dl, long ssn, long contact,
			String address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.dl = dl;
		this.ssn = ssn;
		this.contact = contact;
		this.address = address;
	}
	
	//Constructor without id
	public Customer(String firstName, String lastName, Date dob, String dl, long ssn, long contact,
			String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.dl = dl;
		this.ssn = ssn;
		this.contact = contact;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getDl() {
		return dl;
	}
	public void setDl(String dl) {
		this.dl = dl;
	}
	public long getSsn() {
		return ssn;
	}
	public void setSsn(long ssn) {
		this.ssn = ssn;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", dl="
				+ dl + ", ssn=" + ssn + ", contact=" + contact + ", address=" + address + "]";
	}

	
}
