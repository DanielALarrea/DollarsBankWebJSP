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
		String success = "";
		if (request.getAttribute("success") != null) {
			success = (String) request.getAttribute("success");
		}
	%>

<div class="border">
	<form class="form-horizontal" action="${pageContext.request.contextPath}/LoginServe" method="post">
		<h1 class="h3 mb-3 text-center">Customer Login</h1>
		<div class="form-group">
			<label for="inputUser" class="col-sm-2 control-label">User ID</label>
			<div class="col-sm-10">
				<input class="form-control" id="inputUser" type="text" name="userid" placeholder="User ID" required>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPass" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input class="form-control" id="inputPass" type="password" name="password" placeholder="Password" required>
			</div>
		</div>
		<div class="text-danger"><%=error%></div>
		<div class="text-success"><%=success %></div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-lg btn-primary">Sign In</button>
				<a class="btn btn-lg btn-primary" href="register.jsp">Register</a>
				<a class="btn btn-lg btn-primary" href="forgotpass.jsp">Forgot Password?</a>
			</div>	
		</div>
	</form>
</div>

	<%@include file="footer.html"%>
</body>
</html>