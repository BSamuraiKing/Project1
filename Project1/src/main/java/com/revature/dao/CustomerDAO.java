package com.revature.dao;

import java.util.List;

public interface CustomerDAO<T> {
	
List<T> getAllCustomers();
	
	void addCustomer(T t);	
	
	
	void updateCustomer(T t);
	
	void deleteCustomer(T t);

}
