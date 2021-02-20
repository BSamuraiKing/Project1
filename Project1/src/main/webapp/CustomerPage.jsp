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
	<div>
		<form action="CustomerMain" method="post">
			<h3>Customer Transactions</h3>
			<table>
				<tr>
				<td><label for="custChoice">Choose Your Math</label></td>
				<td>
					<select name ="custChoice">
					<option value="deposit">Make A Deposit</option>
					<option value="withdrawal">Make A Withdrawal</option>
					<option value="transferOut">Make A Transfer</option>
					<option value="transferIn">Approve A Transfer</option>
					<option value="balanceCheck">Check Your Balance</option>
					<option value="newAcct">Create A New Account</option>
					</select>
				</td>
				</tr>
				<tr>
					<td><label for="accountNumber">Account Number</label></td>
					<td><input type="text" name="accountNumber"></td>
				</tr>
				<tr>
					<td><label for="amount">Amount</label></td>
					<td><input type="text" name ="amount"></td>
				</tr>
				<tr>
					<td><label for="toAccount">To Account</label></td>
					<td><input type="text" name="toAccount"></td>
				</tr>
				<tr>
					<td><label for="username">User Name</label></td>
					<td><input type="text" name="username"></td>
				</tr>
			</table>
			<input type="Submit" value="Submit">
		</form>
	
	</div>
</body>
</html>