package com.dollarsbank.controller;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.repository.MockDatabase;
import com.dollarsbank.utility.ErrorUtility;
import com.dollarsbank.utility.InputCheckUtility;
import com.dollarsbank.utility.TransactionUtility;

public class WebAppController {

	public static void depositToAccount(float deposit, Account account) {
		account.deposit(deposit);
		TransactionUtility.depositTransaction(deposit, account);
	}

	public static void withdrawFromAccount(float withdraw, Account account) {
		account.withdraw(withdraw);
		TransactionUtility.withdrawTransaction(withdraw, account);
	}

	public static void transferFunds(Account accountFrom, String userTo, float transfer) {
		Account accountTo = InputCheckUtility.accountLookUp(userTo);
		
		accountFrom.withdraw(transfer);
		TransactionUtility.givingFundTransfer(accountFrom, userTo, transfer);
		accountTo.deposit(transfer);
		TransactionUtility.receivingFundTransfer(accountTo, accountFrom.getUserId(), transfer);
	}
	
	public static void changeCustomerDetails(String userid, String name, String address, String contactNum) {
		Customer customer = InputCheckUtility.customerLookUpFromUser(userid);
		String convertedNum = InputCheckUtility.convertPhoneForm(contactNum);
		System.out.println(convertedNum);
		String changedDetails = "";
		
		if(!(customer.getName().equals(name) && customer.getAddress().equals(address) && customer.getContactNum().equals(contactNum))) {
			if(!customer.getName().equals(name)) {
				System.out.println("Changed name");
				customer.setName(name);
				changedDetails += "<br>" + TransactionUtility.updatedCustomerName(name);
			}

			if(!customer.getAddress().equals(address)) {
				System.out.println("Changed address");
				customer.setAddress(address);
				changedDetails += "<br>" + TransactionUtility.updatedCustomerAddress(address);
			}

			if(!customer.getContactNum().equals(convertedNum)) {
				System.out.println("Changed phone mumber");
				customer.setContactNum(convertedNum);
				changedDetails += "<br>" + TransactionUtility.updatedCustomerPhone(convertedNum);
			}
			TransactionUtility.updatedCustomerDetails(customer.getBankAccount(), changedDetails);
		}
	}
	
	public static void createAccount(String name, String address, String contactNum, String userID, String password, float savings) {
		String convertedNum = InputCheckUtility.convertPhoneForm(contactNum);
		
		Account createdAccount = new Account(userID, password, savings);
		TransactionUtility.accountCreation(createdAccount);
		MockDatabase.insertCustomer(name, address, convertedNum, createdAccount);
	}

	
	public static void displayTransactions(Account account, int x) {
		System.out.println(account.xMostRecentTransaction(x));
	}
	
	public static void displayCustomerInformation(Account account) {
		Customer standIn = InputCheckUtility.customerLookUp(account);
		System.out.println(standIn.getName());
		System.out.println(standIn.getAddress());
		System.out.println(standIn.getContactNum());
		System.out.println(standIn.getBankAccount().getUserId());
		System.out.println(standIn.getBankAccount().getSavings());
	}
}
