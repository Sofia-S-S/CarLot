package com.carlot.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.carlot.dao.impl.CarDAOImpl;
import com.carlot.dao.impl.LoginDAOImpl;
import com.carlot.dao.impl.OfferDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.model.Car;
import com.carlot.model.Offer;
import com.carlot.service.impl.CarServiceImpl;
import com.carlot.service.impl.LoginServiceImpl;
import com.carlot.service.impl.OfferServiceImpl;




public class CarlotMockitoTest {
	
	@Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
	//----------------------------------Offer---------------------------------------------------
	
    @InjectMocks  //Injecting Mocks into an object under test
    OfferServiceImpl offerService;
    
    
    @Mock  //Mocking out dependencies of our tested object
    OfferDAOImpl offerMock;
    
	ArrayList<Offer> myOffers = new ArrayList<>(Arrays.asList(new Offer(200001, 101, 5000,"rejected", 16, new Date()),
															  new Offer(200000, 105, 3000,"accepted", 12, new Date()),
															  new Offer(200004, 107, 9000,"pending", 17, new Date())));
    
	@Test
	public void getOffersByStatus() throws BusinessException {
		
		Mockito.when(offerMock.getAllOffers()).thenReturn(myOffers);
		
		List<Offer> returnedOffers = offerService.getOffersByStatus("pending");
		
		for(Offer o : returnedOffers) {
			Assert.assertEquals("pending", o.getStatus());
			Assert.assertNotEquals("accepted",  o.getStatus());
		}
	}
    
	@Test
	public void getOffersByCarId() throws BusinessException {
		
		Mockito.when(offerMock.getAllOffers()).thenReturn(myOffers);
		
		List<Offer> returnedOffers = offerService.getOffersByCarId(105);
		
		for(Offer o : returnedOffers) {
			Assert.assertEquals(105, o.getCarId());
			Assert.assertNotEquals(107,  o.getCarId());
		}
	}
	
	@Test
	public void getOffersByCustomerrId() throws BusinessException {
		
		Mockito.when(offerMock.getAllOffers()).thenReturn(myOffers);
		
		List<Offer> returnedOffers = offerService.getOffersByCustomerId(12);
		
		for(Offer o : returnedOffers) {
			Assert.assertEquals(12, o.getCustomerId());
			Assert.assertNotEquals(17,   o.getCustomerId());
		}
	}
	
	@Test
	public void getOfferById() throws BusinessException {
		offerService.getOfferById(2000000);
	}
	
	@Test
	public void approveOffer() throws BusinessException {
		offerService.approveOffer(20000000, 100);
	}
	
	//----------------------------------Login---------------------------------------------------
	
    @InjectMocks  //Injecting Mocks into an object under test
    LoginServiceImpl loginService;
    
    
    @Mock  //Mocking out dependencies of our tested object
    LoginDAOImpl loginMock;
    

    @Test
    public void letEmployeeLogin() throws BusinessException {
    	loginService.letEmployeeLogin("MM8955", "gala555");
    }
    
    @Test
    public void letCustomerLogin() throws BusinessException {
    	loginService.letCustomerLogin("Michael", "Brown");  
    }
    @Test
	public void getCustomerById() throws BusinessException {
    	loginService.getCustomerById(300);
    }
    
    //----------------------------------Car -------------------------------------------------------

    @InjectMocks  //Injecting Mocks into an object under test
    CarServiceImpl carService;


    @Mock  //Mocking out dependencies of our tested object
    CarDAOImpl carMock;
    
      
	@Test
	public void getCarsByStatus() throws BusinessException {
		
		
		ArrayList<Car> myCars = new ArrayList<>(Arrays.asList(new Car(100, "hatchback", "honda","tundra", 2008, "blue", 35786, "FFGT7HHDDY7796453", "sold"),
						new Car(107, "hatchback", "honda","tundra", 2008, "blue", 35786, "FFGT888DDY7796453", "sold"),
								new Car(109, "hatchback", "honda","tundra", 2008, "blue", 35786, "FFGT7HHDDY77964P3", "for sale")
				));
		
		Mockito.when(carMock.getAllCars()).thenReturn(myCars);
		
		List<Car> returnedCars = carService.getCarsByStatus("sold");
		
		for(Car c : returnedCars) {
			Assert.assertEquals("sold", c.getStatus());
			Assert.assertNotEquals("for sale", c.getStatus());
		}
	}
	
	@Test
	public void deleteCar() throws BusinessException {
		carMock.deleteCar(55555);
	}
	
	@Test
	public void createCar() throws BusinessException {
		Car car = new Car(109, "hatchback", "honda","tundra", 2008, "blue", 35786, "FFGT7HHDDY77964P3", "for sale");
		carMock.createCar(car);
	}
}

