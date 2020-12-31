package com.fabrick.bank.account.model.view;

import java.util.List;

public class PayloadTransactionView {

	private List<OperationView> operations;

	public PayloadTransactionView() {
	}

	public List<OperationView> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationView> operations) {
		this.operations = operations;
	}
}
