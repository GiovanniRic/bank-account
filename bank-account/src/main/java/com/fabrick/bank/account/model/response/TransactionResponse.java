package com.fabrick.bank.account.model.response;

import java.util.List;


public class TransactionResponse{
	
    private String status;
    private List<Errors> errors;
    private PayloadTransaction payload;
    
    public TransactionResponse() {
    	
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PayloadTransaction getPayload() {
		return payload;
	}

	public void setPayload(PayloadTransaction payload) {
		this.payload = payload;
	}

	public List<Errors> getErrors() {
		return errors;
	}

	public void setErrors(List<Errors> errors) {
		this.errors = errors;
	}
}
