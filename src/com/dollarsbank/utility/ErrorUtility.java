package com.dollarsbank.utility;

public class ErrorUtility {

	public static String errorNotNumber() {
		return "Expected number input";
	}
	
	public static String errorNotPositive() {
		return "Expected positive number";
	}
	
	public static String errorNotEnough() {
		return "Not enough in balance for that transaction";
	}
	
	public static String errorUserNotFound() {
		return "No user found matching that ID";
	}
	
}
