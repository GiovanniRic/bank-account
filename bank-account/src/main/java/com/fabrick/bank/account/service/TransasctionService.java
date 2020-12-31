package com.fabrick.bank.account.service;

public interface TransasctionService<T> {
	
	public T retrieveTransactions(String accountId, String dateFrom, String dateTo);

}
