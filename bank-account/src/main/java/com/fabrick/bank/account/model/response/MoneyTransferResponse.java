package com.fabrick.bank.account.model.response;

import java.util.List;

public class MoneyTransferResponse {
	
	private String status;
	private List<Errors> errors;
	
	public MoneyTransferResponse() {
		
	}
	

	public List<Errors> getErrors() {
		return errors;
	}


	public void setErrors(List<Errors> error) {
		this.errors = error;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
