package com.cg.paymentwallet.service;


import java.math.BigDecimal;

import com.cg.paymentwallet.beans.Customer;
import com.cg.paymentwallet.exception.WalletException;



public interface WalletService {

	public Customer createAccount(Customer customer);
	public Customer showBalance(String mobileno) throws WalletException;
	public Customer fundTransfer(String sourceMobileNo,String targetMobileNo, BigDecimal amount) throws WalletException;
	public Customer depositAmount(String mobileNo,BigDecimal amount) throws WalletException;
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws WalletException;
	boolean validatePhone(String phoneNumber) throws WalletException;
	boolean validateMoney(BigDecimal balance) throws WalletException;
	boolean validateName(String name) throws WalletException;
	public void checkAccount(String mobile) throws WalletException;
	Customer getCustomer(String mobile);
}
