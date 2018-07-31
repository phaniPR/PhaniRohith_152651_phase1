package com.cg.paymentwallet.beans;


import java.time.LocalDateTime;
import java.util.TreeMap;

public class Customer{
	

	private String name;
	private String mobileNo;
	
	private Wallet wallet;
	private TreeMap<LocalDateTime,String> transactions = new TreeMap<>();
	public Customer() {
		wallet = new Wallet();
	}
	

	public Customer(String name, String mobileNo, Wallet wallet, TreeMap<LocalDateTime, String> transactions) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.wallet = wallet;
		this.transactions = transactions;
	}


	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileNo=" + mobileNo + ", wallet=" +wallet + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public TreeMap<LocalDateTime, String> getTransactions() {
		return transactions;
	}

	public void setTransactions(TreeMap<LocalDateTime, String> transactions) {
		this.transactions = transactions;
	}

}

