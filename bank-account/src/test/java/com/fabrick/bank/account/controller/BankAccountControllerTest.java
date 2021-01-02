package com.fabrick.bank.account.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fabrick.bank.account.BankAccountApplication;
import com.fabrick.bank.account.model.command.CommandWrapper;
import com.fabrick.bank.account.model.command.TypeCommand;
import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.response.Errors;
import com.fabrick.bank.account.model.response.PayloadBalance;
import com.fabrick.bank.account.model.response.PayloadTransaction;
import com.fabrick.bank.account.model.response.TransactionResponse;
import com.fabrick.bank.account.model.response.Operation;
import com.fabrick.bank.account.model.view.BalanceView;
import com.fabrick.bank.account.model.view.OperationView;
import com.fabrick.bank.account.model.view.PayloadTransactionView;
import com.fabrick.bank.account.model.view.TransactionView;
import com.fabrick.bank.account.service.BalanceService;
import com.fabrick.bank.account.service.TransasctionService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankAccountApplication.class)
@ActiveProfiles("fabrick")
public class BankAccountControllerTest {

	@Autowired
	private BankAccountController controller;

	@MockBean
	private BalanceService<BalanceReponse> balanceService;

	@MockBean
	private TransasctionService<TransactionResponse> transactionService;

	private BalanceReponse responseBalance;
	private BalanceView expectedBalance;

	private TransactionResponse transactionResponse;
	private TransactionView expectedTransactions;

	private String ACCOUNT_ID = "14537780";
	private String BALANCE = "10.89";
	private String AMOUNT_1 = "20.0";
	private String CURRENCY = "EUR";
	private String DATE_FROM = "2019-01-01";
	private String DATE_TO = "2019-12-01";
	private String ACCOUTING_DATE = "2020-11-12";
	private String VALUE_DATE = "2020-11-11";
	private String DESC_1 = "Desc1";
	private String STATUS_OK = "OK";

	private String date;

	@Before
	public void setUp() {

		date = DateTimeHandler.getDateFormated(LocalDateTime.now());

		responseBalance = getBalanceResponseMock();
		transactionResponse = getTransactionResponseMock();

		expectedBalance = getBalanceView();
		expectedTransactions = getTransactionView();

	}

	@Test
	public void getBalanceTest() {

		when(balanceService.retrieveBalanceOf(ACCOUNT_ID)).thenReturn(responseBalance);

		CommandWrapper command = CommandWrapper.buildCommand(TypeCommand.READ, ACCOUNT_ID);
		BalanceView balance = controller.readBankAccount(command);

		assertTrue(balance.toString().equals(expectedBalance.toString()));
	}

	@Test
	public void getTransactionTest() {

		when(transactionService.retrieveTransactions(ACCOUNT_ID, DATE_FROM, DATE_TO)).thenReturn(transactionResponse);

		String command = ACCOUNT_ID + " " + DATE_FROM + " " + DATE_TO;
		CommandWrapper commandWrapper = CommandWrapper.buildCommand(TypeCommand.TRANSACTIONS, command);
		TransactionView transactions = controller.readTransactions(commandWrapper);

		assertTrue(transactions.getView().equals(expectedTransactions.getView()));

	}

	private BalanceReponse getBalanceResponseMock() {

		BalanceReponse response = new BalanceReponse();
		response.setStatus(STATUS_OK);

		PayloadBalance payload = new PayloadBalance();
		payload.setBalance(Float.parseFloat(BALANCE));
		payload.setAvailableBalance(Float.parseFloat(BALANCE));
		payload.setCurrency(CURRENCY);
		payload.setDate(date);
		response.setPayload(payload);

		List<Errors> error = new ArrayList<>();
		response.setError(error);

		return response;
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

	private BalanceView getBalanceView() {
		BalanceView balance = new BalanceView();
		balance.setAvailableBalance(BALANCE);
		balance.setBalance(BALANCE);
		balance.setDate(date);
		balance.setCurrency(CURRENCY);

		return balance;
	}

	private TransactionView getTransactionView() {
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
