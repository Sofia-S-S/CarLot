package com.carlot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager; // make sure to import from --.logging.-- for version two
import org.apache.logging.log4j.Logger;

import com.carlot.exception.BusinessException;
import com.carlot.model.Car;
import com.carlot.model.Customer;
import com.carlot.model.CustomerLogin;
import com.carlot.model.EmployeeLogin;
import com.carlot.model.Offer;
import com.carlot.model.Payment;
import com.carlot.service.CarService;
import com.carlot.service.LoginService;
import com.carlot.service.OfferService;
import com.carlot.service.PaymentService;
import com.carlot.service.impl.AgeCulculator;
import com.carlot.service.impl.CarServiceImpl;
import com.carlot.service.impl.LoginServiceImpl;
import com.carlot.service.impl.OfferServiceImpl;
import com.carlot.service.impl.PaymentServiceImpl;

public class Driver {

	public static final Logger log = LogManager.getFormatterLogger(Driver.class); // v2 set up

	public static void main(String[] args) throws BusinessException, ParseException {

		Scanner sc = new Scanner(System.in);

		int ch = 0;

		LoginService loginService = new LoginServiceImpl();
		CarService carService = new CarServiceImpl();
		OfferService offerService = new OfferServiceImpl();
		PaymentService payService = new PaymentServiceImpl();
		AgeCulculator ag = new AgeCulculator();

		do {

			log.info("");
			log.info("==============================");
			log.info("    WELCOME TO CAR LOT");
			log.info("==============================");
			log.info("  (1) Log in to my account");
			log.info("  (2) New Customer");
			log.info("  (3) Employee log in");
			log.info("  (4) Exit");

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

						if (customerLogin != null) {
							int cMenu = 0;
							do {

								log.info("");
								log.info("==============================");
								log.info("    CUSTOMER MENU");
								log.info("==============================");
								log.info("  (1) Cars");
								log.info("  (2) Offers");
								log.info("  (3) Payments");
								log.info("  (4) Log out");

								try {
									cMenu = Integer.parseInt(sc.nextLine());
								} catch (NumberFormatException e) {
								}
								switch (cMenu) {
								// --1.1------------------------------------ Cars
								// -------------------------------------

								case 1:
									int cMenuCar = 0;
									do {

										log.info("");
										log.info("==============================");
										log.info("    CARS");
										log.info("==============================");
										log.info("  (1) Cars for Sale");
										log.info("  (2) My cars");
										log.info("  (3) Exit");

										try {
											cMenuCar = Integer.parseInt(sc.nextLine());
										} catch (NumberFormatException e) {
										}
										switch (cMenuCar) {

										// --1.1.1------------------------------------ Cars for Sale
										// -------------------------------------
										case 1:
											try {
												List<Car> cars = carService.getCarsByStatus("for sale");
												if (cars != null) {
													for (Car c : cars) {
														log.info(c);
													}
												}
											} catch (BusinessException e) {
												log.info(e.getMessage());
											}
											break;
										// --1.1.2------------------------------------ My cars and balance
										// -------------------------------------
										case 2:

											// --1.1.3------------------------------ Exit Cars
											// -----------------------------------------
											break;
										default:
											log.info("Invalid menu option.Please try again!");
											log.debug("DD113");
											break;
										} // Switch
									} while (cMenuCar != 3);
									break;

								// --1.2------------------------------------ Offers
								// -------------------------------------
								case 2:
									int cMenuOffer = 0;
									do {

										log.info("");
										log.info("==============================");
										log.info("    OFFERS");
										log.info("==============================");
										log.info("  (1) New Offer");
										log.info("  (2) My offers");
										log.info("  (3) Exit");

										try {
											cMenuOffer = Integer.parseInt(sc.nextLine());
										} catch (NumberFormatException e) {
										}
										switch (cMenuOffer) {
										// --1.2.1------------------------------------ Make new offer by car id
										// -------------------------------------
										case 1:

											try {
												log.info("Enter car id");
												int carId = Integer.parseInt(sc.nextLine());
												log.info("Enter amount in $");
												double amount = Double.parseDouble(sc.nextLine());

												Offer offer = new Offer(carId, amount, "pending", customerId,
														new Date());
												if (offerService.createOffer(offer) != 0) {
													log.info("CarLot received your offer");
												}
											} catch (BusinessException e) {
												log.info(e.getMessage());
											}

											break;
										// --1.2.2------------------------------------ See my offers
										// -------------------------------------
										case 2:
											try {
												List<Offer> offersList = offerService.getOffersByCustomerId(customerId);
												if (offersList != null) {
													log.info("Your offers :");
													for (Offer o : offersList) {
														log.info(o);
													}
												}
											} catch (BusinessException e) {
												log.info(e.getMessage());
											}
											break;
										// --1.2.3------------------------------ Exit Offers
										// -----------------------------------------

										default:
											log.info("Invalid menu option.Please try again!");
											log.debug("DD123");
											break;
										}
									} while (cMenuOffer != 3);
									break;
								// --1.3------------------------------------ Payments
								// -------------------------------------
								case 3:
									int payMenu = 0;
									do {
										log.info("");
										log.info("==============================");
										log.info("    PAYMENTS MENU");
										log.info("==============================");
										log.info("  (1) New payment");
										log.info("  (2) Payment History");
										log.info("  (3) Exit");

										try {
											payMenu = Integer.parseInt(sc.nextLine());
										} catch (NumberFormatException e) {
										}

										switch (payMenu) {

										// --1.3.1------------------------------------ Pay for my car
										// -------------------------------------
										case 1:

											try {
												log.info("Enter your car id");
												int carId = Integer.parseInt(sc.nextLine());

												log.info("Enter payment amount in $");
												double amount = Double.parseDouble(sc.nextLine());

												Payment payment = new Payment(carId, amount, new Date());
												int p = payService.createPayment(payment);
												if (p != 0) {
													log.info("You succsessfully payed $" + amount + " for car with id "
															+ carId);
												}
											} catch (NumberFormatException e) {

											} catch (BusinessException e) {
												log.info(e.getMessage());
											}

											break;

										// --1.3.2------------------------------------ Payments history
										// -------------------------------------

										case 2:

//										try {
//
//
//											int p = 0;
//											if (p !=0) {
//												log.info("Payment history: ");
//											}
//										}catch (BusinessException e) {
//											log.info(e.getMessage());
//										} catch (NumberFormatException e) {
//											log.info("Wrong format");
//										}

											break;

										// --1.3.3----------------------------------------- Exit
										// Payments--------------------------------------------------------
										default:
											log.info("Invalid menu option.Please try again!");
											log.debug("DD133");
											break;
										}
									} while (payMenu != 3);
									break;

								// --1.3.3------------------------------- Exit Customer menu (log
								// out)------------------------------

								}
							} while (cMenu != 4);
							break;
						} // if (c !=null)
					} catch (BusinessException e) {
						log.info(e.getMessage());
					}
					break;
				} while (cLog != 1); // customer login
				break;

//----------------------------------------------------------------------------------
//--2------------------------------------New Customer Registration Page-----------------------	
			case 2:
				int x = 0;
				do {
					try {
						log.info("Enter your FIRST NAME");
						String firstName = sc.nextLine();
						log.info("Enter your LAST NAME");
						String lastName = sc.nextLine();

						// Date formatting
						log.info("Enter your DOB in format yyyy-dd-mm");
						String s = sc.nextLine();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
						sdf.setLenient(false);
						Date dob = null;
						try {
							dob = sdf.parse(s);
						} catch (ParseException e1) {
							log.info("Invalid date format");
							break;
						}
						// Validate age

						log.info("Enter your DL");
						String dl = sc.nextLine();
						log.info("Enter your SSN (9 digits without space)");
						Long ssn = Long.parseLong(sc.nextLine());
						log.info("Enter your ADDRESS");
						String address = sc.nextLine();
						log.info("Enter your PHONE number(10 digits without space)");
						Long contact = Long.parseLong(sc.nextLine());

						// Create new Customer
						Customer customer = new Customer(firstName, lastName, dob, dl, ssn, contact, address);

						log.info("Create new 4 to 12 long LOGIN");
						log.info("Numbers or letters only");
						String login = sc.nextLine();
						log.info("Create new 4 to 12 long PASSWORD");
						log.info("Numbers or letters only");
						String password = sc.nextLine();

						// Create new Login
						CustomerLogin customerLogin = new CustomerLogin(login, password);

						if (loginService.createCustomerAndLogin(customer, customerLogin) == 2) {
							log.info("Registration completed successfully. Now you can log in");
						}
					} catch (BusinessException e) {
						log.info(e.getMessage());

					} catch (NumberFormatException e) {
						log.info("Number is invalid");
						log.info("");
						break;
					}
					break;
				} while (x != 1);
				break;
//----------------------------------------------------------------------------------
//--3------------------------------------Employee Log In -------------------------------------	
			case 3:

