package com.fabrick.bank.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrick.bank.account.client.SandboxClient;
import com.fabrick.bank.account.config.SanboxConfig;
import com.fabrick.bank.account.model.response.BalanceReponse;

import feign.FeignException;

@Service("SandboxBalanceService")
public class SandboxBalanceService implements BalanceService<BalanceReponse> {

	@Autowired
	private SanboxConfig sanboxConfig;

	@Autowired
	private SandboxClient client;
	
	@Autowired
	public SandboxBalanceService(SandboxClient client) {
		this.client = client;


	}

	@Override
	public BalanceReponse retrieveBalanceOf(String accountId) {
		
		BalanceReponse response = null;
		try {
			response = client.getBalanceOf(sanboxConfig.getApiKey(), sanboxConfig.getAuthSchema(), accountId);
		} catch (FeignException e) {
			System.out.print(e.getMessage());
			System.out.print(e.contentUTF8());
		}
		return response;
	}
}
