package com.carlot.service.impl;


import com.carlot.dao.LoginDAO;
import com.carlot.dao.impl.LoginDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.model.Customer;
import com.carlot.model.CustomerLogin;
import com.carlot.model.EmployeeLogin;
import com.carlot.service.LoginService;

public class LoginServiceImpl implements LoginService{
	
	private LoginDAO dao = new LoginDAOImpl();


	@Override
	public CustomerLogin letCustomerLogin(String login, String password) throws BusinessException {
		CustomerLogin customerLogin = null;
		if(login!=null && login.matches("[a-zA-Z0-9]{4,12}")&& password!=null && password.matches("[a-zA-Z0-9]{4,12}")) {
			customerLogin = dao.letCustomerLogin(login, password);
		} else {
				throw new BusinessException("Entered loging or password is INVALID. Please try again");
			}
		return customerLogin;
	}

	@Override
	public EmployeeLogin letEmployeeLogin(String employeeId, String password) throws BusinessException {
		EmployeeLogin employeeLogin =null;
		if(employeeId!=null && employeeId.matches("[a-zA-Z]{2}[0-9]{4}")&& password!=null && password.matches("[a-zA-Z0-9]{4,12}")) {

			employeeLogin =dao.letEmployeeLogin(employeeId, password);
		}else {
			throw new BusinessException("Entered loging or password is INVALID. Please try again");
		}
		return employeeLogin;
	}

	@Override
	public int createCustomerAndLogin(Customer customer, CustomerLogin customerLogin) throws BusinessException {
		int c = 0;
		if (customer.getFirstName().matches("[a-zA-Z]{4,12}")) {
			if (customer.getLastName().matches("[a-zA-Z]{4,12}")) {
//				Check age
				

				
//				if (customer.getDob().getYear()>21) {
					if (customer.getDl().matches("[a-zA-z0-9]{6,17}")) {
						if (customer.getSsn()> 100000000L && customer.getSsn()< 999999999L ) {
							if (customer.getContact()>1000000000L && customer.getContact()<9999999999L) {
								if (customer.getAddress()!=null) {
									if (customerLogin.getLogin().matches("[a-zA-z0-9]{4,12}")) {
										if (customerLogin.getPassword().matches("[a-zA-z0-9]{4,12}")) {

											c = dao.createCustomerAndLogin(customer, customerLogin);
										}  else {
											throw new BusinessException("Password " + customerLogin.getPassword() + " is INVALID");
										}
									}  else {
										throw new BusinessException("Login " + customerLogin.getLogin() + " is INVALID");
									}
								}  else {
									throw new BusinessException("Adress " + customer.getAddress() + " is INVALID");
								}
							}  else {
								throw new BusinessException("Phone number " + customer.getContact() + " is INVALID");
							}
						}  else {
							throw new BusinessException("Social security number " + customer.getSsn() + " is INVALID");
						}
					}  else {
						throw new BusinessException("DL " + customer.getDl() + " is INVALID");
					}
//				}  else {
//					throw new BusinessException("Day of Birth " + customer.getDob() + " is INVALID");
//				}
			}  else {
				throw new BusinessException("Lust name " + customer.getLastName() + " is INVALID");
			}
		}  else {
			throw new BusinessException("Name " + customer.getFirstName() + " is INVALID");
		}

		return c;
	}

}
