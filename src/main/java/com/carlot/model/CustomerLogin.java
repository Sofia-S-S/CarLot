package com.carlot.model;

public class CustomerLogin {
	
	private int customerId;
	private String login;
	private String password;
	
	public CustomerLogin() {}

	public CustomerLogin(int customerId, String login, String password) {
		super();
		this.customerId = customerId;
		this.login = login;
		this.password = password;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
