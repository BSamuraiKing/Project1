<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sandwich Bank</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head>
<body>
	<form action="AccountReview" method="post">
		<table border="1">
		<tr>
			<th>Account Number</th>
			<th>Initial Balance</th>
			<th>User Name</th>
			<th>Select to Approve</th>
		</tr>
		<c:forEach var="acct" items="${accountList}">
			<tr>
				<td>${acct.getAcctno()}</td>
				<td>${acct.getBalance()}</td>
				<td>${acct.getUsername()}</td>
				<td><input type="checkbox" name="approvedAccounts"></td>
			</tr>
		</c:forEach>
	</table>
		<input type="Submit" value="Submit"> <a href="EmployeePage.jsp"></a>
	</form>
</body>
</html>