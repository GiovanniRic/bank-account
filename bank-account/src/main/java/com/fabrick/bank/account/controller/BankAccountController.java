package com.fabrick.bank.account.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fabrick.bank.account.model.command.CommandWrapper;
import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.response.TransactionResponse;
import com.fabrick.bank.account.model.view.BalanceView;
import com.fabrick.bank.account.model.view.TransactionView;
import com.fabrick.bank.account.service.BalanceService;
import com.fabrick.bank.account.service.TransasctionService;

@Component
public class BankAccountController {

	@Autowired
	@Qualifier("SandboxBalanceService")
	private BalanceService<BalanceReponse> sanboxBalanceService;

	@Autowired
	@Qualifier("SandboxTransactionService")
	private TransasctionService<TransactionResponse> sandboxTransactionsService;

	@Autowired
	ModelMapper mapperBalance;
	
	@Autowired
	ModelMapper mapperTransaction;

	public BalanceView readBankAccount(CommandWrapper command) {

		BalanceReponse balance = sanboxBalanceService.retrieveBalanceOf(command.getAccountId());
		return mapperBalance.map(balance, BalanceView.class);

	}

	public TransactionView readTransactions(CommandWrapper command) {

		TransactionResponse transactions = sandboxTransactionsService.retrieveTransactions(command.getAccountId(),
				command.getDateFrom(), command.getDateTo());
		
	    TransactionView views = mapperTransaction.map(transactions, TransactionView.class);
	        
		return views;

	}

}
