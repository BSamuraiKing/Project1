package com.revature.implments;

import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.entities.Account;
import com.revature.main.DBConnectionClass;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


public class AccountDAOImpl implements AccountDAO<Account> {
	
	public Account account;


	public List<Account> getAllAccounts() {
		List<Account> accountList = new ArrayList<Account>();
		
		try(Connection conn = DBConnectionClass.connect()) {
			PreparedStatement getAccounts = conn.prepareStatement("SELECT * FROM accounts");
			ResultSet result = getAccounts.executeQuery();
			
			while(result.next()) {
				account.setAcctno(result.getInt(1));
				account.setBalance(result.getDouble(2));
				account.setUsername(result.getString(3));
				accountList.add(account);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return accountList;
	}

	@Override
	public void addAccounts(Account t) {
		String insert = "INSERT INTO pending_acounts(account_number, balance, username) VALUES(?,?,?)";
		try(Connection conn = DBConnectionClass.connect()) {
			PreparedStatement putAccts = conn.prepareStatement(insert);
			putAccts.setInt(1, t.getAcctno());
			putAccts.setDouble(2, t.getBalance());
			putAccts.setString(1, t.getUsername());
			putAccts.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateAccout(Account t) {
		try(Connection conn = DBConnectionClass.connect()) {
			//CallableStatement call = conn.prepareCall("Call transfer_in(?, ?, ?)");
			//String update = "UPDATE accounts SET balance = balance + ?  "
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAccount(Account t) {
		try(Connection conn = DBConnectionClass.connect()) {
			String delete = "DELETE * FROM pending_accounts WHERE account_number = ? ";
			PreparedStatement deleteAccts = conn.prepareStatement(delete);
			deleteAccts.setInt(1, t.getAcctno());
			deleteAccts.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void addPendingAccounts(Account t) {
		String insert = "INSERT INTO pending_accounts(account_number, balance, username) VALUES(?, ?, ?)";
		try(Connection conn = DBConnectionClass.connect()) {
			PreparedStatement putAccts = conn.prepareStatement(insert);
			BigDecimal bd = new BigDecimal(t.getBalance());
			putAccts.setInt(1, t.getAcctno());
			putAccts.setBigDecimal(2, bd);
			putAccts.setString(3, t.getUsername());
			putAccts.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateAccountLess(Account t) {
		try(Connection conn = DBConnectionClass.connect()) {
			CallableStatement call = conn.prepareCall("Call transfer_out(?, ?)");
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
