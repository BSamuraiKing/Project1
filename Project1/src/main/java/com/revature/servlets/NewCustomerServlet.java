package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.entities.Customer;
import com.revature.implments.CustomerImpl;

/**
 * Servlet implementation class NewCustomerServlet
 */
public class NewCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Customer cust = new Customer();
	public CustomerImpl newCust = new CustomerImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		cust.setFirstName(firstName);
		cust.setLastName(lastName);
		cust.setUsername(userName);
		cust.setPassword(password);
		
		newCust.addCustomer(cust);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/CustomerConfirm.jsp");
		dispatch.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
