package com.cg.paymentwallet.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.cg.paymentwallet.beans.Customer;
import com.cg.paymentwallet.beans.Wallet;
import com.cg.paymentwallet.exception.WalletException;
import com.cg.paymentwallet.service.WalletService;
import com.cg.paymentwallet.service.WalletServiceImpl;

public class JTestClass {

	public static WalletService iWalletService=null;
	Customer customer1,customer2,customer3,customer4,customer5,customer6,customer7;
	
	@Before
	public void initData() {
		iWalletService=new WalletServiceImpl();
		customer1= new Customer("phani","8885599774",new Wallet(),new TreeMap<LocalDateTime, String>());
		customer2 = new Customer("Vineeth","9949453482",new Wallet(),new TreeMap<LocalDateTime, String>());
		customer3 = new Customer("Vinay","8949453482",new Wallet(),new TreeMap<LocalDateTime, String>());

	}
	
	@Test(expected = WalletException.class)
	public void checkMobileNumberError() throws WalletException
	{
		iWalletService.validatePhone("8888");
	}
	@Test
	public void checkMobileNumberTrue() throws WalletException
	{
		iWalletService.validatePhone("8885599774");
	}

 
 
    
  
    
    @Test
    public void amountValidationTestForInteger() throws WalletException {
    	assertTrue(iWalletService.validateMoney(BigDecimal.valueOf(5000)));
    }
    
    @Test
    public void amountValidationTestIfDigitsExistsAfterDecimalPoint() throws WalletException {
    	assertTrue(iWalletService.validateMoney(BigDecimal.valueOf(5000.00)));
    }
    
    @Test
    public void amountValidationTestIfDigitsDoesntExistsAfterDecimalPoint() throws WalletException {
    	assertTrue(iWalletService.validateMoney(BigDecimal.valueOf(5000.)));
    }
    

}
