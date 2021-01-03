package com.fabrick.bank.account.runner;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fabrick.bank.account.controller.BankAccountController;
import com.fabrick.bank.account.model.command.CommandWrapper;
import com.fabrick.bank.account.model.command.TypeCommand;
import com.fabrick.bank.account.model.view.BalanceView;
import com.fabrick.bank.account.model.view.MoneyTransferView;
import com.fabrick.bank.account.model.view.TransactionView;

@Component
@Profile("application-runner")
public class BankAccountRunner implements CommandLineRunner {

	@Autowired
	private BankAccountController bankAccountController;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Welcome on Bank Account Application");
		System.out.println();
		boolean exit = false;
		while (!exit) {
			System.out.println("Chose operation:");
			System.out.println("Read bank account -> Type 1");
			System.out.println("View transactions -> Type 2");
			System.out.println("Create money transfer -> Type 3");
			System.out.println("Exit-> Type 0");
			System.out.println();
			System.out.print("Your choice:");
			String input = readInput();

			if (input.equals("0")) {
				exit = true;
			}

			manageInput(input);
			System.out.println("---------------------------------------");
		}

	}

	private void manageInput(String input) {

		if (input.equals("1")) {
			manageReadAccount();
		} else if (input.equals("2")) {
			manageReadTransaction();
		} else if (input.equals("3")) {
			manageCreateMoneyTransfer();
		}

	}

	private void manageReadAccount() {
		System.out.print("Type yuor accountId:");
		String input = readInput();
		CommandWrapper command = CommandWrapper.buildCommand(TypeCommand.READ, input);
		BalanceView balance = bankAccountController.readBankAccount(command);
		System.out.println();

		System.out.println("---------------------------------------");

		if (balance.statusIsOk()) {
			System.out.println(balance);
		} else {
			balance.getErrors().forEach(System.out::print);
		}

	}

	private void manageReadTransaction() {

		System.out.print("Type yuor accountId:");
		String input = readInput();
		String commandFinal = input;

		System.out.println();

		System.out.println("Type period date (format yyyy-mm-dd)");
		System.out.print("From:");

		input = readInput();
		commandFinal += " " + input;

		System.out.println();
		System.out.print("To:");
		input = readInput();

		commandFinal += " " + input;
		CommandWrapper command = CommandWrapper.buildCommand(TypeCommand.TRANSACTIONS, commandFinal);
		TransactionView transactionView = bankAccountController.readTransactions(command);

		System.out.println("---------------------------------------");

		if (transactionView.statusIsOk()) {
			System.out.println(transactionView.getView());
		} else {
			transactionView.getErrors().forEach(System.out::print);
		}

	}

	private void manageCreateMoneyTransfer() {

		System.out.print("Type yuor accountId:");
		String input = readInput();
		String commandFinal = input;

		System.out.println();

		System.out.print("Type beneficiary name:");
		input = readInput();
		commandFinal += " " + input;
		System.out.println();

		System.out.print("Type beneficiary surname:");
		input = readInput();
		commandFinal += " " + input;
		System.out.println();

		System.out.print("Type IBAN beneficiary:");
		input = readInput();
		commandFinal += " " + input;
		System.out.println();

		System.out.print("Type description:");
		input = readInput();
		commandFinal += " " + input;
		System.out.println();

		System.out.print("Type amount:");
		input = readInput();
		commandFinal += " " + input;
		System.out.println();

		CommandWrapper command = CommandWrapper.buildCommand(TypeCommand.TRANSFER, commandFinal);
		MoneyTransferView transferView = bankAccountController.createTransfer(command);

		System.out.println("---------------------------------------");

		if (transferView.statusIsOk()) {
			System.out.println(transferView);
		} else {
			transferView.getErrors().forEach(System.out::print);
		}

	}

	private String readInput() {
		return new Scanner(System.in).nextLine();
	}
}