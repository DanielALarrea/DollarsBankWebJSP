package com.dollarsbank.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

	private String userId;
	private String password;
	
	private float savings;
	private List<String> recentTransactions;
	
	public Account() {
		
	}

	public Account(String userId, String password, float savings) {
		super();
		this.userId = userId;
		this.password = password;

		this.savings = savings;
		this.recentTransactions = new ArrayList<String>();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public float getSavings() {
		return savings;
	}

	public void setSavings(float savings) {
		this.savings = savings;
	}
	
	public List<String> getRecentTransactions() {
		return recentTransactions;
	}
	
	public void setRecentTransactions(List<String> recentTransactions) {
		this.recentTransactions = recentTransactions;
	}
	
	public void deposit(float deposit) {
		this.savings += deposit;
	}
	
	public void withdraw(float withdraw) {
		this.savings -= withdraw;
	}

	public void popLeastRecentTransaction() {
		if(this.recentTransactions.size() > 0) {
			this.recentTransactions.remove(0);
		}
	}

	public void pushRecentTransaction(String transaction) {
		this.recentTransactions.add(transaction);
	}
	
	public List<String> xMostRecentTransaction(int x) {
		List<String> mostRecentTransactions = new ArrayList<String>();
		int listLength = this.recentTransactions.size();
		int startIndex = listLength - x;
		if (listLength < x) {
			startIndex = 0;
		}
		for(int i = startIndex; i < listLength; i++) {
			mostRecentTransactions.add(this.recentTransactions.get(i));
		}
		
		return mostRecentTransactions;
	}

	@Override
	public String toString() {
		return "Account [userId=" + userId + ", password=" + password + ", savings" + savings + "]";
	}
	
}
