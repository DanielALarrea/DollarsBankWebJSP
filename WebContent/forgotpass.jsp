<%@page import="com.dollarsbank.utility.InputCheckUtility"%>
<%@page import="com.dollarsbank.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DollarsBank Forgot Password</title>
<%@include file="bootstrap.html"%>
</head>
<body>
	<%@include file="header.html"%>
	<%	
		String userid = request.getParameter("userid");
		Account account = InputCheckUtility.accountLookUp(userid);
		
		String errorPass = "";
		String errorMatch = "";
		String errorUser = "";
		if ((String) request.getAttribute("errorPass") != null) {
			errorPass = (String) request.getAttribute("errorPass");
		}
		if ((String) request.getAttribute("errorMatch") != null) {
			errorMatch = (String) request.getAttribute("errorMatch");
		}
		if ((String) request.getAttribute("errorUser") != null) {
			errorUser = (String) request.getAttribute("errorUser");
		}
	%>
	
<div class="border">
	<form action="${pageContext.request.contextPath}/PasswordServe" method="post">
		<h1 class="h3 mb-3 text-center">Register With DollarsBank!</h1>
		<div class="form-group">
			<label for="inputUser" class="col-sm-2 control-label">User ID</label>
			<div class="col-sm-10">
				<input class="form-control" id="inputUser" type="text" name="userid" placeholder="User ID" required>
			</div>
			<div class="col-sm-7 text-danger"><%=errorUser %></div>
		</div>
		<div class="form-group">
			<label for="inputPass" class="col-sm-2 control-label">New Password</label>
			<div class="col-sm-3">
				<input class="form-control" id="inputPass" type="password" name="password" placeholder="Password" required>
			</div>
			<span id="helpBlock" class="help-block col-sm-12">Password must be 8 characters long and contain at least one of each: Uppercase, Lowercase, Special</span>
			<div class="col-sm-7 text-danger"><%=errorPass %></div>
		</div>
		
		<div class="form-group">
			<label for="inputPass" class="col-sm-2 control-label">Verify Password</label>
			<div class="col-sm-3">
				<input class="form-control" id="inputPass" type="password" name="passwordVerify" placeholder="Password" required>
			</div>
			<div class="col-sm-7 text-danger"><%=errorMatch %></div>
		</div>
		
		<div class="form-group form-inline">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="hidden" name="formPage" value="forgotpass.jsp">
				<input type="hidden" name="returnPage" value="index.jsp">
				<button class="btn btn-lg btn-primary">Update</button>
				<a class="btn btn-lg btn-primary" href="index.jsp">Return</a>
			</div>
		</div>
	</form>
</div>
	<%@include file="footer.html"%>
</body>
</html>