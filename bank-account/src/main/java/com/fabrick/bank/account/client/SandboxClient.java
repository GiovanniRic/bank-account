package com.fabrick.bank.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fabrick.bank.account.model.response.BalanceReponse;



@FeignClient( name="sandbox",url="https://sandbox.platfr.io")
public interface SandboxClient {
	
	@GetMapping(value = "/api/gbs/banking/v4.0/accounts/{accountId}/balance")
    public  BalanceReponse getBalance(@RequestHeader("Api-Key") String apiKey, @RequestHeader("Auth-Schema") String autSchema, @PathVariable("accountId") String accountId);

}
