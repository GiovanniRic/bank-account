package com.fabrick.bank.account.service;

public interface ClientService<T> {
	
	public T getBalance(String accountId);

}