				int eLog = 0;
				do {
					log.info("Enter your EMPLOYEE ID");
					String login = sc.nextLine();
					log.info("Enter your PASSWORD");
					String password = sc.nextLine();

					try {
						EmployeeLogin employee = loginService.letEmployeeLogin(login, password);

						if (employee != null) {
							int eMenu = 0;
							do {

								log.info("");
								log.info("===================================");
								log.info(" EMPLOYEE MENU FOR " + employee.getName());
								log.info("===================================");
								log.info("  (1) Cars");
								log.info("  (2) Offers");
								log.info("  (3) View customers");
								log.info("  (4) View Payments");
								log.info("  (5) Log out");

								try {
									eMenu = Integer.parseInt(sc.nextLine());
								} catch (NumberFormatException e) {
								}
								switch (eMenu) {

								// --3.1------------------------------------ View Cars
								// -------------------------------------
								case 1:
									int eMenuCar = 0;

									do {
										log.info("");
										log.info("==============================");
										log.info("    VIEW CARS");
										log.info("==============================");
										log.info("  (1) Search for a Car");
										log.info("  (2) Cars for Sale");
										log.info("  (3) Sold Cars");
										log.info("  (4) Add new Car");
										log.info("  (5) Delete a Car");
										log.info("  (6) Exit");

										try {
											eMenuCar = Integer.parseInt(sc.nextLine());
										} catch (NumberFormatException e) {
										}

										switch (eMenuCar) {
										// --3.1.1------------------------------------ Find car by
										// Id------------------------------------
										case 1:
											try {
												log.info("Enter a car ID");
												int id = Integer.parseInt(sc.nextLine());

												Car car = carService.getCarById(id);
												if (car != null) {

													log.info(car);

												}
											} catch (BusinessException e) {
												log.info(e.getMessage());
											}

											break;

										// --3.1.2------------------------------------ For Sale
										// -------------------------------------
										case 2:
											try {
												List<Car> cars = carService.getCarsByStatus("for sale");
												if (cars != null) {
													for (Car c : cars) {
														log.info(c);
													}
												}
											} catch (BusinessException e) {
												log.info(e.getMessage());
											}

											break;
										// --3.1.3------------------------------------ Sold Cars
										// -------------------------------------
										case 3:
											try {
												List<Car> cars = carService.getCarsByStatus("sold");
												if (cars != null) {
													for (Car c : cars) {
														log.info(c);
													}
												}
											} catch (BusinessException e) {
												log.info(e.getMessage());
											}
											break;

										// --3.1.4------------------------------------ Add new Car to the Lot
										// -------------------------------------
										case 4:

											try {
												log.info("Enter BODY TYPE");
												String body = sc.nextLine();
												log.info("Enter MAKE");
												String make = sc.nextLine();
												log.info("Enter MODEL");
												String model = sc.nextLine();
												log.info("Enter YEAR");
												int year = Integer.parseInt(sc.nextLine());
												log.info("Enter COLOR");
												String color = sc.nextLine();
												log.info("Enter MILEAGE)");
												float mileage = Float.parseFloat(sc.nextLine());
												log.info("Enter VIN");
												String vin = sc.nextLine();

												Car car = new Car(body, make, model, year, color, mileage, vin,
														"for sale");

												if (carService.createCar(car) != 0) {
													log.info("A car has been added successfully");
												}
												;

											} catch (NumberFormatException e) {

											} catch (BusinessException e) {
												log.info(e.getMessage());
											}
											break;

										// --3.1.5------------------------------------ Remove a Car from the Lot
										// -------------------------------------
										case 5:
											try {
												log.info("Enter a Car ID");
												int id = Integer.parseInt(sc.nextLine());

												if (carService.deleteCar(id) != 0) {

													log.info("Car deleted successfully");

												}
											} catch (NumberFormatException e) {
											} catch (BusinessException e) {
												log.info(e.getMessage());
											}

											break;
										// --3.1.6------------------------------------Exit
										// Cars--------------------------------

										default:
											log.info("Invalid menu option.Please try again!");
											log.debug("DD316");
											break;

										}
									} while (eMenuCar != 3);

									break;

								// --3.2------------------------------------ View offers
								// -------------------------------------
								case 2:
									int eMenuOffer = 0;

									do {
										log.info("");
										log.info("==============================");
										log.info("    OFFERS");
										log.info("==============================");
										log.info("  (1) All Offers");
										log.info("  (2) Find an Offer");
										log.info("  (3) Exit");

										try {
											eMenuOffer = Integer.parseInt(sc.nextLine());
										} catch (NumberFormatException e) {
										}

										switch (eMenuOffer) {
										// --3.2.1------------------------------------ All offers
										// -------------------------------------
										case 1:

											int allOffers = 0;
											do {
												log.info("");
												log.info("==============================");
												log.info("    ALL OFFERS");
												log.info("==============================");
												log.info("  (1) All Pending Offers");
												log.info("  (2) All Rejected Offers");
												log.info("  (3) Offers for specific car");
												log.info("  (4) Exit");

												try {
													allOffers = Integer.parseInt(sc.nextLine());
												} catch (NumberFormatException e) {
												}

												switch (allOffers) {

												// --3.2.1.1------------------------------------ Pending offers
												// -------------------------------------
												case 1:
													try {
														List<Offer> offers = offerService.getOffersByStatus("pending");
														if (offers != null) {
															log.info("All pending offers");
															for (Offer o : offers) {
																log.info(o);
															}
														}
													} catch (BusinessException e) {
														log.info(e.getMessage());
													}
													break;
												// --3.2.1.2------------------------------------ Rejected offers
												// -------------------------------------
												case 2:
													try {
														List<Offer> offers = offerService.getOffersByStatus("rejected");
														if (offers != null) {
															log.info("All rejected offers");
															for (Offer o : offers) {
																log.info(o);
															}
														}
													} catch (BusinessException e) {
														log.info(e.getMessage());
													}
													break;
												// --3.2.1.3------------------------------------ all offers for a single
												// Cars -------------------------------------
												case 3:
													log.info("Enter a Car ID");
													try {
														int id = Integer.parseInt(sc.nextLine());
														List<Offer> offers = offerService.getOffersByCarId(id);
														if (offers != null) {
															log.info("All offers for car with  id " + id);
															for (Offer o : offers) {
																log.info(o);
															}
														}
													} catch (NumberFormatException e) {
													} catch (BusinessException e) {
														log.info(e.getMessage());
													}

													break;
												default:
													log.info("Invalid menu option.Please try again!");
													log.debug("DD3213");
													break;
												}
											} while (allOffers != 4);

											break;

										// --3.2.2------------------------------------ Find an offer by ID
										// -------------------------------------
										case 2:

											try {
												log.info("Enter an Offer ID");
												long id = Long.parseLong(sc.nextLine());
												Offer offer = offerService.getOfferById(id);

												if (offer != null) {

													int oneOffer = 0;
													do {
														log.info("");
														log.info("==============================");
														log.info(" " + offer);
														log.info("==============================");
														log.info("  (1) Approve");
														log.info("  (2) Reject");
														log.info("  (3) Exit");

														oneOffer = Integer.parseInt(sc.nextLine());
														int carId = offer.getCarId();

														switch (oneOffer) {
														// --2.5.1------------------------------------ Approve
														// -------------------------------------
														case 1:

															if (offerService.approveOffer(id, carId) != 0) {
																log.info("Offer approved successfully");
															}
															oneOffer = 3;
															break;
														// --2.5.1------------------------------------ Reject
														// -------------------------------------
														case 2:

															if (offerService.rejectOfferById(id) != 0) {
																log.info("Offer rejected successfully");
															}
															oneOffer = 3;
															break;

														default:
															log.info("Invalid menu option.Please try again DD251!");
															log.debug("DD251");

														}

													} while (oneOffer != 3);
													break;
												}
											} catch (NumberFormatException e) {
											} catch (BusinessException e) {
												log.info(e.getMessage());
											}
											break;
										// --2.6------------------------------------ Exit Offers
										// -------------------------------------
										default:
											log.info("Invalid menu option.Please try again!");
											log.debug("DD26");
											break;
										}

									} while (eMenuOffer != 3);
									break;
								// --3.6------------------------------------ Find a customer by id
								// -------------------------------------
								case 3:

									break;

								// --3.7------------------------------------ View Payments
								// -------------------------------------
								case 4:
									int ePay = 0;

									do {

										log.info("");
										log.info("==============================");
										log.info("       PAYMENTS ");
										log.info("==============================");
										log.info("  (1) All Payments");
										log.info("  (2) All Payments for a car");
										log.info("  (3) Exit");

										switch (ePay) {

										// --3.7.1-------------------------------- View All Payments
										// -------------------------------------
										case 1:
											try {
												List<Payment> payments = payService.getAllPaynemts();
												if (payments != null) {
													log.info("    All payments: ");
													log.info("--------------------------");
													for (Payment p : payments) {
														log.info(p);
													}
												}
											} catch (BusinessException e) {
												log.info(e.getMessage());
											}
											break;

										// --3.7.2-------------------------------- View Payments for a Car
										// -------------------------------------
										case 2:
											log.info("Enter a Car ID");
											try {
												int carId = Integer.parseInt(sc.nextLine());
												List<Payment> payments = payService.getAllPaynemtsByCarId(carId);
												if (payments != null) {
													log.info("All payments for car with  id " + carId + " :");
													log.info("--------------------------------------");
													for (Payment p : payments) {
														log.info(p);
													}
												}
											} catch (NumberFormatException e) {
											} catch (BusinessException e) {
												log.info(e.getMessage());
											}
											break;
										// --3.7.3---------------------------------Exit
										// Payments---------------------------------------
										default:
											log.info("Invalid menu option.Please try again!");
											log.debug("DD373");
											break;
										} // Switch
									} while (ePay != 3);
									break;

								// --8-------------------------Employee menu exit (log out)
								// -------------------------------------------
								default:
									log.info("Invalid menu option.Please try again!");
									log.debug("DD8");
									break;
								} // Switch
							} while (eMenu != 5);
							break;
						}
					} catch (BusinessException e) {
						log.info(e.getMessage());

					}
					break;
				} while (eLog != 1); // employee login
				break;
//-----------------------------------------End of main menu ----------------------------------------------------
			default:
				log.info("Invalid menu option.Please try again! ");
				log.debug("DDEnd");

			} // switch ch
		} while (ch != 4); // main menu do

		sc.close(); // avoiding resource leak
	} // main

} // Driver
