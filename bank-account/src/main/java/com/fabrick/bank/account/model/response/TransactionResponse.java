package com.fabrick.bank.account.model.response;

import java.util.List;


public class TransactionResponse{
	
    private String status;
    private List<Object> error;
    private PayloadTransaction payload;
    
    public TransactionResponse() {
    	
    }

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

	public PayloadTransaction getPayload() {
		return payload;
	}

	public void setPayload(PayloadTransaction payload) {
		this.payload = payload;
	}
}
