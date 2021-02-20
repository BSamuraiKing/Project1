package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.entities.Account;
import com.revature.implments.AccountDAOImpl;
import com.revature.main.DBConnectionClass;

/**
 * Servlet implementation class CustomerMain
 */
public class CustomerMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String custChoice = request.getParameter("custChoice");
		
		if (custChoice.equals("deposit")) {
			String amount = request.getParameter("amount");
			String accountNumber = request.getParameter("accountNumber");
			Double deposit = Double.parseDouble(amount);
			Integer acctNumber = Integer.parseInt(accountNumber);
			if(deposit > 0) {
				try(Connection conn = DBConnectionClass.connect()) {
					CallableStatement statement = conn.prepareCall("CALL deposit(?,?)");
					statement.setInt(1, acctNumber);
					BigDecimal bd = new BigDecimal(deposit);
					statement.setBigDecimal(2, bd);
					statement.executeUpdate();
					out.println(deposit + " deposited.");
					RequestDispatcher reqD = request.getRequestDispatcher("CustomerPage.jsp");
					reqD.include(request, response);
				}catch(SQLException e) {
					e.printStackTrace();
					}
				}else {
					out.println("Invalid amount. returning to Customer menu.");
					RequestDispatcher reqD = request.getRequestDispatcher("CustomerPage.jsp");
					reqD.include(request, response);
				}
			
		}else if (custChoice.equals("withdrawal")) {
			String amount = request.getParameter("amount");
			String accountNumber = request.getParameter("accountNumber");
			Double withdrawal = Double.parseDouble(amount);
			Integer acctNumber = Integer.parseInt(accountNumber);
			try(Connection conn = DBConnectionClass.connect()){
				PreparedStatement state = conn.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
				state.setInt(1, acctNumber);
				ResultSet result = state.executeQuery();
				result.next();
				result.getDouble("balance");
				if(withdrawal < 0 || withdrawal > result.getDouble("balance")) {
					
					out.println("Invalid amount. Please try again");
					RequestDispatcher reqD = request.getRequestDispatcher("CustomerPage.jsp");
					reqD.include(request, response);
				}else {
					CallableStatement call = conn.prepareCall("CALL withdrawal(?,?)");
					call.setInt(1, acctNumber);
					BigDecimal bd = new BigDecimal(withdrawal);
					call.setBigDecimal(2, bd);
					call.executeUpdate();
					out.println(withdrawal + " withdrawn.");
					RequestDispatcher reqD = request.getRequestDispatcher("CustomerPage.jsp");
					reqD.include(request, response);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}else if (custChoice.equals("transferOut")) {
			String amount = request.getParameter("amount");
			String accountNumber = request.getParameter("accountNumber");
			String toAccount = request.getParameter("toAccount");
			Double transfer = Double.parseDouble(amount);
			Integer acctNumber = Integer.parseInt(accountNumber);
			Integer transferToAccount = Integer.parseInt(toAccount);
			
			try(Connection conn = DBConnectionClass.connect()) {
				PreparedStatement state = conn.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
				state.setInt(1, acctNumber);
				ResultSet result = state.executeQuery();
				result.next();
				result.getDouble("balance");
				if(transfer < 0 || transfer > result.getDouble("balance")) {
				
					out.println("Invalid amount. returning to Customer menu.");
					RequestDispatcher reqD = request.getRequestDispatcher("CustomerPage.jsp");
					reqD.include(request, response);
				}else {
					CallableStatement call = conn.prepareCall("CALL transfer_out(?,?,?)");
					call.setInt(1, acctNumber);
					BigDecimal bd = new BigDecimal(transfer);
					call.setBigDecimal(2, bd);
					call.setInt(3, transferToAccount);
					call.executeUpdate();
					out.println(transfer + " transferred");
					RequestDispatcher reqD = request.getRequestDispatcher("CustomerPage.jsp");
					reqD.include(request, response);
				}
			
				} catch (SQLException e) {
					e.printStackTrace();
			}
			
		}else if (custChoice.equals("transferIn") ) {
			String accountNumber = request.getParameter("accountNumber");
			Integer acctNumber = Integer.parseInt(accountNumber);
			try(Connection conn = DBConnectionClass.connect()) {
				PreparedStatement searchT = conn.prepareStatement("SELECT amount, to_account FROM pending_transfers WHERE to_account = ?");
				searchT.setInt(1, acctNumber);
				ResultSet result = searchT.executeQuery();
				double amount = 0d;
				while(result.next()) {
					amount = result.getDouble("amount");
				System.out.println(result.getDouble("amount") + "\n" +
									result.getInt("to_account"));
				}
				CallableStatement call = conn.prepareCall("CALL transfer_in(?,?)");
				call.setInt(1, acctNumber);
				BigDecimal bd = new BigDecimal(amount);
				call.setBigDecimal(2, bd);
				call.executeUpdate();
				out.println("Transfer completed.");
				RequestDispatcher reqD = request.getRequestDispatcher("CustomerPage.jsp");
				reqD.include(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				}
			
		}else if (custChoice.equals("balanceCheck") ) {
			String accountNumber = request.getParameter("accountNumber");
			try {
			Integer acctNumber = Integer.parseInt(accountNumber);
			try(Connection conn = DBConnectionClass.connect()) {
				System.out.println("Enter an account number to check your balance: ");
				//acctNumber = Integer.parseInt(input.nextLine());
				PreparedStatement searchIn = conn.prepareStatement("SELECT account_number, balance FROM accounts WHERE account_number = ?");
				searchIn.setInt(1, acctNumber);
				ResultSet res = searchIn.executeQuery();
				
				while(res.next()) {
					System.out.println(res.getInt("account_number") + "\t"
							+ res.getDouble("balance"));
					String resultAcct = "" + res.getInt("account_number");
					String resultBalance = "" + res.getDouble("balance");
					request.setAttribute("resultAcct", resultAcct);
					request.setAttribute("resultBalance", resultBalance);
				}
				
				RequestDispatcher reqD = request.getRequestDispatcher("Balance.jsp");
				reqD.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}catch(NumberFormatException e) {
				out.println("<h3>Invalid Input. Please try again");
				RequestDispatcher reqD = request.getRequestDispatcher("CustomerPage.jsp");
				reqD.include(request, response);
			}
			
			
			
		}else if (custChoice.equals("newAcct")) {
			String accountNumber = request.getParameter("accountNumber");
			String amount = request.getParameter("amount");
			String username = request.getParameter("username");
			Integer nuAcct = Integer.parseInt(accountNumber);
			Double nuAmt = Double.parseDouble(amount);
			Account acct = new Account(nuAcct, nuAmt, username);
			AccountDAOImpl acctImp = new AccountDAOImpl();
			acctImp.addPendingAccounts(acct);
			out.println("<h3> New Account Submitted</h3>");
			RequestDispatcher reqD = request.getRequestDispatcher("CustomerPage.jsp");
			reqD.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
