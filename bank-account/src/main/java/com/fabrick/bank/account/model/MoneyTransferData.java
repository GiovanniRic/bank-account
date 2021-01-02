package com.fabrick.bank.account.model;

public class MoneyTransferData {

	private String acccountId;
	private String name;
	private String surname;
	private String iban;
	private String amount;
	private String description;

	public MoneyTransferData() {

	}

	public String getAcccountId() {
		return acccountId;
	}

	public void setAcccountId(String acccountId) {
		this.acccountId = acccountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
