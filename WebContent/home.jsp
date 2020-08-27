<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Home</title>
<%@include file="bootstrap.html"%>
</head>
<body>
	<%@include file="header.html"%>
	<%
		String userid = request.getParameter("userid");
		Account account = InputCheckUtility.accountLookUp(userid);
	
		float balance = account.getSavings();
	
		String success = "";
		if (request.getAttribute("success") != null) {
			success = (String) request.getAttribute("success");
		}
	%>

	<h1 class="text-center">Hello, <%=userid%></h1>

	<div class="text-center border">
		<div class="border">
			<h3>Balance: $<%=balance%></h3>
			<ul class="list-inline">
				<li class="list-inline-item">
					<form action="deposit.jsp" method="post">
						<input type="hidden" name="userid" value="<%=userid%>">
						<button class="btn btn-lg btn-primary">Deposit</button>
					</form>
				</li>
				<li class="list-inline-item">
					<form action="withdraw.jsp" method="post">
						<input type="hidden" name="userid" value="<%=userid%>">
						<button class="btn btn-lg btn-primary">Withdraw</button>
					</form>
				</li>
				<li class="list-inline-item">
					<form action="transfer.jsp" method="post">
						<input type="hidden" name="userid" value="<%=userid%>">
						<button class="btn btn-lg btn-primary">Transfer</button>
					</form>
				</li>
			</ul>
			<h2 class="text-center text-success"><%=success %></h2>
		</div>
		<div class="border">
			<h3>Account Information</h3>
			<ul class="list-inline">
				<li class="list-inline-item">
					<form action="transaction.jsp" method="post">
						<input type="hidden" name="userid" value="<%=userid%>">
						<button class="btn btn-lg btn-primary">Recent Transactions</button>
					</form>
				</li>
				<li class="list-inline-item">
					<form action="information.jsp" method="post">
						<input type="hidden" name="userid" value="<%=userid%>">
						<button class="btn btn-lg btn-primary">Customer Information</button>
					</form>
				</li>
			</ul>
		</div>
		<a class="btn btn-lg btn-primary" style="width: 20%" href="index.jsp">Sign Out</a>
	</div>
	<%@include file="footer.html"%>
</body>
</html>