package com.cg.paymentwallet.repo;
import java.util.HashMap;

import com.cg.paymentwallet.beans.Customer;

public class WalletRepoImpl implements WalletRepo {

	public static HashMap<String,Customer> account=new HashMap<>();
	
	
	@Override
	public Customer save(Customer customer) {
		
		 account.put(customer.getMobileNo(),customer );
		
		 
		 return customer;
	}


	
	@Override
	public HashMap<String, Customer> getDetails() {
		
		return account;
	}
} 