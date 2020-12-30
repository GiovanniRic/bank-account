package com.fabrick.bank.account.model.response;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class BalanceReponse {
	private String status;
	private List<Object> error;
	private Payload payload;
	
	public BalanceReponse() {}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Object> getError() {
		return error;
	}

	public void setError(List<Object> error) {
		this.error = error;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}
}