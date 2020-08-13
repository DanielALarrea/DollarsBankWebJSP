<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Other Page</title>
</head>
<body>
	<%
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String passwordHide = "";
		if(password != null) {
		for(int i = 0; i < password.length(); i++) {
			passwordHide += "*";
		}
		}
		float balance = 10;
		if (request.getParameter("balance") != null) {
			balance = Float.parseFloat(request.getParameter("deposit")) + Float.parseFloat(request.getParameter("balance"));
		}
	%>
	
	<h1>Hello, <%=userid%></h1>
	<h2>Your password is <%=passwordHide%></h2>
	<h3>Your balance is <%=balance%></h3>
	
	<form action="other.jsp" method="post">
		<input type="submit" value="Deposit">
		<input type="number" name="deposit">
		<input type="hidden" name="balance" value="<%=balance%>">
		<input type="hidden" name="userid" value="<%=userid%>">
		<input type="hidden" name="password" value="<%=password%>">
	</form>
	
	<form action="index.jsp" method="post">
		<input type="submit" value="Return">
	</form>
</body>
</html>