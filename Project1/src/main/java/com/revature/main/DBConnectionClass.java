package com.revature.main;

import java.sql.*;


public class DBConnectionClass {
	
	private static final String URL  = "jdbc:postgresql://localhost/Project0";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "Walmen01!";
	
	public static Connection connect() {
		
		Connection connection = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			 
			 return connection;
		
		}catch(SQLException e) {
			e.printStackTrace();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
		
	}

}

