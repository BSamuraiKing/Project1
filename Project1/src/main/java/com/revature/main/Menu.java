package com.revature.main;

import java.util.Scanner;

import com.revature.entities.Account;
import com.revature.entities.Customer;
import com.revature.implments.AccountDAOImpl;
import com.revature.implments.CustomerImpl;
import com.revature.implments.TransactionImpl;

import java.math.BigDecimal;
import java.sql.*;

public class Menu {
	
	 public static Account acct;
	 public static AccountDAOImpl acctImp = new AccountDAOImpl();
	 public static Customer cust;
	 public static CustomerImpl customer;
	 public static TransactionImpl transactions;

	public Menu() {
		super();
	}

	public Menu(AccountDAOImpl acctImp) {
		super();
		Menu.acctImp = acctImp;
	}
	
	public static void newAccountMenu(Scanner input) {
		String firstName = "";
		String lastName = "";
		String username = "";
		String password = " ";
		
		try(Connection conn = DBConnectionClass.connect()) {
		
			do{
			System.out.println("Please enter your first name:");
			firstName = input.nextLine();
			
			if (firstName.length() > 50) {
				System.out.println("Sorry that name is way too long");
			}
			
			}while(firstName.length() > 50);
			
			
			do{
				System.out.println("Please enter your last name:");
				lastName = input.nextLine();
				
				if (lastName.length() > 50) {
					System.out.println("Sorry that name is way too long");
				}
				
				}while(lastName.length() > 50);
			
			do{
				System.out.println("Please enter a username:");
				username = input.nextLine();
				
				if (username.length() > 10) {
					System.out.println("Sorry please re-enter");
				}
				
			}while(username.length() > 10);
			
			do{
				System.out.println("Please enter the password you wish to use, not to exceed 8 characters:");
				password = input.nextLine();	
				
				if(password.length() > 8) {
					System.out.println("Your password is too long, plase re-enter");
				}
					
			}while(password.length() > 8);
			//cust = new Customer(first_name, last_name, username, password);
			//customer.addCustomer(cust);
			PreparedStatement prepareCust = conn.prepareStatement("INSERT INTO customer(first_name, last_name, username, pass_word) VALUES(?, ?, ?, ?)");
			prepareCust.setString(1, firstName);
			prepareCust.setString(2, lastName);
			prepareCust.setString(3, username);
			prepareCust.setString(4, password);
			prepareCust.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
			
	}
	
	
	public static void login(Scanner input) {
		ResultSet result;
		String role = null;
		String enterUser = null;
		String enterPassword = null;
		int select;
		//customer.getAllCustomers();
		
		try(Connection conn = DBConnectionClass.connect()) {
			
			do {
				System.out.println("Select 1 if your are a Customer, 2 if you are an Employee:");
				select = Integer.parseInt(input.nextLine());
				if(select == 1) {
					role = "customer";
				}else if (select == 2) {
					role = "employees";
				}else {
					System.out.println("Please select 1 or 2");
				
				}
				input.nextLine();
				System.out.println();
				
				do {
					System.out.println("Please enter your username:");
					enterUser = input.nextLine();
				}while(enterUser.length() > 10);
				
				
					System.out.println();
				do {
					System.out.println("Please enter your password:");
					enterPassword = input.nextLine();
				}while(enterPassword.length() > 8);
			
			System.out.println();
			if(role.equals("customer")) {
				PreparedStatement prep = conn.prepareStatement("SELECT username, pass_word FROM customer WHERE username = ? AND pass_word = ?");
				prep.setString(1, enterUser);
				prep.setString(2, enterPassword);
			
				result = prep.executeQuery();
			}else {
				PreparedStatement prep = conn.prepareStatement("SELECT username, pass_word FROM employees WHERE username = ? AND pass_word = ?");
				prep.setString(1, enterUser);
				prep.setString(2, enterPassword);
				
				result = prep.executeQuery();
			}
			
			if(!result.next()) {
				System.out.println("I'm sorry, please try again");
			}else {
				if(role.equals("employees")) {
				employeeMenu(enterUser);
				break;
				}else {
				customerMenu(enterUser);
				break;
				}
			}
				
			}while(!result.next());
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}catch(Exception e) {
			e.getMessage();
		}finally {
			 ;
		}
	}
	
	public static void customerMenu(String username) {
		Scanner input = new Scanner(System.in);
		//String username;
		int choice = 0;
		int acctNumber = 0;
		int acctNumber2 = 0;
		double balance = 0;
		double deposit = 0;
		double withdrawal = 0;
		double transfer = 0;
		boolean stop = false;
		
		do {
			System.out.println("Please make a selection below: "
				+ "\n" + "1. Apply for a new Account" 
				+ "\n" + "2. Make a Deposit"
				+ "\n" + "3. Make a Withdrawal"
				+ "\n" + "4. Transfer out funds"
				+ "\n" + "5. Receive a Transfer"
				+ "\n" + "6. Check your balance"
				+ "\n" + "7. Back to Main Menu");
			choice = Integer.parseInt(input.nextLine());
			switch(choice) {
			
			case 1:
				
				//acctNumber = createAccount(input, acctNumber);		
				System.out.println("Please enter the account number you wish to use for the new account:");
				acctNumber = Integer.parseInt(input.nextLine());
				System.out.println();
				System.out.println("Please enter your initial balance:");
				balance = Double.parseDouble(input.nextLine());
				acct = new Account(acctNumber, balance, username);
				acctImp.addPendingAccounts(acct);
				break;
			case 2: 
				System.out.println("Enter your account no: ");
				acctNumber = Integer.parseInt(input.nextLine());
				System.out.println("Enter the amount you wish to deposit: ");
				deposit = Double.parseDouble(input.nextLine());
				
				if(deposit > 0) {
					try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres", "Walmen01!")) {
						CallableStatement statement = conn.prepareCall("CALL deposit(?,?)");
						statement.setInt(1, acctNumber);
						BigDecimal bd = new BigDecimal(deposit);
						statement.setBigDecimal(2, bd);
						statement.executeUpdate();
						System.out.println(deposit + " deposited.");
					}catch(SQLException e) {
						e.printStackTrace();
						}
					}else {
						System.out.println("Invalid amount. returning to Customer menu.");
					}
				break;
				
			case 3:
				
				try(Connection conn = DBConnectionClass.connect()){
					System.out.println("Enter your account number: ");
					acctNumber = Integer.parseInt(input.nextLine());
					PreparedStatement state = conn.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
					state.setInt(1, acctNumber);
					ResultSet result = state.executeQuery();
					result.next();
					result.getDouble("balance");
					System.out.println("Enter the amount you wish to withdraw: ");
					withdrawal = Double.parseDouble(input.nextLine());
					if(withdrawal < 0 || withdrawal > result.getDouble("balance")) {
						
						System.out.println("Invalid amount. returning to Customer menu.");
					}else {
						CallableStatement call = conn.prepareCall("CALL withdrawal(?,?)");
						call.setInt(1, acctNumber);
						BigDecimal bd = new BigDecimal(withdrawal);
						call.setBigDecimal(2, bd);
						call.executeUpdate();
						System.out.println(withdrawal + " withdrawn.");
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
				break;
				
			case 4:
				try(Connection conn = DBConnectionClass.connect()) {
					System.out.println("Please enter your account number:");
					acctNumber = Integer.parseInt(input.nextLine());
					System.out.println("Enter the account number of the person you wish to transer to: ");
					acctNumber2 = Integer.parseInt(input.nextLine());
					System.out.println("Enter the amount you wish to transfer:");
					transfer = Double.parseDouble(input.nextLine());
					PreparedStatement state = conn.prepareStatement("SELECT balance FROM accounts WHERE account_number = ?");
					state.setInt(1, acctNumber);
					ResultSet result = state.executeQuery();
					result.next();
					result.getDouble("balance");
					if(transfer < 0 || transfer > result.getDouble("balance")) {
					
						System.out.println("Invalid amount. returning to Customer menu.");
					}else {
						CallableStatement call = conn.prepareCall("CALL transfer_out(?,?,?)");
						call.setInt(1, acctNumber);
						BigDecimal bd = new BigDecimal(transfer);
						call.setBigDecimal(2, bd);
						call.setInt(3, acctNumber2);
						call.executeUpdate();
						System.out.println(transfer + " transferred");
					}
				
					} catch (SQLException e) {
						e.printStackTrace();
				}
				break;
			case 5:
				try(Connection conn = DBConnectionClass.connect()) {
					System.out.println("Enter your account number to accept pending transfers: ");
					acctNumber = Integer.parseInt(input.nextLine());
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
					System.out.println("Transfer completed.");
				} catch (SQLException e) {
					e.printStackTrace();
					}
				break;
			case 6:
				try(Connection conn = DBConnectionClass.connect()) {
					System.out.println("Enter an account number to check your balance: ");
					acctNumber = Integer.parseInt(input.nextLine());
					PreparedStatement searchIn = conn.prepareStatement("SELECT account_number, balance FROM accounts WHERE account_number = ?");
					searchIn.setInt(1, acctNumber);
					ResultSet res = searchIn.executeQuery();
					
					while(res.next()) {
						System.out.println(res.getInt("account_number") + "\t"
								+ res.getDouble("balance"));
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
				break;
			case 7:
				stop = true;
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
			
			}while(!stop);
		
	}

	private static int createAccount(Scanner input, int acctNumber) {
		String username;
		double balance;
		try(Connection conn = DBConnectionClass.connect()) {
			do {
				System.out.println("Please enter your Username: ");
				username = input.nextLine();
				PreparedStatement getUserName = conn.prepareStatement("SELECT username FROM customer WHERE username = ?");
				getUserName.setString(1, username);
				ResultSet result = getUserName.executeQuery();
				
				if(!result.next()) {
					username = "";
					System.out.println("Username not found.");
					
				}
				
			}while(username.equals(""));
			
				//input.nextLine();
				System.out.println();

			while(acctNumber == 0) {
				System.out.println("Please enter your account number, no more or less than 5 characters:");
				acctNumber = (Integer) Integer.parseInt(input.nextLine());
				System.out.println("Enter your initial balance:");
				balance = Double.parseDouble(input.nextLine());
				acct = new Account(acctNumber, balance, username);
			if (acctNumber < 10000 || acctNumber > 99999) {
				acctNumber = 0;
				balance = 0;
				System.out.println("Sorry please re-enter:");
			}else {
				acctNumber = Integer.parseInt(input.nextLine());
				balance = Double.parseDouble(input.nextLine());
				//acct = new Account(acctNumber, balance, username);
				//acctImp.addPendingAccounts(acct);
				String insert = "INSERT INTO pending_accounts(account_number, balance, username) VALUES(?, ?, ?)";
				PreparedStatement putAccts = conn.prepareStatement(insert);
				putAccts.setInt(1, acctNumber);
				putAccts.setDouble(2, balance);
				putAccts.setString(3, username);
				putAccts.executeUpdate();
				 ;
				}
			}
			}catch(SQLException e) {
				e.getMessage();
			}
		return acctNumber;
	}
	
	public static void employeeMenu(String username) {
		Scanner input = new Scanner(System.in);
		//String username;
		int accountNumber = 0;
		int choice = 0;
		int approval = 0;
		boolean stop = false;
		
		
		do {
			System.out.println("Make Selection: "
				+ "\n" + "1. Approve or Reject Accounts" 
				+ "\n" + "2. View a Customer's Bank Accounts"
				+ "\n" + "3. View all Transactions"
				+ "\n" + "4. Back to Main Menu");
			choice = Integer.parseInt(input.nextLine());
			switch(choice) {
			
			case 1:
				try(Connection conn = DBConnectionClass.connect()) {
					PreparedStatement pend = conn.prepareStatement("SELECT * FROM pending_accounts");
					ResultSet result = pend.executeQuery();
					
					while(result.next()) {
						System.out.println(result.getInt(1)
								+ "\t" + result.getDouble(2)
								+ "\t" + result.getString(3));
						System.out.println();
						
						System.out.println("Enter the first account number for review: ");
						System.out.println();
						accountNumber = Integer.parseInt(input.nextLine());
						PreparedStatement prepareApproval = conn.prepareStatement("SELECT * FROM pending_accounts WHERE account_number = ?");
						prepareApproval.setInt(1, accountNumber);
						ResultSet set = prepareApproval.executeQuery();
						set.next();
						System.out.println(set.getInt(1) + "\t" + set.getDouble(2) + "\t" + set.getString(3));
						System.out.println();
						System.out.println("1. Approve"
								+ "\n" + "2.Reject");
						approval = Integer.parseInt(input.nextLine());
						if(approval == 2) {
							String delete = "DELETE FROM pending_accounts WHERE account_number = ?";
							PreparedStatement deleteAccts = conn.prepareStatement(delete);
							deleteAccts.setInt(1, accountNumber);
							deleteAccts.executeUpdate();
							System.out.println("Account rejected.");
							System.out.println();
						}else if(approval > 2 || approval < 1) {
							System.out.println("Please enter one of the valid options");
						}else {
							//PreparedStatement insertAccts = conn.prepareStatement("INSERT INTO accounts(account_number, balance, username) VALUES(?, ?, ?)");
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
						}
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
				break;
			case 2: 
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
					username = input.nextLine();
					PreparedStatement pare = conn.prepareStatement("SELECT account_number, balance FROM accounts WHERE username = ?");
					pare.setString(1, username);
					ResultSet res = pare.executeQuery();
					
					while(res.next()) {
						System.out.println(res.getInt(1) 
								+ "\t" + res.getDouble(2)
								); 
					}
					System.out.println();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				//transactions.getAllTransactions();
				try(Connection conn = DBConnectionClass.connect()) {
				//CallableStatement call = conn.prepareCall("Call get_all_transactions()");
				//ResultSet result = call.executeQuery();
					PreparedStatement getTransactions = conn.prepareStatement("SELECT * FROM transactions");
					ResultSet result = getTransactions.executeQuery();
				
				while(result.next()) {
					System.out.println(result.getInt(1) 
					+ "\t" + result.getInt(2) + "\t" + result.getDouble(3) + "\t" + result.getDouble(4) + "\t" +result.getDouble(5) + "\n");
				}
				
				}catch(SQLException e) {
					e.printStackTrace();
				}catch(Exception e) {
					e.printStackTrace();
				}
			
				System.out.println();
				break;
			case 4:
				stop = true;
				break;
			default:
				System.out.println("Invalid choice.");
				break;
			}
			}while(!stop);
		
			// ;
			}

		}
