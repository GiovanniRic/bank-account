package com.fabrick.bank.account.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrick.bank.account.client.SandboxClient;
import com.fabrick.bank.account.config.SanboxConfig;
import com.fabrick.bank.account.handler.DateTimeHandler;
import com.fabrick.bank.account.model.MoneyTransferData;
import com.fabrick.bank.account.model.request.Account;
import com.fabrick.bank.account.model.request.Creditor;
import com.fabrick.bank.account.model.request.MoneyTransferRequest;
import com.fabrick.bank.account.model.response.Errors;
import com.fabrick.bank.account.model.response.MoneyTransferResponse;
import com.fabrick.bank.account.model.view.MoneyTransferView;
import com.google.gson.Gson;

import feign.FeignException;

@Service
public class SandnoxMoneyTransferService implements MoneyTransferService<MoneyTransferView, MoneyTransferData> {

	@Autowired
	private SanboxConfig sanboxConfig;

	@Autowired
	private SandboxClient client;

	@Autowired
	private ModelMapper mapperMoneyTransfer;

	@Override
	public MoneyTransferView createMoneyTransferFrom(MoneyTransferData moneyTransferData) {

		MoneyTransferRequest request = new MoneyTransferRequest();
		request = buildMoneyTransferRequestFrom(moneyTransferData);

		MoneyTransferResponse createMoneyTransferResponse = new MoneyTransferResponse();
		try {
			createMoneyTransferResponse = client.createMoneyTransfer(sanboxConfig.getApiKey(),
					sanboxConfig.getAuthSchema(), moneyTransferData.getAcccountId(), request);

		} catch (FeignException e) {
			Gson gson = new Gson();

			createMoneyTransferResponse = gson.fromJson(e.contentUTF8(), MoneyTransferResponse.class);

			if (createMoneyTransferResponse == null) {
				return getErrorGeneric();
			}

			return mapperMoneyTransfer.map(createMoneyTransferResponse, MoneyTransferView.class);

		}
		return mapperMoneyTransfer.map(createMoneyTransferResponse, MoneyTransferView.class);

	}

	private MoneyTransferRequest buildMoneyTransferRequestFrom(MoneyTransferData moneyTransferData) {

		MoneyTransferRequest request = new MoneyTransferRequest();
		request.setAmount(Integer.valueOf(moneyTransferData.getAcccountId()).intValue());
		request.setDescription(moneyTransferData.getDescription());
		request.setCurrency("EUR");
		request.setExecutionDate(DateTimeHandler.getDateFormated(LocalDateTime.now()));
		Creditor creditor = new Creditor();
		String name = moneyTransferData.getName() + " " + moneyTransferData.getSurname();
		creditor.setName(name);

		Account account = new Account();
		account.setAccountCode(moneyTransferData.getIban());
		creditor.setAccount(account);

		request.setCreditor(creditor);

		return request;

	}

	private MoneyTransferView getErrorGeneric() {
		MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse();
		moneyTransferResponse.setStatus("KO");
		Errors error = new Errors();
		error.setCode("NA");
		error.setDescription("Generic error");
		List<Errors> errors = new ArrayList<>();
		errors.add(error);
		moneyTransferResponse.setErrors(errors);
		return mapperMoneyTransfer.map(moneyTransferResponse, MoneyTransferView.class);
	}

}