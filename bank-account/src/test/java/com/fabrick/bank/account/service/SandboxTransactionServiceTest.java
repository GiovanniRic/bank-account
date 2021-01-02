package com.fabrick.bank.account.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.fabrick.bank.account.model.response.Errors;
import com.fabrick.bank.account.model.response.Operation;
import com.fabrick.bank.account.model.response.PayloadTransaction;
import com.fabrick.bank.account.model.response.TransactionResponse;
import com.fabrick.bank.account.model.view.OperationView;
import com.fabrick.bank.account.model.view.PayloadTransactionView;
import com.fabrick.bank.account.model.view.TransactionView;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MapperConfig.class, SandboxTransactionService.class})
public class SandboxTransactionServiceTest {
	
    @Autowired
	private TransasctionService<TransactionView> service;
    
	@MockBean
	private SandboxClient client;

	@MockBean
	private SanboxConfig sanboxConfig;
	
	
    
    private TransactionResponse response;
    private TransactionView transactionViewExpeted;
    
	private String ACCOUNT_ID = "14537780";
	private String AMOUNT_1 = "20.0";
	private String CURRENCY = "EUR";
	private String DATE_FROM = "2019-01-01";
	private String DATE_TO = "2019-12-01";
	private String ACCOUTING_DATE = "2020-11-12";
	private String VALUE_DATE = "2020-11-11";
	private String DESC_1 = "Desc1";
	private String STATUS_OK = "OK";
    
 	@Before
 	public void setUp() {	
 		response = getTransactionResponseMock();
 		transactionViewExpeted = getTransactionViewExpeted();
 	}
    
	@Test
	public void retrieveTransactionsTest() {
		
		when(client.getTransactions(null, null, ACCOUNT_ID, DATE_FROM, DATE_TO)).thenReturn(response);
		
		TransactionView transactions = service.retrieveTransactions(ACCOUNT_ID, DATE_FROM, DATE_TO);
		assertTrue(transactionViewExpeted.getView().equals(transactions.getView()));
		
	}
	
	private TransactionResponse getTransactionResponseMock() {
		TransactionResponse response = new TransactionResponse();
		
		Operation operation = new Operation();
		operation.setAmount(Float.parseFloat(AMOUNT_1));
		operation.setCurrency(CURRENCY);
		operation.setDescription(DESC_1);
		operation.setValueDate(VALUE_DATE);
		operation.setAccountingDate(ACCOUTING_DATE);

		List<Operation> operations = new ArrayList<>();
		operations.add(operation);

		PayloadTransaction payload = new PayloadTransaction();
		payload.setOperation(operations);

		response.setStatus(STATUS_OK);
		response.setPayload(payload);

		List<Errors> error = new ArrayList<>();
		response.setErrors(error);
		
		return response;	
	}
	
	private TransactionView getTransactionViewExpeted() {
		TransactionView transaction = new TransactionView();

		transaction.setStatus(STATUS_OK);

		PayloadTransactionView payload = new PayloadTransactionView();
		OperationView operation = new OperationView();

		operation.setAccountingDate(ACCOUTING_DATE);
		operation.setValueDate(VALUE_DATE);
		operation.setDescription(DESC_1);
		operation.setAmount(AMOUNT_1);
		operation.setCurrency(CURRENCY);

		List<OperationView> operations = new ArrayList<>();
		operations.add(operation);

		payload.setOperations(operations);

		transaction.setPayloadTransactionView(payload);

		return transaction;

	}

}
