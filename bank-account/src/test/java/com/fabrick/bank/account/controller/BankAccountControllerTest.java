package com.fabrick.bank.account.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fabrick.bank.account.BankAccountApplication;
import com.fabrick.bank.account.model.command.CommandWrapper;
import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.response.Payload;
import com.fabrick.bank.account.model.view.BalanceView;
import com.fabrick.bank.account.service.ClientService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankAccountApplication.class)
@ActiveProfiles("fabrick")
public class BankAccountControllerTest {

	@Autowired
	private BankAccountController controller;

	@MockBean
	private ClientService<BalanceReponse> service;

	BalanceReponse response;
	BalanceView expected;

	private String ACCOUNT_ID = "14537780";
	private String BALANCE = "10.89";
	private String CURRENCY = "EUR";
	private String date;

	@Before
	public void setUp() {

		date = DateTimeHandler.getDateFormated(LocalDateTime.now());
		response = getBalanceResponseMock();
		expected = getBalanceView();

	}

	@Test
	public void getBalanceTest() {

		when(service.getBalance(ACCOUNT_ID)).thenReturn(response);

		CommandWrapper command = CommandWrapper.buildCommand(ACCOUNT_ID);
		BalanceView readBankAccount = controller.readBankAccount(command);

		assertTrue(readBankAccount.toString().equals(expected.toString()));
	}

	private BalanceReponse getBalanceResponseMock() {

		BalanceReponse response = new BalanceReponse();
		response.setStatus("OK");

		Payload payload = new Payload();
		payload.setBalance(Float.parseFloat(BALANCE));
		payload.setAvailableBalance(Float.parseFloat(BALANCE));
		payload.setCurrency(CURRENCY);
		payload.setDate(date);
		response.setPayload(payload);

		List<Object> error = new ArrayList<>();
		response.setError(error);

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

}
