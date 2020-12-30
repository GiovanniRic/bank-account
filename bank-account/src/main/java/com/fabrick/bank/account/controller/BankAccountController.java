package com.fabrick.bank.account.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fabrick.bank.account.model.command.CommandWrapper;
import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.view.BalanceView;
import com.fabrick.bank.account.service.ClientService;

@Component
public class BankAccountController {
	
   @Autowired
   private ClientService<BalanceReponse> sanboxService;
   
   @Autowired
   ModelMapper mapperBalance;
   	
	public BalanceView readBankAccount(CommandWrapper command) {
		
		BalanceReponse balance = sanboxService.getBalance(command.getAccountId());
		return mapperBalance.map(balance, BalanceView.class);
		
	}

}
