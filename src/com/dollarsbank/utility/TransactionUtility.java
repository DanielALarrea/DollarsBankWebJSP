package com.dollarsbank.utility;

import java.time.LocalDateTime;

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
				+ "$" + account.getSavings() + " at " + LocalDateTime.now();
		account.pushRecentTransaction(transaction);
	}
	
	public static void givingFundTransfer(Account accountFrom, String userTo, float transfer) {
		String transaction = "User [" + accountFrom.getUserId() + "] transferred $" + transfer + " to user [" + userTo + "]";
		accountFrom.pushRecentTransaction(transaction);
	}
	
	public static void receivingFundTransfer(Account accountTo, String userFrom, float transfer) {
		String transaction = "User [" + accountTo.getUserId() + "] received $" + transfer + " from user [" + userFrom + "]";
		accountTo.pushRecentTransaction(transaction);
	}

}
