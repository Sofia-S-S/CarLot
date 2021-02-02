package com.carlot;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.carlot.exception.BusinessException;
import com.carlot.model.CustomerLogin;
import com.carlot.model.Offer;
import com.carlot.service.LoginService;
import com.carlot.service.LotService;
import com.carlot.service.SaleService;
import com.carlot.service.impl.LoginServiceImpl;
import com.carlot.service.impl.LotServiceImpl;
import com.carlot.service.impl.SaleServiceImpl;

public class Driver {
	
	private static Logger log=Logger.getLogger(Driver.class);  // Set up log

	public static void main(String[] args) throws BusinessException, ParseException {
		
		Scanner sc = new Scanner(System.in);

		int ch = 0;

		LoginService loginService = new LoginServiceImpl();
		LotService lotService = new LotServiceImpl();
		SaleService saleService = new SaleServiceImpl();
		
		do {
		
			log.info("");
			log.info("==============================");
			log.info("    WELCOME TO CAR LOT");
			log.info("==============================");
			log.info("  (1) Log in to my account");
			log.info("  (2) New Customer");
			log.info("  (3) Employee log in");
			
			try {
				ch = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
			}

			switch (ch) {
//----------------------------------------------------------------------------------		
//--1------------------------------------Customer Log In -------------------------------------
			case 1:
				
				int cLog = 0;
				do {
					log.info("Enter your LOGIN");
					String login = sc.nextLine();	
					log.info("Enter your PASSWORD");
					String password = sc.nextLine();	
				
					try {
					CustomerLogin customerLogin = loginService.letCustomerLogin(login, password);
					
					int customerId = customerLogin.getCustomerId();
					
					if (customerLogin !=null) {
						int cMenu = 0;
						do {
							
							log.info("");
							log.info("==============================");
							log.info("    CUSTOMER MENU");
							log.info("==============================");
							log.info("  (1) Cars for Sale");
							log.info("  (2) New Offer");
							log.info("  (3) My Offers");
							log.info("  (4) My cars");
							log.info("  (5) Payments");
							log.info("  (6) Exit");
							

							try {
								cMenu = Integer.parseInt(sc.nextLine());
							} catch (NumberFormatException e) {
							}
							switch (cMenu) {
		//--1.1------------------------------------ Cars for Sale -------------------------------------
							
							case 1:
								try {
									lotService.getCarsByStatus("for sale");
								}catch (BusinessException e) {
									log.info(e.getMessage());
								}
								break;	
								
		//--1.2------------------------------------ Do new offer by car id -------------------------------------
							case 2:
								log.info("Enter car id");
								int carId = Integer.parseInt(sc.nextLine());
								log.info("Enter amount in $");
								double amount = Double.parseDouble(sc.nextLine());
//								Generate id
								long offerId = 100000L;

								Offer offer = new Offer(offerId, carId, amount,"pending", customerId, new Date());
								try {
									saleService.createOffer(offer);
								}catch (BusinessException e) {
									log.info(e.getMessage());
								}

								break;	
		//--1.3------------------------------------ See my offers -------------------------------------
							case 3:
								try {
									List<Offer> offersList = lotService.getOffersByCustomerId(customerId);
									if (offersList !=null) {
										log.info("Your offers :");
										for (Offer o : offersList) {
										log.info(o);
										}
										
									}
								}catch (BusinessException e) {
									log.info(e.getMessage());
								}
								break;	
		//--1.4------------------------------------ My cars and balance -------------------------------------
							case 4:

								break;	
		//--1.5------------------------------------ Payments -------------------------------------
							case 5:

								break;	
			//--1.5.1------------------------------------ Pay for my car -------------------------------------
				//--1.5.1.1------------------------------------ Pay for another car -------------------------------------
				//--1.5.1.2------------------------------------ Exit -------------------------------------
			//--1.5.2------------------------------------ Payments history -------------------------------------
							} //Switch
						} while (cMenu != 6);
						break;	
					} //if (c !=null)
					} catch (BusinessException e) {
						log.info(e.getMessage());
					}


			
				} while (cLog != 1); //customer login
				break;	

//----------------------------------------------------------------------------------
//--2------------------------------------New Customer Registration Page-----------------------	
//			case 2:
//				int x = 0;
//				while (x==0) {
//				try {	
//					log.info("Enter your FIRST NAME");
//					String firstName=sc.nextLine();
//					log.info("Enter your LAST NAME");
//					String lastName=sc.nextLine();
//					
//					// Date formatting
//					log.info("Enter your DOB in format yyyy-dd-mm");
//					String s = sc.nextLine();
//			    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
//			    	sdf.setLenient(false);
//			    	Date dob = null;
//			    	try {
//			    		dob = sdf.parse(s);
//			    	}catch (ParseException e1){
//			    		log.info("Invalid date format");
//			    	}
//					log.info("Enter your DL");
//					String dl = sc.nextLine();	
//					log.info("Enter your SSN (9 digits without space)");
//					Long ssn = Long.parseLong(sc.nextLine());
//					log.info("Enter your ADDRESS");
//					String address = sc.nextLine();	
//					log.info("Enter your PHONE number(10 digits without space)");
//					Long contact = Long.parseLong(sc.nextLine());
//					
//					//Generate id
//					int id = 10;
//					
//					//Create new Customer
//					Customer customer = new Customer (id, firstName, lastName, dob, dl, ssn, contact, address);
//					
//					log.info("Create new 4 to 12 long LOGIN");
//					log.info("Numbers or letters only");
//					String login=sc.nextLine();
//					log.info("Create new 4 to 12 long PASSWORD");
//					log.info("Numbers or letters only");
//					String password=sc.nextLine();
//					
//					//Create new Login
//					CustomerLogin customerLogin = new CustomerLogin(id,login,password);
//					
//					if(loginService.createCustomerAndLogin(customer, customerLogin) ==2) {
//						log.info("Registration completed successfully. Now you can log in");
//					}
//				} catch (BusinessException e) {
//					log.info(e.getMessage());
//				} catch (NumberFormatException e) {
//					log.info("Number is invalid");
//					log.info("");
//				}} x++;
//				break;		
//----------------------------------------------------------------------------------
//--3------------------------------------Employee Log In -------------------------------------	
		
		//--3.1------------------------------------ View Cars -------------------------------------
			//--3.1.1------------------------------------ For Sale -------------------------------------
			//--3.1.2------------------------------------ Sold Cars -------------------------------------
			//--3.1.3------------------------------------ Find a single Car -------------------------------------
		
		//--3.2------------------------------------ Add new Car to the Lot -------------------------------------
		
		//--3.3------------------------------------ Find a Car in the Lot -------------------------------------
			//--3.3.1------------------------------------ Remove a Car from the Lot -------------------------------------
		
		//--3.4------------------------------------ View offers  -------------------------------------
			//--3.4.1------------------------------------ All offers -------------------------------------
			//--3.4.2------------------------------------ Pending offers sorted by Cars -------------------------------------
			//--3.4.3------------------------------------ Rejected offers sorted by Cars -------------------------------------
			//--3.4.4------------------------------------ all offers for a single Cars -------------------------------------
		
		//--3.5------------------------------------ Approve/Reject an offer by ID -------------------------------------
		
		//--3.6------------------------------------ View customers -------------------------------------
			//--3.6.1------------------------------------ View all customers -------------------------------------
			//--3.6.2------------------------------------ Find a customer and cars they own and pending offers---------------------------
		
		//--3.7------------------------------------ View Payments -------------------------------------
			//--3.7.1------------------------------------ View All Payments -------------------------------------
			//--3.7.2------------------------------------ View Payments for a Car -------------------------------------




			default:
				log.info("Invalid menu option.Please try again!");
				break;
			} //switch ch
		} while (ch != 4); // main menu do

		sc.close(); // avoiding resource leak
	} //main

} //Driver		
