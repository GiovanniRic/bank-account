package com.fabrick.bank.account.model.response;

public class PayloadBalance {
	
	 private String date;
	 private float balance;
	 private float availableBalance;
	 private String currency;
	 
	 public PayloadBalance() {
		 
	 }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(float availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
