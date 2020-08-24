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
		List<String> transactions = account.xMostRecentTransaction(5);

		String error = "";
		if ((String) request.getAttribute("error") != null) {
			error = (String) request.getAttribute("error");
		}
	%>
<div class="text-center">
<h3>5 Most Recent Transactions</h3>
<div class="d-flex justify-content-center">
	
	<table class="mb-3">
		<%
			for(String transaction: transactions) {
			%>
				<tr class="border"><td> <%=transaction %></td></tr>
			<% 
			}
		%>
	</table>
	</div>
</div>
	<form class="d-flex justify-content-center" action="home.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>">
		<button class="btn btn-lg btn-primary" style="width: 20%">Return</button>
	</form>

	<%@include file="footer.html"%>
</body>
</html>