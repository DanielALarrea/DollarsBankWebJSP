package com.dollarsbank.utility;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.repository.MockDatabase;

public class InputCheckUtility {
	
	// For ensuring positive number inputs related to funds
	public static boolean isPositiveNumber(float input) {
		boolean isPositive = false;
		if (input > 0) {
			isPositive = true;
		}
		
		return isPositive;
	}
	
	// For withdrawing and transferring funds
	public static boolean isValidWithdraw(float input, Account account) {
		boolean isValid = false;
		if(input < account.getSavings()) {
			isValid = true;
		}
		
		return isValid;
	}
	
	// For fund transfer and account creation
	public static boolean isExistingUser(String userID) {
		boolean userExists = false;
		for (Customer customer : MockDatabase.mockDB) {
			if (customer.getBankAccount().getUserId().equals(userID)) {
				userExists = true;
			}
		}
		
		return userExists;
	}
	
	// Check that the string is a float
	public static boolean isFloat(String input) {
		boolean isFloat = true;
		try {
			Float.parseFloat(input);
		} catch (NumberFormatException ex) {
			isFloat = false;
		}

		return isFloat;
	}
	
	// Criteria: At least 8 characters in length, 1 Lowercase character, 1 Uppercase character, 1 Special character
	public static boolean matchesPasswordCriteria(String password) {
		boolean matchesCriteria = true;
		if (password.length() != 8 
			|| password.equals(password.toLowerCase()) 
			|| password.equals(password.toUpperCase()) 
			|| password.matches("[A-Za-z0-9 ]*")) {
				matchesCriteria = false;
		}
		return matchesCriteria;
	}
	
	// Check that the user's phone number is in a valid form
	public static boolean isValidPhoneNum(String phoneNum) {
		boolean validPhone = false;
		String regexBasic = "^\\d{10}$";
		String regexExtra = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
		if ((phoneNum.matches(regexBasic) || phoneNum.matches(regexExtra)) && 
			(phoneNum.length() == 10 || phoneNum.length() == 12 || phoneNum.length() == 14)) {
			validPhone = true;
		}

		return validPhone;
	}

	// Convert a given phone number string into a specific form of writing a phone number
	public static String convertPhoneForm(String phoneNum) {
		String convertedForm = "";
		/* 3 main forms - Assuming input is in these forms
		 * 
		 * 1) No separation - 1234567890 - 10 length
		 * 
		 * 2) 3 groups of digits - 123 456 7890 - 12 length
		 * 
		 * 3) 3 groups, first has parentheses - (123) 456 7890 - 14 length
		 * 
		 * Desired result - 123-456-7890
		 */

		if (phoneNum.length() == 10) {
			convertedForm += phoneNum.substring(0, 3) + "-" + phoneNum.substring(3, 6) + "-" + phoneNum.substring(6, 10);
		} else if (phoneNum.length() == 12) {
			convertedForm += phoneNum.substring(0, 3) + "-" + phoneNum.substring(4, 7) + "-" + phoneNum.substring(8, 12);
		} else if (phoneNum.length() == 14) {
			convertedForm += phoneNum.substring(1, 4) + "-" + phoneNum.substring(6, 9) + "-" + phoneNum.substring(10, 14);
		}

		return convertedForm;
	}
	
	// Return account that matches user id
	public static Account accountLookUp(String userID) {
		Account foundAccount = new Account();
		for (Customer customer : MockDatabase.mockDB) {
			if (customer.getBankAccount().getUserId().equals(userID)) {
				foundAccount = customer.getBankAccount();
			}
		}
		
		return foundAccount;
	}
	
	public static Customer customerLookUp(Account account) {
		Customer foundCustomer = new Customer();
		for (Customer customer : MockDatabase.mockDB) {
			if (customer.getBankAccount() == account) {
				foundCustomer = customer;
			}
		}
		
		return foundCustomer;
	}
	
	public static Customer customerLookUpFromUser(String userID) {
		Customer foundCustomer = customerLookUp(accountLookUp(userID));
		
		return foundCustomer;
	}
	
	
	public static boolean validLogin(String user, String password) {
		boolean validLogin = false;
		for (Customer customer : MockDatabase.mockDB) {
			if (customer.getBankAccount().getUserId().equals(user) && customer.getBankAccount().getPassword().equals(password)) {
				validLogin = true;
			}
		}
		
		return validLogin;
	}
}
