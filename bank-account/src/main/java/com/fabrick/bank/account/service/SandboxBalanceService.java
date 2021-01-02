package com.fabrick.bank.account.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrick.bank.account.client.SandboxClient;
import com.fabrick.bank.account.config.SanboxConfig;
import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.response.TransactionResponse;
import com.fabrick.bank.account.model.view.BalanceView;
import com.fabrick.bank.account.model.view.TransactionView;
import com.google.gson.Gson;

import feign.FeignException;

@Service("SandboxBalanceService")
public class SandboxBalanceService implements BalanceService<BalanceView> {

	@Autowired
	private SanboxConfig sanboxConfig;

	@Autowired
	private SandboxClient client;
	
	@Autowired
	ModelMapper mapperBalance;
	
	@Autowired
	public SandboxBalanceService(SandboxClient client) {
		this.client = client;


	}

	@Override
	public BalanceView retrieveBalanceOf(String accountId) {
		
		BalanceReponse balanceResponse = new BalanceReponse();
		try {
			balanceResponse = client.getBalanceOf(sanboxConfig.getApiKey(), sanboxConfig.getAuthSchema(), accountId);
		} catch (FeignException e) {
			
			Gson gson = new Gson();
			balanceResponse = gson.fromJson(e.contentUTF8(), BalanceReponse.class);
			return  mapperBalance.map(balanceResponse, BalanceView.class);
			
		}
		return  mapperBalance.map(balanceResponse, BalanceView.class);
	}
}
