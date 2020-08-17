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
		if(input >= account.getSavings()) {
			isValid = true;
		}
		
		return isValid;
	}
	
	// For fund transfer
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
}
