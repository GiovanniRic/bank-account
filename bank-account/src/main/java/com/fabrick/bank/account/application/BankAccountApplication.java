package com.fabrick.bank.account.application;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BankAccountApplication implements CommandLineRunner {
	
	@Autowired
	private static BankAccountController controller;

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Welcome on Bank Account Application");
		System.out.println();
		
		System.out.println("Chose operation:");
		System.out.println("1 -> Read bank account");
		System.out.println("2 -> View transactions");
		System.out.println("3 -> Create money transfer");
		
		String input = readInput();
		System.out.println(input);
		System.out.println();
		
	}
	
	private static void manageInput(String input) {
		
		

	}
	
	private static String readInput() {
		return  new Scanner(System.in).nextLine();	
	}

}
