package com.carlot.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.carlot.dao.impl.LoginDAOImpl;
import com.carlot.exception.BusinessException;
import com.carlot.serviceMock.impl.LoginServiceImplMock;

public class CarlotMockitoTest {
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
    @InjectMocks  
    LoginServiceImplMock loginServiceMock;
    
    @Mock  
    LoginDAOImpl loginMock;
    
  //----------------------------------Login---------------------------------------------------
	    
    @Test
    public void letCustomerLogin() throws BusinessException {
    	
    	boolean b = loginServiceMock.letCustomerLogin("KK", "Klawn");
    	assertEquals(false, b);
    	verify(loginMock, times(1)).letCustomerLogin("KK", "Klawn");
    	
       	boolean b1 = loginServiceMock.letCustomerLogin("KKKK", "Klawn66");
    	assertEquals(true, b1);
    	verify(loginMock, times(1)).letCustomerLogin("KKKK", "Klawn66");
    	
    	boolean b2 = loginServiceMock.letCustomerLogin(null, null);
    	assertEquals(false, b2);
    	verify(loginMock, times(1)).letCustomerLogin(null, null);
    }
    
    @Test
    public void letEmployeeLogin() throws BusinessException {
    	
    	boolean b = loginServiceMock.letEmployeeLogin("GG5555", "gala555");
    	assertEquals(true, b);
    	verify(loginMock, times(1)).letEmployeeLogin("GG5555", "gala555");
    	
    	boolean b1 = loginServiceMock.letEmployeeLogin("kasha", "kashamala");
    	assertEquals(false, b1);
    	verify(loginMock, times(1)).letEmployeeLogin("kasha", "kashamala");
    	
    	boolean b2 = loginServiceMock.letEmployeeLogin(null, null);
    	assertEquals(false, b2);
    	verify(loginMock, times(1)).letEmployeeLogin(null, null);
    }

}
