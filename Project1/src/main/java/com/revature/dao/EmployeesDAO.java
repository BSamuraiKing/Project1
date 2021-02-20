package com.revature.dao;

import java.util.List;

public interface EmployeesDAO<T> {
List<T> getAllEmployees();
	
	void addEmployee(T t);	
	
	void updateEmployee(T t);
	
	void deleteEmployee(T t);

}
