package com.fabrick.bank.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrick.bank.account.client.SandboxClient;
import com.fabrick.bank.account.config.SanboxConfig;
import com.fabrick.bank.account.model.response.TransactionResponse;

import feign.FeignException;

@Service("SandboxTransactionService")
public class SandboxTransactionService implements TransasctionService<TransactionResponse> {

	@Autowired
	private SanboxConfig sanboxConfig;

	@Autowired
	private SandboxClient client;

	@Override
	public TransactionResponse retrieveTransactions(String accountId, String dateFrom, String dateTo) {

		TransactionResponse response = null;
		try {
			response = client.getTransactions(sanboxConfig.getApiKey(), sanboxConfig.getAuthSchema(), 
					accountId, dateFrom, dateTo);
		} catch (FeignException e) {
			System.out.print(e.getMessage());
			System.out.print(e.contentUTF8());
		}
		return response;
	}
}
