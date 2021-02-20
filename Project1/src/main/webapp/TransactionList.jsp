<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<table border="1">
		<tr>
			<th>Account Number</th>
			<th>Deposits</th>
			<th>Withdrawals</th>
			<th>Transfer Ins</th>
			<th>Transfer Outs</th>
		</tr>
		<c:forEach var="actions" items="${transactionList}">
			<tr>
				<td>${actions.getAccount()}</td>
				<td>${actions.getDeposit()}</td>
				<td>${actions.getWithdrawal()}</td>
				<td>${actions.getTransferIn()}</td>
				<td>${actions.getTransferOut()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>