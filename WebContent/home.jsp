<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Home</title>
</head>
<body>
	<%
		String userid = request.getParameter("userid");
		Account account = InputCheckUtility.accountLookUp(userid);
		
		/*
		String password = request.getParameter("password");
		String passwordHide = "";
		if(password != null) {
		for(int i = 0; i < password.length(); i++) {
			passwordHide += "*";
		}
		}
		float balance = account.getSavings();
		if (request.getParameter("balance") != null) {
			balance = Float.parseFloat(request.getParameter("deposit")) + Float.parseFloat(request.getParameter("balance"));
		}
		*/
	%>
	
	<h1>Hello, <%=userid%></h1>
	
	<!--
	<h2>Your password is <passwordHide%></h2>
	<h3>Your balance is <balance%></h3>
	
	
	<form action="home.jsp" method="post">
		<input type="submit" value="Deposit">
		<input type="number" name="deposit">
		<input type="hidden" name="balance" value="<balance%>">
		<input type="hidden" name="userid" value="<userid%>">
		<input type="hidden" name="password" value="<password%>">
	</form>
	 -->
	 
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
	
</body>
</html>