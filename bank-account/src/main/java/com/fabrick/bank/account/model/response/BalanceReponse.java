package com.fabrick.bank.account.model.response;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class BalanceReponse {
	private String status;
	private List<Errors> errors;
	private PayloadBalance payload;
	
	public BalanceReponse() {}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Errors> getErrors() {
		return errors;
	}

	public void setErrors(List<Errors> error) {
		this.errors = error;
	}

	public PayloadBalance getPayload() {
		return payload;
	}

	public void setPayload(PayloadBalance payload) {
		this.payload = payload;
	}
}