package com.fabrick.bank.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fabrick.bank.account.model.MoneyTransferData;
import com.fabrick.bank.account.model.command.CommandWrapper;
import com.fabrick.bank.account.model.view.BalanceView;
import com.fabrick.bank.account.model.view.MoneyTransferView;
import com.fabrick.bank.account.model.view.TransactionView;
import com.fabrick.bank.account.service.BalanceService;
import com.fabrick.bank.account.service.MoneyTransferService;
import com.fabrick.bank.account.service.TransasctionService;

@Component
public class BankAccountController {

	@Autowired
	private BalanceService<BalanceView> sanboxBalanceService;

	@Autowired
	private TransasctionService<TransactionView> sandboxTransactionsService;

	@Autowired
	private MoneyTransferService<MoneyTransferView, MoneyTransferData> moneyTransferService;

	public BalanceView readBankAccount(CommandWrapper command) {

		return sanboxBalanceService.retrieveBalanceOf(command.getAccountId());

	}

	public TransactionView readTransactions(CommandWrapper command) {

		return sandboxTransactionsService.retrieveTransactions(command.getAccountId(), command.getDateFrom(),
				command.getDateTo());

	}

	public MoneyTransferView createTransfer(CommandWrapper command) {

		MoneyTransferData transferData = buildMoneyTransferDataFrom(command);

		return moneyTransferService.createMoneyTransferFrom(transferData);
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
