package com.cg.paymentwallet.pl;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.cg.paymentwallet.beans.Customer;
import com.cg.paymentwallet.exception.WalletException;
import com.cg.paymentwallet.service.WalletService;
import com.cg.paymentwallet.service.WalletServiceImpl;



public class Client {

	public static void main(String[] args) {
		WalletService service=new WalletServiceImpl();
		
		Scanner sc=new Scanner(System.in);
	int choice=0;
		
		do {
			Customer customer=new Customer();
			printdetails();
			System.out.println("Enter Your Choice");
			
			choice=sc.nextInt();
			
	
				
			
		
			switch(choice) {
			case 1:
	
				try {
					
				System.out.println("Enter your mobile number");
				String mobile=sc.next().trim();
				service.validatePhone(mobile);
				service.checkAccount(mobile);
				customer.setMobileNo(mobile);
				
				System.out.println("Enter your name");
				String name=sc.next();
				service.validateName(name);
				customer.setName(name);
				
					System.out.println("Enter amount");
					BigDecimal balance=sc.nextBigDecimal();
					service.validateMoney(balance);
						customer.getWallet().setBalance(balance);
						
						 Customer cust=service.createAccount(customer);
						 System.out.println("Your Account is created \n Your Details are");
						 System.out.println("Name:" +cust.getName());
						 System.out.println("Mobile:" +cust.getMobileNo());
						 System.out.println("Balance is: " +cust.getWallet().getBalance());
					}catch(WalletException exception) {
						System.out.println(exception.getMessage());
					}
			
				 break;
				
			case 2:
				try {
					
				
				System.out.println("Enter your mobile number");
				String mobile1=sc.next().trim();
				service.validatePhone(mobile1);
				Customer cust1=service.showBalance(mobile1);
				System.out.println("Your Balance is:" +cust1.getWallet().getBalance());
				}catch (WalletException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
				System.out.println("Enter target mobile Number");
				String mobile2=sc.next().trim();
				service.validatePhone(mobile2);
				System.out.println("Enter your mobile number");
				String mobile3=sc.next().trim();
				service.validatePhone(mobile3);
				
				System.out.println("Enter the amount u wanted to transfer");
				BigDecimal amount=sc.nextBigDecimal();
				service.validateMoney(amount);
				
		
				
				Customer cust2=service.fundTransfer(mobile3, mobile2, amount);
				System.out.println(cust2.getName()+ " your balance after transfering is: " +cust2.getWallet().getBalance());
				}catch(WalletException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
				System.out.println("Enter your mobile number");
				String mobile4=sc.next().trim();
				service.validatePhone(mobile4);
				System.out.println("Enter Amount you wanted to deposit");
				BigDecimal amount1=sc.nextBigDecimal();
				service.validateMoney(amount1);
				
				Customer cust3=service.depositAmount(mobile4, amount1);
				System.out.println(cust3.getName()+ " Your New Balance is: " +cust3.getWallet().getBalance());
				}catch(WalletException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				try {
				System.out.println("Enter your mobile number");
				String mobile5=sc.next().trim();
				service.validatePhone(mobile5);
				System.out.println("Enter Amount you wanted to withdraw");
				BigDecimal amount2=sc.nextBigDecimal();
				service.validateMoney(amount2);
				Customer cust4=service.withdrawAmount(mobile5, amount2);
				System.out.println("Amount after transaction is:" +cust4.getWallet().getBalance());
				}catch(WalletException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				try{
					System.out.println("Enter your mobile number");
				String mobile6=sc.next().trim();
				service.validatePhone(mobile6);
				Customer cust5=service.getCustomer(mobile6);
				if(cust5!=null) {
				TreeMap<LocalDateTime, String> transactions = cust5.getTransactions();
				for (Map.Entry<LocalDateTime, String> trans : transactions.entrySet()) {
					System.out.println(trans.getKey() + " @ " + trans.getValue());
				}
				}else {
					System.out.println("User with mobile number " +mobile6 + "Not found");
				}
				}catch(WalletException exception) {
					System.out.println(exception.getMessage());
				}
				
				break;
				
			default:
				System.out.println("Enter a valid choice");
			}
			
				
				
			
			
		}while(choice!=7);
			
	}

	private static void printdetails() {
		System.out.println("--welcome--");
		System.out.println(" \n 1.Register \n 2.ShowBalance \n 3.FundTransfer \n 4.Deposit \n 5.Withdraw \n 6.printTransactions");
	}

}
