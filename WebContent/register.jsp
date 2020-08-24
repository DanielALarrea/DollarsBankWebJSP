<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Register</title>
<%@include file="bootstrap.html"%>
</head>
<body>
	<%@include file="header.html"%>
	<%		
		String errorPhone = "";
		if ((String) request.getAttribute("errorPhone") != null) {
			errorPhone = (String) request.getAttribute("errorPhone");
		}

		String errorUser = "";
		if ((String) request.getAttribute("errorUser") != null) {
			errorUser = (String) request.getAttribute("errorUser");
		}
		
		String errorPass = "";
		if ((String) request.getAttribute("errorPass") != null) {
			errorPass = (String) request.getAttribute("errorPass");
		}
		
		String errorNumber = "";
		if ((String) request.getAttribute("errorNumber") != null) {
			errorNumber = (String) request.getAttribute("errorNumber");
		}
	%>
	
<div class="border">
	<form action="${pageContext.request.contextPath}/RegisterServe" method="post">
		<h1 class="h3 mb-3 text-center">Register With DollarsBank!</h1>
		
		<div class="form-group form-inline">
			<label for="inputName" class="col-sm-2 control-label">Name</label>
			<div class="col-sm-10">
				<input class="form-control" id="inputName" type="text" name="name" placeholder="Name" required>
			</div>
		</div>
		
		<div class="form-group form-inline">
			<label for="inputAddress" class="col-sm-2 control-label">Address</label>
			<div class="col-sm-10">
				<input class="form-control" id="inputAddress" type="text" name="address" placeholder="Address" required>
			</div>
		</div>
		
		<div class="form-group form-inline">
			<label for="inputPhone" class="col-sm-2 control-label">Contact Number</label>
			<div class="col-sm-3">
				<input class="form-control" id="inputPhone" type="text" name="contactNum" placeholder="123-456-7890" required>
			</div>
			<div class="col-sm-7 text-danger"><%=errorPhone %></div>
		</div>
		
		<div class="form-group form-inline">
			<label for="inputUser" class="col-sm-2 control-label">User ID</label>
			<div class="col-sm-3">
				<input class="form-control" id="inputUser" type="text" name="userID" placeholder="User ID" required>
			</div>
			<div class="col-sm-7 text-danger"><%=errorUser %></div>
		</div>
		
		<div class="form-group form-inline">
			<label for="inputPass" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-3">
				<input class="form-control" id="inputPass" type="password" name="password" placeholder="Password" required>
			</div>
			<div class="col-sm-7 text-danger"><%=errorPass %></div>
		</div>
		
		<div class="form-group form-inline">
			<label for="inputInitDepo" class="col-sm-2 control-label">Initial Deposit</label>
			<div class="col-sm-3">
				<input class="form-control" id="inputInitDepo" type="number" name="intialDeposit" placeholder="10.00" required>
			</div>
			<div class="col-sm-7 text-danger"><%=errorNumber %></div>
		</div>
		
		<div class="form-group form-inline">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-lg btn-primary">Register</button>
				<a class="btn btn-lg btn-primary" href="index.jsp">Return</a>
			</div>
		</div>
	</form>
</div>

	

	<%@include file="footer.html"%>
</body>
</html>