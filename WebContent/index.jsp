<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.repository.MockDatabase"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Login</title>
</head>
<body>
<%
	if(!MockDatabase.checkInit()) {
		MockDatabase.initDatabase();
	}
	// Check that receiving error back
	// Print based on error
	// Do in all pages with input
	// Fill in textboxes with previous input?
	String error = "";
	if(request.getAttribute("error") != null) {
		error = (String)request.getAttribute("error");
	}
%>

<h1>Hello there!</h1>
<h2><%=error%></h2>
<form action="${pageContext.request.contextPath}/LoginServe" method="post">
User ID: <input type="text" name="userid"> <br>
Password: <input type="text" name="password"> <br>
<input type="submit" value="Login">
</form>
</body>
</html>