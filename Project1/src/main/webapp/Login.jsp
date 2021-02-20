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
	<form action="LoginServlet" method="post">
		<table>
			<tr>
				<td>Select Your Role:</td>
				<td>
				<label>Customer</label>
				<input type="radio" name="role" value="Customer"/>
				</td>
				<td>
				<label>Employee</label>
				<input type="radio" name="role" value="Employee"/>
				</td>
			</tr>
			<tr>
				<td>Enter Your New Username :</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>Enter Password :</td>
				<td> <input type="password" name="password"></td>
			</tr>
		</table>
		<input type ="submit" value="Submit">
		<a href="index.jsp">Cancel</a>
	</form>
</body>
</html>