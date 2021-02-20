package com.revature.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.entities.Account;
import com.revature.entities.Transactions;
import com.revature.main.DBConnectionClass;

/**
 * Servlet implementation class EmployeeMain
 */
public class EmployeeMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String transactChoice = request.getParameter("transactChoice");
		
		if(transactChoice.equals("review")) {
			int accountNumber = 0;
			int choice = 0;
			int approval = 0;
			try(Connection conn = DBConnectionClass.connect()) {
				PreparedStatement pend = conn.prepareStatement("SELECT * FROM pending_accounts");
				ResultSet result = pend.executeQuery();
				List<Account> accountList = new ArrayList<Account>();
				while(result.next()) {
					Account acct = new Account();
					acct.setAcctno(result.getInt("account_number"));
					acct.setBalance(result.getDouble("balance"));
					acct.setUsername(result.getString("username"));
					accountList.add(acct);
				}
					request.setAttribute("accountList", accountList);
					RequestDispatcher dispatch = request.getRequestDispatcher("ReviewAccounts.jsp");
					dispatch.forward(request, response);
					
					
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else if(transactChoice.equals("viewAccounts")) {
			String username = request.getParameter("username");
			List<Account> selectedList = new ArrayList<Account>();
			try(Connection conn = DBConnectionClass.connect()) {
				PreparedStatement getCustomers = conn.prepareStatement("SELECT first_name, last_name, username FROM customer");
				ResultSet result = getCustomers.executeQuery();
				
				while(result.next()) {
					System.out.println(
							result.getString("first_name") 
				+ "\t" + result.getString("last_name")
				+ "\t" +	result.getString("username"));
				}
				
				System.out.println();
				System.out.println();
				System.out.println("Enter the customer's username that you wish to view:");
				PreparedStatement pare = conn.prepareStatement("SELECT account_number, balance FROM accounts WHERE username = ?");
				pare.setString(1, username);
				ResultSet res = pare.executeQuery();
				
				while(res.next()) {
					Account account = new Account();
					account.setAcctno(res.getInt("account_number") );
					account.setBalance(res.getDouble("balance"));
					account.setUsername(username);
					selectedList.add(account);
				}
				request.setAttribute("selectedList", selectedList);
				RequestDispatcher dispatch = request.getRequestDispatcher("CustomerAccountView.jsp");
				dispatch.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else if(transactChoice.equals("viewAll")) {
			try(Connection conn = DBConnectionClass.connect()) {
				//CallableStatement call = conn.prepareCall("Call get_all_transactions()");
				//ResultSet result = call.executeQuery();
					List<Transactions> transactionList = new ArrayList<Transactions>();
					
					PreparedStatement getTransactions = conn.prepareStatement("SELECT * FROM transactions");
					ResultSet result = getTransactions.executeQuery();
				
				while(result.next()) {
					Transactions actions = new Transactions();
					actions.setAccount(result.getInt(2));
					actions.setDeposit(result.getDouble(3));
					actions.setWithdrawal(result.getDouble(4));
					actions.setTransferIn(result.getDouble(5));
					actions.setTransferOut(result.getDouble(6));
					transactionList.add(actions);
				}
				request.setAttribute("transactionList", transactionList);
				RequestDispatcher reqT = request.getRequestDispatcher("TransactionList.jsp");
				reqT.forward(request, response);
				}catch(SQLException e) {
					e.printStackTrace();
				}catch(Exception e) {
					e.printStackTrace();
				}
			
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
