package com.dollarsbank.repository;

import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.InputCheckUtility;
import com.dollarsbank.utility.TransactionUtility;

public class MockDatabase {
	
	public static List<Customer> mockDB = new ArrayList<Customer>();
	
	public static void initDatabase() {
		
		Account account1 = new Account("X0001", "P@sswork", 1000.0f);
		TransactionUtility.accountCreation(account1);
		Account account2 = new Account("X0002", "Pass", 100.0f);
		TransactionUtility.accountCreation(account2);
		
		Customer customer1 = new Customer("Paladin Danse", "Party Town", "123-456-7890", account1);
		insertCustomer(customer1);
		Customer customer2 = new Customer("Guy Fieri", "Flavor Town", "098-765-4321", account2);
		insertCustomer(customer2);
	}
	
	public static void insertCustomer(Customer customer) {
		mockDB.add(customer);
	}
	
	public static void insertCustomer(String name, String address, String contactNum, Account account) {
		mockDB.add(new Customer(name, address, contactNum, account));
	}
	
	public static boolean checkInit() {
		if(InputCheckUtility.isExistingUser("X0001")) {
			return true;
		} else {
			return false;
		}
	}
}
