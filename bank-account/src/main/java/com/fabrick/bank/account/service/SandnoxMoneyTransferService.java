package com.fabrick.bank.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrick.bank.account.client.SandboxClient;
import com.fabrick.bank.account.config.SanboxConfig;
import com.fabrick.bank.account.model.MoneyTransferData;
import com.fabrick.bank.account.model.request.Account;
import com.fabrick.bank.account.model.request.Creditor;
import com.fabrick.bank.account.model.request.MoneyTransferRequest;
import com.fabrick.bank.account.model.response.MoneyTransferResponse;
import com.google.gson.Gson;

import feign.FeignException;

@Service
public class SandnoxMoneyTransferService implements MoneyTransferService<MoneyTransferResponse, MoneyTransferData> {

	@Autowired
	private SanboxConfig sanboxConfig;

	@Autowired
	private SandboxClient client;

	@Override
	public MoneyTransferResponse createMoneyTransferFrom(MoneyTransferData moneyTransferData) {

		MoneyTransferRequest request = new MoneyTransferRequest();
		request = buildMoneyTransferRequestFrom(moneyTransferData);

		MoneyTransferResponse createMoneyTransfer = new MoneyTransferResponse();
		try {
			createMoneyTransfer = client.createMoneyTransfer(sanboxConfig.getApiKey(), sanboxConfig.getAuthSchema(),
					moneyTransferData.getAcccountId(), request);

		} catch (FeignException e) {
			System.out.print(e.getMessage());
			System.out.print(e.contentUTF8());
			
			Gson gson = new Gson();
			createMoneyTransfer = gson.fromJson(e.contentUTF8(), MoneyTransferResponse.class);
			
		}
		return createMoneyTransfer;
	}

	private MoneyTransferRequest buildMoneyTransferRequestFrom(MoneyTransferData moneyTransferData) {

		MoneyTransferRequest request = new MoneyTransferRequest();
		request.setAmount(Integer.valueOf(moneyTransferData.getAcccountId()).intValue());
		request.setDescription(moneyTransferData.getDescription());
		request.setCurrency("EUR");

		Creditor creditor = new Creditor();
		String name = moneyTransferData.getName() + " " + moneyTransferData.getSurname();
		creditor.setName(name);

		Account account = new Account();
		account.setAccountCode(moneyTransferData.getIban());
		creditor.setAccount(account);

		request.setCreditor(creditor);

		return request;

	}

}
