package com.revature.entities;

public class Transactions {
	
	private int account;
	private double deposit;
	private double withdrawal;
	private double transferIn;
	private double transferOut;
	
	public Transactions() {
		super();
	}
	
	

	public Transactions(int account, double deposit, double withdrawal, double transferIn, double transferOut) {
		super();
		this.account = account;
		this.deposit = deposit;
		this.withdrawal = withdrawal;
		this.transferIn = transferIn;
		this.transferOut = transferOut;
	}



	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getWithdrawal() {
		return withdrawal;
	}

	public void setWithdrawal(double withdrawal) {
		this.withdrawal = withdrawal;
	}

	public double getTransferIn() {
		return transferIn;
	}

	public void setTransferIn(double transferIn) {
		this.transferIn = transferIn;
	}
	
	
	public double getTransferOut() {
		return transferOut;
	}



	public void setTransferOut(double transferOut) {
		this.transferOut = transferOut;
	}



	@Override
	public String toString() {
		return "Transactions [account=" + account + ", deposit=" + deposit + ", withdrawal=" + withdrawal
				+ ", transferIn=" + transferIn + ", transferOut=" + transferOut + "]";
	}



	
	
	
	
}
