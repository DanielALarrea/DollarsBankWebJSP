package com.dollarsbank.utility;

import com.dollarsbank.model.Account;

public class TransactionUtility {
	
	public static void depositTransaction(float deposit, Account account) {
		String transaction = "Deposited $" + deposit;
		account.pushRecentTransaction(transaction);
	}
	
	public static void withdrawTransaction(float withdraw, Account account) {
		String transaction = "Withdrew $" + withdraw;
		account.pushRecentTransaction(transaction);
	}
	
	public static void accountCreation(Account account) {
		String transaction = "Created account for user "
				+ "[" + account.getUserId() + "] with initial deposit of "
				+ "$" + account.getSavings();
		account.pushRecentTransaction(transaction);
	}
	
	public static void givingFundTransfer(Account accountFrom, String userTo, float transfer) {
		String transaction = "Transferred $" + transfer + " to user [" + userTo + "]";
		accountFrom.pushRecentTransaction(transaction);
	}
	
	public static void receivingFundTransfer(Account accountTo, String userFrom, float transfer) {
		String transaction = "Received $" + transfer + " from user [" + userFrom + "]";
		accountTo.pushRecentTransaction(transaction);
	}
	
	public static void updatedPassword(Account account, String user) {
		String transaction = "Updated password for user [" + user + "]";
		account.pushRecentTransaction(transaction);
	}
	
	public static void updatedCustomerDetails(Account account, String combinedTransactions) {
		String transaction = "Updated following account details:" + combinedTransactions;
		account.pushRecentTransaction(transaction);
	}
	
	public static String updatedCustomerName(String name) {
		return "-Updated name to " + name;
	}
	
	public static String updatedCustomerAddress(String address) {
		return "-Updated address to " + address;
	}

	public static String updatedCustomerPhone(String contactNum) {
		return "-Updated contact number to " + contactNum;
	}

}
