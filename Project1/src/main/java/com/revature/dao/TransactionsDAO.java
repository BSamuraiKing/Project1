package com.revature.dao;

import java.util.List;

public interface TransactionsDAO<T> {
   void getAllTransactions();
	
	void addTransaction(T t);	
	
	void addPendingTransaction(T t);
	
	void updateTransaction(T t);
	
	void deleteTransaction(T t);

}
