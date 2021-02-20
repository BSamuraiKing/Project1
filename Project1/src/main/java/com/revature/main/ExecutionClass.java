package com.revature.main;

import java.util.Scanner;



import java.sql.*;

public class ExecutionClass {
	
	//gger logger = LogManager.getLogger(ExecutionClass.class);

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean stop = false;
		int choice;
		try {
			do {
				System.out.println("Hello, and welcome to Sandwich Bank! Please make a selection below: "
				+ "\n" + "1. New Customer" 
				+ "\n" + "2. Login" 
				+ "\n" + "3. Exit.");
				choice = Integer.parseInt(scan.nextLine());
				
				switch(choice) {
				case 1:
					Menu.newAccountMenu(scan);
					break;
				case 2:
					Menu.login(scan);
					break;
				case 3:
					stop = true;
					break;
				default:
					System.out.println("Invalid choice");
					break;
				
				}
				
			}while(!stop);

		}catch(NumberFormatException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			scan.close();
		
		}
		
	}

}
