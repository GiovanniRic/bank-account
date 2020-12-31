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
import com.fabrick.bank.account.model.response.PayloadTransaction;
import com.fabrick.bank.account.model.response.TransactionResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankAccountApplication.class)
@ActiveProfiles("fabrick")
public class SandboxTransactionServiceTest {
	
    @Autowired
	private TransasctionService<TransactionResponse> service;
    
    private TransactionResponse response;
    
     private String ACCOUNT_ID = "14537780";
     private String DATE_FROM = "2019-01-01";
     private String DATE_TO = "2019-12-01";
    
 	@Before
 	public void setUp() {	
 		response = getTransactionResponseMock();
 	}
    
	@Test
	public void retrieveTransactionsTest() {
		
		TransactionResponse transactions = service.retrieveTransactions(ACCOUNT_ID, DATE_FROM, DATE_TO);
		assertTrue(transactions.getStatus().equals(response.getStatus()));
		
	}
	
	private TransactionResponse getTransactionResponseMock() {
		TransactionResponse response = new TransactionResponse();
		
		PayloadTransaction payload = new PayloadTransaction();
		response.setStatus("OK");
		response.setPayload(payload);
		
		List<Object> error = new ArrayList<>();
		response.setError(error);
		
		return response;	
	}

}
