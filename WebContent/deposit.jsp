<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Deposit</title>
</head>
<body>
	<%
		String userid = request.getParameter("userid");
		Account account = InputCheckUtility.accountLookUp(userid);
		
		float balance = account.getSavings();
		
		String error = request.getParameter("error");
	%>
	
	<form action="${pageContext.request.contextPath}/DepositServe" method="post">
		<input type="submit" value="Deposit">
		<input type="number" name="deposit">
		<input type="hidden" name="userid" value="<%=userid%>">
	</form>
	
	<form action="home.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>">
		<input type="submit" value="Return">
	</form>
</body>
</html>