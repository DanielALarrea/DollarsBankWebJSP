<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.repository.MockDatabase"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Login</title>
<%@include file="bootstrap.html"%>
</head>
<body>
	<%@include file="header.html"%>
	<%
		if (!MockDatabase.checkInit()) {
		MockDatabase.initDatabase();
	}
	String error = "";
	if (request.getAttribute("error") != null) {
		error = (String) request.getAttribute("error");
	}
	%>
	<h2><%=error%></h2>

<div class="text-center border">
	<form class="form-signin" action="${pageContext.request.contextPath}/LoginServe" method="post">
		<h1 class="h3 mb-3">Customer Login</h1>
		<div class="form-group">
			<label for="inputUser" class="sr-only">User ID</label>
			<input type="text" style="width: 20%; height: 30px" name="userid" placeholder="User ID" required>
			<br>
			<label for="inputPass" class="sr-only">Password</label>
			<input type="text" style="width: 20%; height: 30px" name="password" placeholder="Password" required>
		</div>
		<button class="btn btn-lg btn-primary" style="width: 20%">Sign In</button>
	</form>
	<a class="btn btn-lg btn-primary" style="width: 20%" href="register.jsp">Register</a>
</div>

	<%@include file="footer.html"%>
</body>
</html>