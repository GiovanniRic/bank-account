package com.fabrick.bank.account.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fabrick.bank.account.client.SandboxClient;
import com.fabrick.bank.account.config.MapperConfig;
import com.fabrick.bank.account.config.SanboxConfig;
import com.fabrick.bank.account.model.MoneyTransferData;
import com.fabrick.bank.account.model.view.MoneyTransferView;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MapperConfig.class, SandnoxMoneyTransferService.class})
public class SandboxMoneyTransferTest {
	
	@Autowired
	MoneyTransferService<MoneyTransferView, MoneyTransferData> service;
	
	@MockBean
	private SandboxClient client;

	@MockBean
	private SanboxConfig sanboxConfig;
	
	private MoneyTransferView transferViewExpeted;
	
	private final String JSON_ERROR_STRING = "{\r\n" + 
			"    \"status\": \"KO\",\r\n" + 
			"    \"errors\": [\r\n" + 
			"        {\r\n" + 
			"            \"code\": \"REQ010\",\r\n" + 
			"            \"description\": \"Invalid request: wrong parameter(s)\",\r\n" + 
			"            \"params\": \"\"\r\n" + 
			"        }\r\n" + 
			"    ],\r\n" + 
			"    \"payload\": {}\r\n" + 
			"}";
	
	@Before
	public void setUp() {
		
		
		
	}
	
	@Test
	public void createMoneyTransferKOTest() {
		//FeignException f = FeignException.
		
	  //  doThrow(FeignException.errorStatus("", Response.builder().headers(Collections.emptyMap()).status(500).build())).when(client.getBalanceOf(null, null, null));
		
		
		
	}
	

}
