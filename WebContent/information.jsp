<%@page import="com.dollarsbank.model.Customer"%>
<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Customer Information</title>
<%@include file="bootstrap.html"%>
</head>
<body>
	<%@include file="header.html"%>
	<%
		String userid = request.getParameter("userid");
		Account account = InputCheckUtility.accountLookUp(userid);
		Customer customer = InputCheckUtility.customerLookUp(account);
		
		String success = "";
		if (request.getAttribute("success") != null) {
			success = (String) request.getAttribute("success");
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
		<div class="d-flex justify-content-center">
			<div class="col-sm-3">
				<table class="table table-sm">
					<tr class="border"><td class="border">Name</td><td><%=customer.getName() %></td></tr>
					<tr class="border"><td class="border">Address</td><td><%=customer.getAddress() %></td></tr>
					<tr class="border"><td class="border">Contact Number</td><td><%=customer.getContactNum() %></td></tr>
				</table>
			</div>
		</div>
	</div>
	<div class="text-center text-success"><%=success %></div>
	<form class="d-flex justify-content-center" action="infoedit.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>">
		<button class="btn btn-lg btn-primary" style="width: 20%">Edit</button>
	</form>
	
	<form class="d-flex justify-content-center" action="updatepass.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>">
		<button class="btn btn-lg btn-primary" style="width: 20%">Update Password</button>
	</form>

	<form class="d-flex justify-content-center" action="home.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>">
		<button class="btn btn-lg btn-primary" style="width: 20%">Return</button>
	</form>

	<%@include file="footer.html"%>

</body>
</html>