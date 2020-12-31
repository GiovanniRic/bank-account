package com.fabrick.bank.account.service;

public interface BalanceService<T> {
	
	public T retrieveBalanceOf(String accountId);

}
