package com.fabrick.bank.account.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fabrick.bank.account.BankAccountApplication;
import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.response.Errors;
import com.fabrick.bank.account.model.response.PayloadBalance;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankAccountApplication.class)
@ActiveProfiles("fabrick")
public class SandboxBalanceServiceTest {
	
    @Autowired
	private BalanceService<BalanceReponse> service;
    
	private BalanceReponse response;
	
	private String ACCOUNT_ID = "14537780";

	
	@Before
	public void setUp() {	
		response = getBalanceResponseMock();
	}
	
	@Test
	public void retrieveBalanceTest() {
		BalanceReponse balance = service.retrieveBalanceOf(ACCOUNT_ID);
		assertTrue(balance.getStatus().equals(response.getStatus()));
		
	}
	
	private BalanceReponse getBalanceResponseMock() {
		BalanceReponse response = new BalanceReponse();
		
		PayloadBalance payload = new PayloadBalance();
		response.setStatus("OK");
		response.setPayload(payload);
		
		List<Errors> error = new ArrayList<>();
		response.setError(error);
		
		return response;	
	}

}
