package com.fabrick.bank.account.model.view;

public class BalanceView {
	
	private String balance;
	private String availableBalance;
	private String currency;
	private String date;
	
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
	
	@Override
	public String toString() {
		return "Bank account in date "+ date + ":\n"
				+ "Balance: " + balance + " " + currency  + "\n"
				+ "Available balance: " + availableBalance + " " + currency  + "\n";
	}
	
	

}
