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
import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.response.Errors;
import com.fabrick.bank.account.model.response.PayloadBalance;
import com.fabrick.bank.account.model.view.BalanceView;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MapperConfig.class, SandboxBalanceService.class})
public class SandboxBalanceServiceTest {

	@Autowired
	private BalanceService<BalanceView> service;

	@MockBean
	private SandboxClient client;

	@MockBean
	SanboxConfig sanboxConfig;

	private BalanceView balanceViewExpeted;

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
	private String DATE_BALANCE = "2021-01-01";

	BalanceReponse response;

	@Before
	public void setUp() {
		balanceViewExpeted = getBalanceViewExpeted();
		response = getBalanceResponseMock();
	}

	@Test
	public void retrieveBalanceTest() {
		when(client.getBalanceOf(null, null, ACCOUNT_ID)).thenReturn(response);
		BalanceView balance = service.retrieveBalanceOf(ACCOUNT_ID);
		assertTrue(balance.toString().equals(balanceViewExpeted.toString()));
	}

	private BalanceView getBalanceViewExpeted() {
		BalanceView view = new BalanceView();
		view.setBalance(BALANCE);
		view.setAvailableBalance(BALANCE);
		view.setCurrency(CURRENCY);
		view.setDate(DATE_BALANCE);
		view.setStatus(STATUS_OK);

		return view;
	}

	private BalanceReponse getBalanceResponseMock() {

		BalanceReponse response = new BalanceReponse();
		response.setStatus(STATUS_OK);

		PayloadBalance payload = new PayloadBalance();
		payload.setBalance(Float.parseFloat(BALANCE));
		payload.setAvailableBalance(Float.parseFloat(BALANCE));
		payload.setCurrency(CURRENCY);
		payload.setDate(DATE_BALANCE);
		response.setPayload(payload);

		List<Errors> error = new ArrayList<>();
		response.setErrors(error);

		return response;
	}

}
