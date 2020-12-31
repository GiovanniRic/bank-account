package com.fabrick.bank.account.model.command;

public class CommandWrapper {

	private String accountId;
	private String dateFrom;
	private String dateTo;

	private CommandWrapper(String accountId) {
		this.accountId = accountId;
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
			} else {
				return null;
			}
		}
	}
}
