package com.fabrick.bank.account.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrick.bank.account.client.SandboxClient;
import com.fabrick.bank.account.config.SanboxConfig;
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

		TransactionResponse TransactionResponse = new TransactionResponse();
		try {
			TransactionResponse = client.getTransactions(sanboxConfig.getApiKey(), sanboxConfig.getAuthSchema(),
					accountId, dateFrom, dateTo);

		} catch (FeignException e) {

			Gson gson = new Gson();
			TransactionResponse = gson.fromJson(e.contentUTF8(), TransactionResponse.class);
			return mapperTransaction.map(TransactionResponse, TransactionView.class);
		}

		return mapperTransaction.map(TransactionResponse, TransactionView.class);

	}
}
