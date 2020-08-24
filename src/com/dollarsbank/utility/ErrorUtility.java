package com.dollarsbank.utility;

public class ErrorUtility {
	
	public static String errorDepositPrefix() {
		return "Deposit failed: ";
	}
	
	public static String errorWithdrawPrefix() {
		return "Withdraw failed: ";
	}
	
	public static String errorTransferPrefix() {
		return "Transfer failed: ";
	}
	
	public static String errorAccountCreationPrefix() {
		return "Account creation failed: ";
	}
	
	public static String errorLogin() {
		return "No account found matching user and password";
	}
	
	public static String errorNotPositive() {
		return "Expected positive number input";
	}
	
	public static String errorNotEnough() {
		return "Not enough in balance for that transaction";
	}
	
	public static String errorUserNotFound() {
		return "No user found matching that ID";
	}
	
	public static String errorExistingUser() {
		return "User already exists with that ID";
	}
	
	public static String errorNotPasswordCriteria() {
		return "Password does not match criteria";
	}
	
	public static String errorNotPhone() {
		return "Phone number is not a valid form";
	}
	
}
