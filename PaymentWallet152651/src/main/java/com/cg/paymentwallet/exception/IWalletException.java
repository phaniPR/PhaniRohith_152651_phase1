package com.cg.paymentwallet.exception;

public interface IWalletException {
	String nameException ="Enter Valid Name";
	String mobileNumberException = "Enter Valid Mobile Number ";
	String invalidDetails = "Given details are incorrect ";
	String insufficientFunds = "Insufficicnet account balance";
	String ACCOUNTEXISTS = "Account already exists with the given mobile number";

}