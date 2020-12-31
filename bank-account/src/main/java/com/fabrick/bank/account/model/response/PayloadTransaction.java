package com.fabrick.bank.account.model.response;

import java.util.List;

public class PayloadTransaction {

	private List<Operation> list;
	

	public PayloadTransaction() {

	}

	public List<Operation> getList() {
		return list;
	}

	public void setOperation(List<Operation> list) {
		this.list = list;
	}
}
