<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Transfer</title>
<%@include file="bootstrap.html"%>
</head>
<body>
	<%@include file="header.html"%>
	<%
		String userid = request.getParameter("userid");
		Account account = InputCheckUtility.accountLookUp(userid);

		float balance = account.getSavings();

		String errorUser = "";
		if ((String) request.getAttribute("errorUser") != null) {
			errorUser = (String) request.getAttribute("errorUser");
		}
		
		String errorNumber = "";
		if ((String) request.getAttribute("errorNumber") != null) {
			errorNumber = (String) request.getAttribute("errorNumber");
		}
	%>

<div class="text-center border">
	<form action="${pageContext.request.contextPath}/TransferServe"
		method="post">
		<h1 class="h3 mb-3">Balance: $<%=balance%></h1>
		<div class="d-flex justify-content-center">
			<label class="col-sm-1">Transfer</label>
			<div class="form-group col-sm-3">
				<input class="form-control" type="number" name="transfer" placeholder="10.00" required>
				<div class="text-danger"><%=errorNumber%></div>
			</div>
			
		</div>
		<div class="d-flex justify-content-center">
		<label class="col-sm-1">To</label>
			<div class="form-group col-sm-3">
				<input class="form-control" type="text" name="userTransfer" placeholder="User" required>
				<div class="text-danger"><%=errorUser%></div>
			</div>
		</div>
		<input type="hidden" name="userid" value="<%=userid%>">
		<button class="btn btn-lg btn-primary" style="width: 20%">Transfer</button>
	</form>
</div>

	<form class="d-flex justify-content-center" action="home.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>">
		<button class="btn btn-lg btn-primary" style="width: 20%">Return</button>
	</form>

	<%@include file="footer.html"%>
</body>
</html>