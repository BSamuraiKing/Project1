<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sandwich Bank</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head>
<body>
	<form action="EmployeeMain" method="post">
		<h3>Employee Menu</h3>
		<table>
			<tr>
				<td><label for="transactChoice">Transactions</label></td>
				<td>
					<select name ="transactChoice" id="transactChoice">
					<option value="review">Review Accounts</option>
					<option value="viewAccounts">View Client Accounts</option>
					<option value="viewAll">View All Transactions</option>
					</select>	
				</td>
			</tr>
			<tr>
				<td>Enter information below for searching for a customer's accounts</td>
			</tr>
			<tr>
				<td><label for="firstName">First Name</label></td>
				<td><input type="text" name="firstName"></td>
			</tr>
			<tr>
				<td><label for="LastName">Last Name</label></td>
				<td><input type="text" name ="lastName"></td>
			</tr>
			<tr>
				<td><label for="username">User Name</label></td>
				<td><input type="text" name ="username"></td>
			</tr>
		</table>
	<input type="Submit" value="Submit">
		<a href="index.jsp">Cancel</a>
	</form>
</body>
</html>