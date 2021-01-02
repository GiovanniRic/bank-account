package com.fabrick.bank.account.model.view;

import java.util.List;

public class BalanceView {
	
	private String balance;
	private String availableBalance;
	private String currency;
	private String date;
	private String Status;
	private List<ErrorView> errors;
	
	public BalanceView() {
		
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public List<ErrorView> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorView> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "Bank account in date "+ date + ":\n"
				+ "Balance: " + balance + " " + currency  + "\n"
				+ "Available balance: " + availableBalance + " " + currency  + "\n";
	}
	
	

}
