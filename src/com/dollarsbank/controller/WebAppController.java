package com.dollarsbank.controller;

import com.dollarsbank.model.Account;
import com.dollarsbank.utility.ErrorUtility;
import com.dollarsbank.utility.InputCheckUtility;
import com.dollarsbank.utility.TransactionUtility;

public class WebAppController {

	public static void depositToAccount(float deposit, Account account) {
		if(InputCheckUtility.isPositiveNumber(deposit)) {
			account.deposit(deposit);
			TransactionUtility.depositTransaction(deposit, account);
		} else {
			System.out.println(ErrorUtility.errorDepositPrefix() + ErrorUtility.errorNotPositive());
		}
	}

	public static void withdrawFromAccount(float withdraw, Account account) {
		if(InputCheckUtility.isPositiveNumber(withdraw)) {
			if(InputCheckUtility.isValidWithdraw(withdraw, account)) {
				account.withdraw(withdraw);
				TransactionUtility.withdrawTransaction(withdraw, account);
			} else {
				System.out.println(ErrorUtility.errorWithdrawPrefix() + ErrorUtility.errorNotEnough());
			}
		} else {
			System.out.println(ErrorUtility.errorWithdrawPrefix() + ErrorUtility.errorNotPositive());
		}
	}

	public static void transferFunds(Account accountFrom, String userTo, float transfer) {
		if(InputCheckUtility.isExistingUser(userTo)) {
			Account accountTo = InputCheckUtility.accountLookUp(userTo);
			if(InputCheckUtility.isPositiveNumber(transfer)) {
				if(InputCheckUtility.isValidWithdraw(transfer, accountFrom)) {
					accountFrom.withdraw(transfer);
					TransactionUtility.givingFundTransfer(accountFrom, userTo, transfer);
					accountTo.deposit(transfer);
					TransactionUtility.receivingFundTransfer(accountTo, accountFrom.getUserId(), transfer);
				} else {
					System.out.println(ErrorUtility.errorTransferPrefix() + ErrorUtility.errorNotEnough());
				}
			} else {
				System.out.println(ErrorUtility.errorTransferPrefix() + ErrorUtility.errorNotPositive());
			}
		} else {
			System.out.println(ErrorUtility.errorTransferPrefix() + ErrorUtility.errorUserNotFound());
		}
	}
	
	public static void displayTransactions(Account account, int x) {
		System.out.println(account.xMostRecentTransaction(x));
	}
}
