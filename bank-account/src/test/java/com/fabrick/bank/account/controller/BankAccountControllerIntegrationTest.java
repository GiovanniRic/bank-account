package com.fabrick.bank.account.controller;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fabrick.bank.account.BankAccountApplication;
import com.fabrick.bank.account.handler.DateTimeHandler;
import com.fabrick.bank.account.model.command.CommandWrapper;
import com.fabrick.bank.account.model.command.TypeCommand;
import com.fabrick.bank.account.model.view.BalanceView;
import com.fabrick.bank.account.model.view.TransactionView;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BankAccountApplication.class })
@ActiveProfiles("application-test")
public class BankAccountControllerIntegrationTest {

	@Autowired
	BankAccountController controller;

	private String STATUS_OK = "OK";
	private String STATUS_KO = "KO";
	private String date;
	private String ACCOUNT_ID = "14537780";
	private String ACCOUNT_ID_BAD = "00000000";
	private String DATE_FROM = "2019-01-01";
	private String DATE_TO = "2019-12-01";

	BalanceView balanceViewExpetedOK;
	TransactionView transactionViewExpetedOK;

	BalanceView balanceViewExpetedKO;
	TransactionView transactionViewExpetedKO;

	@Before
	public void setUp() {
		balanceViewExpetedOK = getBalanceViewExpeted(STATUS_OK);
		transactionViewExpetedOK = getTransactionViewExpeted(STATUS_OK);

		balanceViewExpetedOK = getBalanceViewExpeted(STATUS_KO);
		transactionViewExpetedOK = getTransactionViewExpeted(STATUS_KO);

		date = DateTimeHandler.getDateFormated(LocalDateTime.now());

	}

	@Test
	public void testBalanceOK() {

		CommandWrapper command = CommandWrapper.buildCommand(TypeCommand.READ, ACCOUNT_ID);
		BalanceView view = controller.readBankAccount(command);

		assertTrue(view.getStatus().equals(balanceViewExpetedOK.getStatus()));
		assertTrue(view.getDate().equals(balanceViewExpetedOK.getDate()));
	}

	@Test
	public void testTransactionOK() {

		String commandString = ACCOUNT_ID + " " + DATE_FROM + " " + DATE_TO;
		CommandWrapper command = CommandWrapper.buildCommand(TypeCommand.TRANSACTIONS, commandString);
		TransactionView view = controller.readTransactions(command);

		assertTrue(view.getStatus().equals(balanceViewExpetedOK.getStatus()));

	}

	@Test
	public void testBalanceKO() {

		CommandWrapper command = CommandWrapper.buildCommand(TypeCommand.READ, ACCOUNT_ID_BAD);
		BalanceView view = controller.readBankAccount(command);

		assertTrue(view.getStatus().equals(balanceViewExpetedKO.getStatus()));

	}

	@Test
	public void testTransactionKO() {

		String commandString = ACCOUNT_ID_BAD + " " + DATE_FROM + " " + DATE_TO;
		CommandWrapper command = CommandWrapper.buildCommand(TypeCommand.TRANSACTIONS, commandString);
		TransactionView view = controller.readTransactions(command);

		assertTrue(view.getStatus().equals(balanceViewExpetedKO.getStatus()));

	}

	private TransactionView getTransactionViewExpeted(String status) {
		TransactionView transaction = new TransactionView();

		transaction.setStatus(status);

		return transaction;
	}

	private BalanceView getBalanceViewExpeted(String status) {
		BalanceView view = new BalanceView();
		view.setDate(date);
		view.setStatus(status);

		return view;
	}

}