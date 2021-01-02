package com.fabrick.bank.account.service;

public interface MoneyTransferService<T, R> {
	
	public T createMoneyTransferFrom(R moneyTransferData);

}
