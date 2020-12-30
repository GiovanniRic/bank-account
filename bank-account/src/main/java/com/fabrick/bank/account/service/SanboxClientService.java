package com.fabrick.bank.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrick.bank.account.client.SandboxClient;
import com.fabrick.bank.account.config.SanboxConfig;
import com.fabrick.bank.account.model.response.BalanceReponse;

import feign.FeignException;

@Service
public class SanboxClientService implements ClientService<BalanceReponse> {

	@Autowired
	private SanboxConfig sanboxConfig;

	@Autowired
	private SandboxClient client;
	
	@Autowired
	public SanboxClientService(SandboxClient client) {
		this.client = client;

		
	}

	@Override
	public BalanceReponse getBalance(String accountId) {
		BalanceReponse response = null;
		try {
			response = client.getBalance(sanboxConfig.getApiKey(), sanboxConfig.getAuthSchema(), accountId);
		} catch (FeignException e) {
			System.out.print(e.getMessage());
			System.out.print(e.contentUTF8());
		}
		return response;
	}
}
