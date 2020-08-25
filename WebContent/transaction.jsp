<%@page import="java.util.List"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Transactions</title>
<%@include file="bootstrap.html"%>
</head>
<body>
	<%@include file="header.html"%>
	<%
		String userid = request.getParameter("userid");
		Account account = InputCheckUtility.accountLookUp(userid);
		int numberOfTransactions = 5;
		if (request.getParameter("transactionCount") != null) {
			numberOfTransactions = Integer.parseInt(request.getParameter("transactionCount"));
		}
		List<String> transactions = account.xMostRecentTransaction(numberOfTransactions);

		String error = "";
		if ((String) request.getAttribute("error") != null) {
			error = (String) request.getAttribute("error");
		}
	%>
	<div class="text-center">
		<h3>Recent Transactions</h3>
		<div class="d-flex justify-content-center">

		<form action="transaction.jsp" method="post">
			<select name="transactionCount">
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
			</select>
			<input type="hidden" name="userid" value="<%=userid%>">
			<button class="btn btn-sm btn-primary">Display</button>
		</form>
		</div>
<div class="d-flex justify-content-center">
			<table class="mb-3">
				<%
					for (String transaction : transactions) {
				%>
					<tr class="border">
						<td><%=transaction%></td>
					</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>
	<form class="d-flex justify-content-center" action="home.jsp"
		method="post">
		<input type="hidden" name="userid" value="<%=userid%>">
		<button class="btn btn-lg btn-primary" style="width: 20%">Return</button>
	</form>

	<%@include file="footer.html"%>
</body>
</html>