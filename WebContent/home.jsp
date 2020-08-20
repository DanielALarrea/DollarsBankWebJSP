<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Home</title>
<%@include file="bootstrap.html"%>
</head>
<body>
	<%@include file="header.html"%>
	<%
		String userid = request.getParameter("userid");
		Account account = InputCheckUtility.accountLookUp(userid);
	%>

	<h1>Hello, <%=userid%></h1>

	<form action="deposit.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>"> 
		<input type="submit" value="Deposit">
	</form>

	<form action="deposit.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>"> 
		<input type="submit" value="Withdraw">
	</form>

	<form action="deposit.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>"> 
		<input type="submit" value="Transfer">
	</form>

	<form action="deposit.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>"> 
		<input type="submit" value="5 Recent Transactions">
	</form>

	<form action="deposit.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>"> 
		<input type="submit" value="Display Customer Information">
	</form>

	<form action="index.jsp" method="get">
		<input type="submit" value="Sign Out">
	</form>

	<%@include file="footer.html"%>
</body>
</html>