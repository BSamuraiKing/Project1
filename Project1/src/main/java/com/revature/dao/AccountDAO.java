package com.revature.dao;

import java.util.List;

public interface AccountDAO<T> {
	
	List<T> getAllAccounts();
	
	void addAccounts(T t);	
	
	void addPendingAccounts(T t);
	
	void updateAccout(T t);
	
	void updateAccountLess(T t);
	
	void deleteAccount(T t);

}
