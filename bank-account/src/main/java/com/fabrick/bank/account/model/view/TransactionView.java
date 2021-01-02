package com.fabrick.bank.account.model.view;

import java.util.List;

public class TransactionView extends BaseView {

	private PayloadTransactionView payloadTransactionView;

	public TransactionView() {

	}

	public PayloadTransactionView getPayloadTransactionView() {
		return payloadTransactionView;
	}

	public void setPayloadTransactionView(PayloadTransactionView payloadTransactionView) {
		this.payloadTransactionView = payloadTransactionView;
	}

	public String getView() {
		List<OperationView> operations = payloadTransactionView.getOperations();

		String operationsString = new String();
		for (OperationView operationView : operations) {
			operationsString += "------ \n";
			operationsString += operationView.toString();
			operationsString += "------ \n";
		}

		return operationsString;

	}

}
