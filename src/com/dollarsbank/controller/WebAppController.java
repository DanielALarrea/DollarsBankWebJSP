package com.dollarsbank.controller;

import com.dollarsbank.model.Account;
import com.dollarsbank.repository.MockDatabase;
import com.dollarsbank.utility.ErrorUtility;
import com.dollarsbank.utility.InputCheckUtility;
import com.dollarsbank.utility.TransactionUtility;

public class WebAppController {

	public static void depositToAccount(float deposit, Account account) {
		boolean noErrorNumber = false;
		
		if(InputCheckUtility.isPositiveNumber(deposit)) {
			noErrorNumber = true;
		} else {
			System.out.println(ErrorUtility.errorDepositPrefix() + ErrorUtility.errorNotPositive());
		}
		
		if(noErrorNumber) {
			account.deposit(deposit);
			TransactionUtility.depositTransaction(deposit, account);
		}
	}

	public static void withdrawFromAccount(float withdraw, Account account) {
		boolean noErrorNumber = false;
		
		if(InputCheckUtility.isPositiveNumber(withdraw)) {
			if(InputCheckUtility.isValidWithdraw(withdraw, account)) {
				noErrorNumber = true;
			} else {
				System.out.println(ErrorUtility.errorWithdrawPrefix() + ErrorUtility.errorNotEnough());
			}
		} else {
			System.out.println(ErrorUtility.errorWithdrawPrefix() + ErrorUtility.errorNotPositive());
		}
		
		if(noErrorNumber) {
			account.withdraw(withdraw);
			TransactionUtility.withdrawTransaction(withdraw, account);
		}
	}

	public static void transferFunds(Account accountFrom, String userTo, float transfer) {
		Account accountTo = new Account();
		boolean noErrorExistingUser = false;
		boolean noErrorNumber = false;
		
		if(InputCheckUtility.isExistingUser(userTo)) {
			accountTo = InputCheckUtility.accountLookUp(userTo);
			noErrorExistingUser = true;
		} else {
			System.out.println(ErrorUtility.errorTransferPrefix() + ErrorUtility.errorUserNotFound());
		}
		
		if(InputCheckUtility.isPositiveNumber(transfer)) {
			if(InputCheckUtility.isValidWithdraw(transfer, accountFrom)) {
				noErrorNumber = true;
			} else {
				System.out.println(ErrorUtility.errorTransferPrefix() + ErrorUtility.errorNotEnough());
			}
		} else {
			System.out.println(ErrorUtility.errorTransferPrefix() + ErrorUtility.errorNotPositive());
		}
		
		if(noErrorExistingUser && noErrorNumber) {
			accountFrom.withdraw(transfer);
			TransactionUtility.givingFundTransfer(accountFrom, userTo, transfer);
			accountTo.deposit(transfer);
			TransactionUtility.receivingFundTransfer(accountTo, accountFrom.getUserId(), transfer);
		}
		
	}
	
	public static void createAccount(String name, String address, String contactNum, String userID, String password, float savings) {
		String convertedNum = "";
		boolean noErrorPhone = false;
		boolean noErrorUser = false;
		boolean noErrorPassword = false;
		boolean noErrorNumber = false;
		
		if(InputCheckUtility.isValidPhoneNum(contactNum)) {
			noErrorPhone = true;
			convertedNum = InputCheckUtility.convertPhoneForm(contactNum);
		} else {
			System.out.println(ErrorUtility.errorAccountCreationPrefix() + ErrorUtility.errorNotPhone());
		}
		
		if(!InputCheckUtility.isExistingUser(userID)) {
			noErrorUser = true;
		} else {
			System.out.println(ErrorUtility.errorAccountCreationPrefix() + ErrorUtility.errorExistingUser());
		}
		
		if(InputCheckUtility.matchesPasswordCriteria(password)) {
			noErrorPassword = true;
		} else {
			System.out.println(ErrorUtility.errorAccountCreationPrefix() + ErrorUtility.errorNotPasswordCriteria());
		}
		
		if(InputCheckUtility.isPositiveNumber(savings)) {
			noErrorNumber = true;
		} else {
			System.out.println(ErrorUtility.errorAccountCreationPrefix() + ErrorUtility.errorNotPositive());
		}
		
		if(noErrorPhone && noErrorUser && noErrorPassword && noErrorNumber) {
			Account createdAccount = new Account(userID, password, savings);
			TransactionUtility.accountCreation(createdAccount);
			MockDatabase.insertCustomer(name, address, convertedNum, createdAccount);
		}
		
	}
	
	public static void displayTransactions(Account account, int x) {
		System.out.println(account.xMostRecentTransaction(x));
	}
}
