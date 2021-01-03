package com.fabrick.bank.account.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrick.bank.account.client.SandboxClient;
import com.fabrick.bank.account.config.SanboxConfig;
import com.fabrick.bank.account.model.response.Errors;
import com.fabrick.bank.account.model.response.MoneyTransferResponse;
import com.fabrick.bank.account.model.response.TransactionResponse;
import com.fabrick.bank.account.model.view.MoneyTransferView;
import com.fabrick.bank.account.model.view.TransactionView;
import com.google.gson.Gson;

import feign.FeignException;

@Service("SandboxTransactionService")
public class SandboxTransactionService implements TransasctionService<TransactionView> {

	@Autowired
	private SanboxConfig sanboxConfig;

	@Autowired
	private SandboxClient client;

	@Autowired
	ModelMapper mapperTransaction;

	@Override
	public TransactionView retrieveTransactions(String accountId, String dateFrom, String dateTo) {

		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			transactionResponse = client.getTransactions(sanboxConfig.getApiKey(), sanboxConfig.getAuthSchema(),
					accountId, dateFrom, dateTo);

		} catch (FeignException e) {
			Gson gson = new Gson();

			transactionResponse = gson.fromJson(e.contentUTF8(), TransactionResponse.class);

			if (transactionResponse == null) {
				return getErrorGeneric();
			}

			return mapperTransaction.map(transactionResponse, TransactionView.class);

		}

		return mapperTransaction.map(transactionResponse, TransactionView.class);

	}

	private TransactionView getErrorGeneric() {
		TransactionResponse moneyTransferResponse = new TransactionResponse();
		moneyTransferResponse.setStatus("KO");
		Errors error = new Errors();
		error.setCode("NA");
		error.setDescription("Generic error");
		List<Errors> errors = new ArrayList<>();
		errors.add(error);
		moneyTransferResponse.setErrors(errors);
		return mapperTransaction.map(moneyTransferResponse, TransactionView.class);
	}
}