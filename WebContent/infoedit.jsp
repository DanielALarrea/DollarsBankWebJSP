<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.model.Customer"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Edit Information</title>
<%@include file="bootstrap.html"%>
</head>
<body>
	<%@include file="header.html"%>
	<%
		String userid = request.getParameter("userid");
		Account account = InputCheckUtility.accountLookUp(userid);
		Customer customer = InputCheckUtility.customerLookUp(account);
		
		String errorPhone = "";
		if ((String) request.getAttribute("errorPhone") != null) {
			errorPhone = (String) request.getAttribute("errorPhone");
		}

	%>
	<div class="text-center">
		<h3>Customer Information</h3>
		<div class="d-flex justify-content-center">
			<div class="col-sm-3">
				<table class="table table-sm">
					<tr class="border"><td class="border">User ID</td><td><%=account.getUserId() %></td></tr>
					<tr class="border"><td class="border">Balance</td><td>$<%=account.getSavings() %></td></tr>
				</table>
			</div>
		</div>
		<form action="${pageContext.request.contextPath}/InformationServe"
			method="post">
			<div class="d-flex justify-content-center">
				<label class="col-sm-1">Name</label>
				<div class="form-group">
					<input class="form-control" type="text" name="name"
						value="<%=customer.getName()%>" required>
				</div>
			</div>
			<div class="d-flex justify-content-center">
				<label class="col-sm-1">Address</label>
				<div class="form-group">
					<input class="form-control" type="text" name="address"
						value="<%=customer.getAddress()%>" required>
				</div>
			</div>
			<div class="d-flex justify-content-center">
				<label class="col-sm-1">Contact Number</label>
				<div class="form-group">
					<input class="form-control" type="text" name="contactNum"
						value="<%=customer.getContactNum()%>" required>
				</div>
			</div>
			<div class="d-flex justify-content-center text-danger"><%=errorPhone%></div>
			<input type="hidden" name="userid" value="<%=userid%>">
			<button class="btn btn-lg btn-primary">Save</button>
		</form>
	</div>

	<form class="d-flex justify-content-center" action="home.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>">
		<button class="btn btn-lg btn-primary" style="width: 20%">Return</button>
	</form>

	<%@include file="footer.html"%>

</body>
</html>