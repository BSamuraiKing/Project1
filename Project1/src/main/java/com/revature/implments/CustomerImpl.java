package com.revature.implments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.CustomerDAO;
import com.revature.entities.Customer;
import com.revature.main.DBConnectionClass;

public class CustomerImpl implements CustomerDAO<Customer> {
	
	public Customer cust;

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = new ArrayList<Customer>();
		
		try(Connection conn = DBConnectionClass.connect()) {
			PreparedStatement getCustomers = conn.prepareStatement("SELECT first_name, last_name, username FROM customer");
			ResultSet result = getCustomers.executeQuery();
			
			while(result.next()) {
				cust.setFirstName(result.getString("first_name"));
				cust.setLastName(result.getString("last_name"));
				cust.setUsername(result.getString("username"));
				cust.setPassword(result.getString("pass_word"));
				customerList.add(cust);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return customerList;
	}

	@Override
	public void addCustomer(Customer t) {
		String insert = "INSERT INTO customer(first_name, last_name, username, pass_word) VALUES(?, ?, ?, ?)";
		try(Connection conn = DBConnectionClass.connect()) { 
			PreparedStatement putCust = conn.prepareStatement(insert);
			putCust.setString(1, t.getFirstName());
			putCust.setString(2, t.getLastName());
			putCust.setString(3, t.getUsername());
			putCust.setString(4, t.getPassword());
			putCust.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			e.getMessage();
		}
		
	}

	@Override
	public void updateCustomer(Customer t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(Customer t) {
		// TODO Auto-generated method stub
		
	}

}
