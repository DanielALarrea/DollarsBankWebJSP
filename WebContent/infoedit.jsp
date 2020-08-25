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
			<table>
				<tr class="border"><td class="border">User ID</td><td><%=account.getUserId() %></td></tr>
				<tr class="border"><td class="border">Balance</td><td>$<%=account.getSavings() %></td></tr>
			</table>
			</div>
		<div class="d-flex justify-content-center">
			<form action="${pageContext.request.contextPath}/InformationServe"
				method="post">
				<div class="form-group">
					<input type="text" name="name" value="<%=customer.getName() %>">
				</div>
				<div class="form-group">
					<input type="text" name="address" value="<%=customer.getAddress() %>"> 
				</div>
				<div class="form-group">
					<input type="text" name="contactNum" value="<%=customer.getContactNum() %>">
				</div>
				<div><%=errorPhone %></div>
				<input type="hidden" name="userid" value="<%=userid%>">
				<button class="btn btn-lg btn-primary">Save</button>
			</form>
		</div>
	</div>

	

	<form class="d-flex justify-content-center" action="home.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userid%>">
		<button class="btn btn-lg btn-primary" style="width: 20%">Return</button>
	</form>

	<%@include file="footer.html"%>

</body>
</html>