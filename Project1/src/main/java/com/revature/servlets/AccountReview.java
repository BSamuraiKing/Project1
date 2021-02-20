package com.revature.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.main.DBConnectionClass;

/**
 * Servlet implementation class AccountReview
 */
public class AccountReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		try(Connection conn = DBConnectionClass.connect()) {
			String[] values = request. getParameter("approvedAccounts");
			
			for (int i = 0; i < values.length; i++) {
			
			}
			CallableStatement insertAccts = conn.prepareCall("Call approve_account(?, ?, ?)");
			String acctUsername = set.getString("username");
			int addNewAccount = set.getInt("account_number");
			double initialBalance = set.getDouble("balance");
			BigDecimal big = new BigDecimal(initialBalance);
			insertAccts.setInt(1, addNewAccount);
			insertAccts.setBigDecimal(2, big);
			insertAccts.setString(3, acctUsername);
			insertAccts.executeUpdate();
			System.out.println("Account approved!");
			System.out.println();
		}catch(SQLException e) {
			
		}
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
