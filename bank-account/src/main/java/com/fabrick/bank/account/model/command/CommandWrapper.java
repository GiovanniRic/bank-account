package com.fabrick.bank.account.model.command;

import com.fabrick.bank.account.model.command.input.InputMoneyTransfer;

public class CommandWrapper {

	private String accountId;
	private String dateFrom;
	private String dateTo;
	private InputMoneyTransfer inputMoneyTransfer;

	private CommandWrapper(String accountId) {
		this.accountId = accountId;
	}
	
	private CommandWrapper(String accountId, InputMoneyTransfer inputMoneyTransfer) {
		this.accountId = accountId;
		this.inputMoneyTransfer = inputMoneyTransfer;
	}


	private CommandWrapper(String accountId, String dateFrom, String dateTo) {
		this.accountId = accountId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public String getAccountId() {
		return accountId;
	}
	
	public InputMoneyTransfer getInputMoneyTransfer() {
		return inputMoneyTransfer;
	}

	public static CommandWrapper buildCommand(TypeCommand type, String command) {
		return CommandBuilder.buildCommand(type, command);
	}

	private static class CommandBuilder {

		public static CommandWrapper buildCommand(TypeCommand type, String command) {

			if (type.equals(TypeCommand.READ)) {

				String[] strings = command.split(" ");
				String accountId = strings[0];
				return new CommandWrapper(accountId);

			} else if (type.equals(TypeCommand.TRANSACTIONS)) {

				String[] strings = command.split(" ");
				String accountId = strings[0];
				String dateFrom = strings[1];
				String dateTo = strings[2];

				return new CommandWrapper(accountId, dateFrom, dateTo);

			} else if (type.equals(TypeCommand.TRANSFER)) {

				String[] strings = command.split(" ");
				String accountId = strings[0];
				String name = strings[1];
				String surname = strings[2];
				String iban = strings[3];
				String description = strings[4];
				String amount = strings[5];
				
				InputMoneyTransfer inputMoney = new InputMoneyTransfer();
				inputMoney.setIban(iban);
				inputMoney.setName(name);
				inputMoney.setSurname(surname);
				inputMoney.setDescription(description);
				inputMoney.setAmount(amount);

				return new CommandWrapper(accountId, inputMoney);
			} else {
				return null;
			}
		}
	}
}
