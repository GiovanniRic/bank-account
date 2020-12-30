package com.fabrick.bank.account.model.command;


public class CommandWrapper {

	private String accountId;
	private TypeCommand type;

	private CommandWrapper(String accountId) {
		this.accountId = accountId;
		this.type = type;
	}

	public String getAccountId() {
		return accountId;
	}

	public TypeCommand getType() {
		return type;
	}

	public static CommandWrapper buildCommand(String command) {
		return CommandBuilder.buildCommand(command);
	}

	private static class CommandBuilder {

		public static CommandWrapper buildCommand(String command) {

			String[] strings = command.split(" ");
			String accountId = strings[0];

			return new CommandWrapper(accountId);

		}

	}

}
