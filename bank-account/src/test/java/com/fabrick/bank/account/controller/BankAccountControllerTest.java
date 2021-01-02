package com.fabrick.bank.account.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fabrick.bank.account.BankAccountApplication;
import com.fabrick.bank.account.handler.DateTimeHandler;
import com.fabrick.bank.account.model.command.CommandWrapper;
import com.fabrick.bank.account.model.command.TypeCommand;
import com.fabrick.bank.account.model.view.BalanceView;
import com.fabrick.bank.account.model.view.OperationView;
import com.fabrick.bank.account.model.view.PayloadTransactionView;
import com.fabrick.bank.account.model.view.TransactionView;
import com.fabrick.bank.account.service.BalanceService;
import com.fabrick.bank.account.service.TransasctionService;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
@SpringBootTest(classes = BankAccountApplication.class)
public class BankAccountControllerTest {

	@InjectMocks
	private BankAccountController controller;

	@Mock
	private BalanceService<BalanceView> balanceService;

	@Mock
	private TransasctionService<TransactionView> transactionService;

	private BalanceView expectedBalance;
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

		expectedBalance = getBalanceViewExpeted();
		expectedTransactions = getTransactionViewExpeted();

	}

	@Test
	public void getBalanceTest() {

		when(balanceService.retrieveBalanceOf(ACCOUNT_ID)).thenReturn(expectedBalance);

		CommandWrapper command = CommandWrapper.buildCommand(TypeCommand.READ, ACCOUNT_ID);
		BalanceView balance = controller.readBankAccount(command);

		assertTrue(balance.toString().equals(expectedBalance.toString()));
	}

	@Test
	public void getTransactionTest() {

		when(transactionService.retrieveTransactions(ACCOUNT_ID, DATE_FROM, DATE_TO)).thenReturn(expectedTransactions);

		String command = ACCOUNT_ID + " " + DATE_FROM + " " + DATE_TO;
		CommandWrapper commandWrapper = CommandWrapper.buildCommand(TypeCommand.TRANSACTIONS, command);
		TransactionView transactions = controller.readTransactions(commandWrapper);

		assertTrue(transactions.getView().equals(expectedTransactions.getView()));

	}

	private BalanceView getBalanceViewExpeted() {
		BalanceView balance = new BalanceView();
		balance.setAvailableBalance(BALANCE);
		balance.setBalance(BALANCE);
		balance.setDate(date);
		balance.setCurrency(CURRENCY);

		return balance;
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
