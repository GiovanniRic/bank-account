package com.fabrick.bank.account.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabrick.bank.account.client.SandboxClient;
import com.fabrick.bank.account.config.SanboxConfig;
import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.response.Errors;
import com.fabrick.bank.account.model.view.BalanceView;
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

			if (balanceResponse == null) {
				return getErrorGeneric();
			}

			return mapperBalance.map(balanceResponse, BalanceView.class);

		}
		return mapperBalance.map(balanceResponse, BalanceView.class);
	}

	private BalanceView getErrorGeneric() {
		BalanceReponse balanceResponse = new BalanceReponse();
		balanceResponse.setStatus("KO");
		Errors error = new Errors();
		error.setCode("NA");
		error.setDescription("Generic error");
		List<Errors> errors = new ArrayList<>();
		errors.add(error);
		balanceResponse.setErrors(errors);
		return mapperBalance.map(balanceResponse, BalanceView.class);
	}
}