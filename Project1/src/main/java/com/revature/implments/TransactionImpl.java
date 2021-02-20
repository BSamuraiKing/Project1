package com.revature.implments;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.TransactionsDAO;
import com.revature.entities.Transactions;
import com.revature.main.DBConnectionClass;


public class TransactionImpl implements TransactionsDAO<Transactions>{
	
	public Transactions trns;

	@Override
	public void getAllTransactions() {
		//List<Transactions> transactionList = new ArrayList<Transactions>();
		
		try(Connection conn = DBConnectionClass.connect()) {
			//PreparedStatement getTransactions = conn.prepareStatement("SELECT * FROM transactions");
			//ResultSet result = getTransactions.executeQuery();
			
			CallableStatement call = conn.prepareCall("Call get_all_transactions()");
			ResultSet result = call.executeQuery();
			
			while(result.next()) {
				System.out.println(result.getInt(1) 
				+ "\t" + result.getInt(2) + "\t" + result.getDouble(3) + "\t" + result.getDouble(4) + "\t" +result.getDouble(5) + "\n");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void addTransaction(Transactions t) {
		try(Connection conn = DBConnectionClass.connect()) {
			String insert = "INSERT INTO transactions(accounts, deposit, withdrawal, transfer_in, transfer_out) VALUES(?, ?, ?, ?)";
			PreparedStatement putTransactions = conn.prepareStatement(insert);
			putTransactions.setInt(1, t.getAccount());
			
			putTransactions.setDouble(2, t.getDeposit());
			putTransactions.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void addPendingTransaction(Transactions t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTransaction(Transactions t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTransaction(Transactions t) {
		// TODO Auto-generated method stub
		
	}

}
