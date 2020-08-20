package com.dollarsbank.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.dollarsbank.controller.WebAppController;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.repository.MockDatabase;
import com.dollarsbank.utility.ErrorUtility;
import com.dollarsbank.utility.InputCheckUtility;

class WebAppTester {
	
	Account testAccount = new Account("TestID", "TestPass", 100.0f);
	
	Customer testCustomer = new Customer("TestName", "TestAddress", "TestNum", testAccount);
	
	@Test
	void depositTestSuccess() {
		WebAppController.depositToAccount(10.0f, testAccount);
		assertEquals(110.0f, testAccount.getSavings());
	}
	
	@Test
	void depositTestFailNotPositive() {
		WebAppController.depositToAccount(-10.0f, testAccount);
		fail(ErrorUtility.errorDepositPrefix() + ErrorUtility.errorNotPositive());
	}
	
	@Test
	void withdrawTestSuccess() {
		WebAppController.withdrawFromAccount(10.0f, testAccount);
		assertEquals(90.0f, testAccount.getSavings());
	}
	
	@Test
	void withdrawTestFailNotEnough() {
		WebAppController.withdrawFromAccount(110.0f, testAccount);
		fail(ErrorUtility.errorWithdrawPrefix() + ErrorUtility.errorNotEnough());
	}
	
	@Test
	void withdrawTestFailNotPostive() {
		WebAppController.withdrawFromAccount(-10.0f, testAccount);
		fail(ErrorUtility.errorWithdrawPrefix() + ErrorUtility.errorNotPositive());
	}
	
	@Test 
	void accountLookupSuccess() {
		MockDatabase.initDatabase();
		Account lookUp = InputCheckUtility.accountLookUp("TestID");
		assertEquals("TestPass", lookUp.getPassword());
		assertEquals(100.0f, lookUp.getSavings());
	}
	
	@Test 
	void accountLookupFail() {
		MockDatabase.initDatabase();
		MockDatabase.insertCustomer(testCustomer);
		Account lookUp = InputCheckUtility.accountLookUp("TestIDFail");
		assertNotNull(lookUp.getPassword());
	}
	
	@Test
	void transferTestSuccess() {
		MockDatabase.initDatabase();
		WebAppController.transferFunds(testAccount, "X0001", 10.0f);
		assertEquals(90.0f, testAccount.getSavings());
		assertEquals(1010.0f, InputCheckUtility.accountLookUp("X0001").getSavings());
	}

	@Test
	void transferTestFailNoUser() {
		MockDatabase.initDatabase();
		WebAppController.transferFunds(testAccount, "Fail", 10.0f);
		fail(ErrorUtility.errorTransferPrefix() + ErrorUtility.errorUserNotFound());
	}
	
	@Test
	void transferTestFailNotPositive() {
		MockDatabase.initDatabase();
		WebAppController.transferFunds(testAccount, "X0001", -10.0f);
		fail(ErrorUtility.errorTransferPrefix() + ErrorUtility.errorNotPositive());
	}
	
	@Test
	void transferTestFailNotEnough() {
		MockDatabase.initDatabase();
		WebAppController.transferFunds(testAccount, "X0001", 110.0f);
		fail(ErrorUtility.errorTransferPrefix() + ErrorUtility.errorNotEnough());
	}
	
	@Test
	void displayTransactionTest() {
		MockDatabase.initDatabase();
		Account testAccount = InputCheckUtility.accountLookUp("X0001");
		Account testAccount2 = InputCheckUtility.accountLookUp("X0002");
		
		WebAppController.displayTransactions(testAccount, 5);
		
		WebAppController.depositToAccount(10, testAccount);
		WebAppController.withdrawFromAccount(15, testAccount);
		WebAppController.transferFunds(testAccount, testAccount2.getUserId(), 20);
		WebAppController.transferFunds(testAccount2, testAccount.getUserId(), 5);
		
		WebAppController.displayTransactions(testAccount, 6);
	}
	
	@Test
	void phoneConvertionTestSuccess() {
		String phone1 = "1234567890";
		String phone2 = "123-456-7890";
		String phone3 = "(123) 456-7890";
		
		assertEquals("123-456-7890", InputCheckUtility.convertPhoneForm(phone1));
		assertEquals("123-456-7890", InputCheckUtility.convertPhoneForm(phone2));
		assertEquals("123-456-7890", InputCheckUtility.convertPhoneForm(phone3));
	}
	
	@Test
	void phoneConvertionTestFail() {
		String phone1 = "123456789";
		String phone2 = "123-456-780";
		String phone3 = "(123) 456-790";
		
		assertEquals("123-456-7890", InputCheckUtility.convertPhoneForm(phone1), "Phone 1 failed");
		assertEquals("123-456-7890", InputCheckUtility.convertPhoneForm(phone2), "Phone 1 failed");
		assertEquals("123-456-7890", InputCheckUtility.convertPhoneForm(phone3), "Phone 1 failed");
	}
	
	@Test
	void customerFromAccountTest() {
		MockDatabase.initDatabase();
		Account testAccount = InputCheckUtility.accountLookUp("X0001");
		Customer testCustomer = InputCheckUtility.customerLookUp(testAccount);
		System.out.println(testCustomer);
	}
	
	@Test
	void accountCreateTestSuccess() {
		MockDatabase.initDatabase();
		String phoneNum = "1234567890";
		WebAppController.createAccount("Name", "Address", phoneNum, "X0003", "P@ssword", 100.0f);
		assertEquals("123-456-7890", InputCheckUtility.convertPhoneForm(phoneNum));
	}
	
	@Test
	void accountCreateTestFailBadPhoneNum() {
		MockDatabase.initDatabase();
		String phoneNum = "123456789";
		WebAppController.createAccount("Name", "Address", phoneNum, "X0004", "P@ssword", 100.0f);
		fail("Bad phone number");
	}
	
	@Test
	void accountCreateTestFailExistingUser() {
		MockDatabase.initDatabase();
		String phoneNum = "1234567890";
		WebAppController.createAccount("Name", "Address", phoneNum, "X0001", "P@ssword", 100.0f);
		fail("User Exists");
	}
	
	@Test
	void accountCreateTestFailBadPassword() {
		MockDatabase.initDatabase();
		String phoneNum = "1234567890";
		WebAppController.createAccount("Name", "Address", phoneNum, "X0005", "Password", 100.0f);
		fail("Bad password");
	}
	
	@Test
	void accountCreateTestFailNegativeNumber() {
		MockDatabase.initDatabase();
		String phoneNum = "1234567890";
		WebAppController.createAccount("Name", "Address", phoneNum, "X0006", "P@ssword", -100.0f);
		fail("Negative Number");
	}

}
