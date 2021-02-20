package com.revature.implments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.EmployeesDAO;
import com.revature.entities.Employee;
import com.revature.main.DBConnectionClass;


public class EmployeeImpl implements EmployeesDAO<Employee>{
	
	public Employee emp;

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		try(Connection conn = DBConnectionClass.connect()) {
			PreparedStatement getEmployees = conn.prepareStatement("SELECT * FROM accounts");
			ResultSet result = getEmployees.executeQuery();
			
			while(result.next()) {
				emp.setFirstName(result.getString(1));
				emp.setLastName(result.getString(2));
				emp.setUsername(result.getString(3));
				emp.setPassword(result.getString(4));
				employeeList.add(emp);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return employeeList;
	}

	@Override
	public void addEmployee(Employee t) {
		
		
	}

	@Override
	public void updateEmployee(Employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(Employee t) {
		// TODO Auto-generated method stub
		
	}

}
