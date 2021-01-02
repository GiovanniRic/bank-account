package com.fabrick.bank.account.model.view;

import java.util.List;

public abstract class BaseView {

	private String status;
	private List<ErrorView> errors;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ErrorView> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorView> errors) {
		this.errors = errors;
	}

	public boolean statusIsOk() {
		if (status.equals("OK")) {
			return true;
		} else {
			return false;
		}
	}

}
