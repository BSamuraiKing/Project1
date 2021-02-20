package com.revature.entities;

public class Account {
	
	
	private int acctno;
	
	private double balance;
	
	private String username;

	public Account() {
		super();
	}

	public Account(int acctno, double balance, String username) {
		super();
		this.acctno = acctno;
		this.balance = balance;
		this.username = username;
	}

	public int getAcctno() {
		return acctno;
	}

	public void setAcctno(int acctno) {
		this.acctno = acctno;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Account [acctno=" + acctno + ", balance=" + balance + ", username=" + username + "]";
	}

	
	

}
