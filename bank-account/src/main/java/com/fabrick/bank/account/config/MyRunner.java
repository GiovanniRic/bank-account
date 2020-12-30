package com.fabrick.bank.account.config;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fabrick.bank.account.controller.BankAccountController;
import com.fabrick.bank.account.model.command.CommandWrapper;
import com.fabrick.bank.account.model.view.BalanceView;

@Component
@Profile("!fabrick")
public class MyRunner implements CommandLineRunner {
	
	@Autowired
	private BankAccountController bankAccountController;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Welcome on Bank Account Application");
		System.out.println();

		System.out.println("Chose operation:");
		System.out.println("1 -> Read bank account");
		System.out.println("2 -> View transactions");
		System.out.println("3 -> Create money transfer");

		String input = readInput();
		manageInput(input);
	}

	private void manageInput(String input) {
		
		if (input.equals("1")) {
			readAccount();
		}
	}

	private void readAccount() {
		System.out.print("Digit yuor accountId:");
		String input = readInput();
		CommandWrapper command = CommandWrapper.buildCommand(input);
		BalanceView balance = bankAccountController.readBankAccount(command);
		System.out.println();
		System.out.println(balance);
		
	}

	private String readInput() {
		return new Scanner(System.in).nextLine();
	}
}
