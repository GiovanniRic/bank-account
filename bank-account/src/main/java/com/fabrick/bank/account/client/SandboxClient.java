package com.fabrick.bank.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.fabrick.bank.account.model.request.MoneyTransferRequest;
import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.response.MoneyTransferResponse;
import com.fabrick.bank.account.model.response.TransactionResponse;


@FeignClient(name = "sandbox", url = "${sandbox.baseUri}")
public interface SandboxClient {

	@GetMapping(value = "/${sandbox.balance}")
	public BalanceReponse getBalanceOf(@RequestHeader("Api-Key") String apiKey,
			@RequestHeader("Auth-Schema") String autSchema, @PathVariable("accountId") String accountId);

	@GetMapping(value = "${sandbox.transaction}")
	public TransactionResponse getTransactions(@RequestHeader("Api-Key") String apiKey,
			@RequestHeader("Auth-Schema") String autSchema, @PathVariable("accountId") String accountId,
			@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo);

	@PostMapping(value = "${sandbox.moneyTransfer}")
	public MoneyTransferResponse createMoneyTransfer(@RequestHeader("Api-Key") String apiKey,
			@RequestHeader("Auth-Schema") String autSchema, @PathVariable("accountId") String accountId,
			@RequestBody MoneyTransferRequest request);

}