package com.fabrick.bank.account.config;

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
//@Profile("application")
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

		System.out.print("--->");
		String input = readInput();
		manageInput(input);
	}

	private void manageInput(String input) {

		if (input.equals("1")) {
			manageReadAccount();
		} else if (input.equals("2")){
			manageReadTransaction();
		} else if (input.equals("3")){
			manageCreateMoneyTransfer();
		}
		
	}

	private void manageReadAccount() {
		System.out.print("Type yuor accountId:");
		String input = readInput();
		CommandWrapper command = CommandWrapper.buildCommand(TypeCommand.READ, input);
		BalanceView balance = bankAccountController.readBankAccount(command);
		System.out.println();
		System.out.println(balance);

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
	     TransactionView readTransactions = bankAccountController.readTransactions(command);
	     System.out.println(readTransactions.getView());
	     
	     
	     
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
	     MoneyTransferView createTransfer = bankAccountController.createTransfer(command);
	     
	     
	     
	     
	}


	private String readInput() {
		return new Scanner(System.in).nextLine();
	}
}
