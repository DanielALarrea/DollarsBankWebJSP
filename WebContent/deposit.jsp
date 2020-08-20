<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Deposit</title>
<%@include file="bootstrap.html"%>
</head>
<body>
	<%@include file="header.html"%>
	<%
		String userid = request.getParameter("userid");
		Account account = InputCheckUtility.accountLookUp(userid);

		float balance = account.getSavings();

		String error = "";
		if ((String) request.getAttribute("error") != null) {
			error = (String) request.getAttribute("error");
		}
	%>
	<h2>Balance: $<%=balance%></h2>
	<h2><%=error%></h2>
	
	<form action="${pageContext.request.contextPath}/DepositServe"
		method="post">
		<input type="submit" value="Deposit"> 
		<input type="number" name="deposit"> <input type="hidden" name="userid" value="<%=userid%>">
	</form>

	<form action="home.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>">
		<input type="submit" value="Return">
	</form>

	<%@include file="footer.html"%>
</body>
</html>