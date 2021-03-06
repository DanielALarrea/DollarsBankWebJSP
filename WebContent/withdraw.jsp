<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Withdraw</title>
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
	
<div class="text-center border">
	<form action="${pageContext.request.contextPath}/WithdrawServe"
		method="post">
		<h1 class="h3 mb-3">Balance: $<%=balance%></h1>
		<div class="text-danger"><%=error%></div>
		<div class="d-flex justify-content-center">
			<div class="form-group col-sm-3">
				<input class="form-control" type="number" name="withdraw" placeholder="10.00" required>
				<input type="hidden" name="userid" value="<%=userid%>">
			</div>
		</div>
		<button class="btn btn-lg btn-primary" style="width: 20%">Withdraw</button>
	</form>
	</div>

	<form class="d-flex justify-content-center" action="home.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>">
		<button class="btn btn-lg btn-primary" style="width: 20%">Return</button>
	</form>

	<%@include file="footer.html"%>
</body>
</html>