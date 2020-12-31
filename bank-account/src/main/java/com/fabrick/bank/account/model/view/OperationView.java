package com.fabrick.bank.account.model.view;

public class OperationView {

	private String amount;
	private String currency;
	private String description;
	private String accountingDate;
	private String valueDate;

	public OperationView() {

	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	
	@Override
	public String toString() {
		return "Amount:" + amount + " " + currency +
				"\n Date: " + valueDate +
				"\n Accouting date: " + accountingDate +
				"\n Description: " + description +
				"\n";
	}
}