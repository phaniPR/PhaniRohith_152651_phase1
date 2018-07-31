package com.cg.paymentwallet.repo;
import java.util.HashMap;

import com.cg.paymentwallet.beans.Customer;




public interface WalletRepo {
	
	public Customer save(Customer customer);
	
	public HashMap<String, Customer> getDetails();
}
