package com.cg.paymentwallet.service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;

import com.cg.paymentwallet.beans.Customer;
import com.cg.paymentwallet.exception.IWalletException;
import com.cg.paymentwallet.exception.WalletException;
import com.cg.paymentwallet.repo.WalletRepo;
import com.cg.paymentwallet.repo.WalletRepoImpl;



public class WalletServiceImpl implements WalletService {
	static WalletRepo repo=new WalletRepoImpl() ;
	public static HashMap<String,Customer> account=new HashMap<>();
    static {
    	account = repo.getDetails();
    }
	
	public boolean validatePhone(String phone) throws WalletException {
		String pattern = "\\d{10}";
		if (!(phone.matches(pattern))) {
			throw new WalletException("enter valid phone number");
		}
		return true;
	}

	public boolean validateMoney(BigDecimal amount) throws WalletException {
		String amou = amount.toString();
		if (!(amou.matches("[0-9]+[.]{0,1}[0-9]+"))) {
			throw new WalletException("enter valid Amount");
		}
		return true;
	}

	public boolean validateName(String name) throws WalletException {
		if (!(name.matches("[a-zA-Z]+"))) {
			throw new WalletException("enter valid name");
		}
		return true;

	}
		
	@Override
	public Customer createAccount(Customer customer) {
		
		return repo.save(customer);
		
	}

	@Override
	public Customer showBalance(String mobileno) throws WalletException {
		
		
			if(!validatePhone(mobileno))
			{
				throw new WalletException(IWalletException.mobileNumberException);
			}
			else {
				Customer customer=account.get(mobileno);
		        if(customer!=null)
		        {
		            return customer;
		        }
		        else
		        {
		        	 throw new WalletException("Account with given Mobile number doesn't exist" );
		        }
			}
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws WalletException {
		if(!validatePhone(sourceMobileNo)){
			if(!validatePhone(targetMobileNo)){
				throw new WalletException(IWalletException.mobileNumberException);
			}
		}
		else {
			Customer customer=account.get(sourceMobileNo);
			Customer customer2=account.get(targetMobileNo);
			if(customer!=null && customer2!=null) {
				BigDecimal high=(customer.getWallet().getBalance()).max(amount);
				if(customer.getWallet().getBalance()==high) {
					BigDecimal upadetBalance=customer.getWallet().getBalance().subtract(amount);
					customer.getWallet().setBalance(upadetBalance);
					customer.getTransactions().put(LocalDateTime.now(), "Debited " +amount +" to "+customer2.getName());
					BigDecimal updateBalance1=customer2.getWallet().getBalance().add(amount);
					customer2.getWallet().setBalance(updateBalance1);
					customer2.getTransactions().put(LocalDateTime.now(), " Credited" +amount +" from "+customer.getName());
				}
				else
					throw new WalletException(IWalletException.insufficientFunds);
			

			}
		}
		return account.get(sourceMobileNo);
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws WalletException {
		if(!validatePhone(mobileNo)) {
			if(!validateMoney(amount)) {
				throw new WalletException(IWalletException.mobileNumberException);
			}
		}
		else {
			Customer customer=account.get(mobileNo);
			if(customer!=null)
			{
				BigDecimal deposit=customer.getWallet().getBalance().add(amount);
				customer.getWallet().setBalance(deposit);
				customer.getTransactions().put(LocalDateTime.now(), " Credited " + amount);
			}
		}
		return account.get(mobileNo);
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws WalletException {
		if(!validatePhone(mobileNo)) {
			if(!validateMoney(amount)) {
				throw new WalletException(IWalletException.mobileNumberException);
			}
		}
		else {
			Customer customer=account.get(mobileNo);
			BigDecimal big=customer.getWallet().getBalance().max(amount);
	        if(customer.getWallet().getBalance()==big){
	        	BigDecimal newbal=customer.getWallet().getBalance().subtract(amount);
	        	customer.getWallet().setBalance(newbal);
	        	customer.getTransactions().put(LocalDateTime.now(), "Debited " + amount);
		}
	        else {
	        	throw new WalletException(IWalletException.insufficientFunds);
	        }
		}
		return account.get(mobileNo);
		
	}


	@Override
	public void checkAccount(String mobile) throws WalletException {
		if((account.containsKey(mobile))) {
			throw new WalletException(IWalletException.ACCOUNTEXISTS);
		}
			
		
	}

	@Override
	public Customer getCustomer(String mobile) {
		return account.get(mobile);
	}


	

}

