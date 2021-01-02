package com.fabrick.bank.account.controller;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fabrick.bank.account.model.MoneyTransferData;
import com.fabrick.bank.account.model.command.CommandWrapper;
import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.response.MoneyTransferResponse;
import com.fabrick.bank.account.model.response.TransactionResponse;
import com.fabrick.bank.account.model.view.BalanceView;
import com.fabrick.bank.account.model.view.MoneyTransferView;
import com.fabrick.bank.account.model.view.TransactionView;
import com.fabrick.bank.account.service.BalanceService;
import com.fabrick.bank.account.service.MoneyTransferService;
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
	private MoneyTransferService<MoneyTransferResponse, MoneyTransferData> moneyTransferService;

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
	
	public MoneyTransferView createTransfer(CommandWrapper command) {
		
		MoneyTransferData transferData = buildMoneyTransferDataFrom(command);
		
		MoneyTransferResponse transfer = moneyTransferService.createMoneyTransferFrom(transferData);
		
	    MoneyTransferView views = mapperTransaction.map(transfer, MoneyTransferView.class);
	        
		return views;

	}
	
	private MoneyTransferData buildMoneyTransferDataFrom(CommandWrapper command) {
		
		MoneyTransferData transferData = new MoneyTransferData();
		transferData.setAcccountId(command.getAccountId());
		transferData.setIban(command.getInputMoneyTransfer().getIban());
		transferData.setName(command.getInputMoneyTransfer().getName());
		transferData.setSurname(command.getInputMoneyTransfer().getSurname());
		transferData.setDescription(command.getInputMoneyTransfer().getDescription());
		return transferData;
		
	}

}
