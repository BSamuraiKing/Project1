# Project1
This is my current iteration of the Project 1 Banking application. 

Technologies Used:
  Java
  JDBC
  Java Servlets
  JSP
  Postgres
  Apache Tomcat
  
Ready Features:
  Customer Menu and menu actions
  Employee Menu and menu actions
  Navigation back to home page from Login page
  
To Do List:
  Configure Login page to send User to either Customer page or Employee page
  Configure links in Customer/Employee pages to go back to relevant pages
  Create a Log Out link to destroy session and  return to homepage
  Add CSS styling either via Bootstrap or through a custom CSS file
  
  
Getting Started:
  The project currently runs as a Maven project and requires the Eclipse Enterprise Edition and Apache Tomcat 9 to run.
  The build path will need to be reconfigured to match the relevant paths and dependencies on a new localhost.
  Additonally the JDBC connection is for a Postgres database with distinct Customer, Employee, Account, Transaction, and pending acouunt/transaction tables.

Usage:
  Once all required technologies are installed, you may run the project from Eclipse. Run it on a server. From there the main home page will come up in Eclipse. If desired you may go to a web browser and enter localhost:8080 to view the web pages in a browser of your choice.
