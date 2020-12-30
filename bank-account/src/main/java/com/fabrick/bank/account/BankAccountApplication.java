package com.fabrick.bank.account;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;

import com.fabrick.bank.account.config.SanboxConfig;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(SanboxConfig.class)
@Profile("fabrick")
public class BankAccountApplication{

//	@Autowired
//	private BankAccountController bankAccountController;


	public static void main(String[] args) {
		SpringApplication run = new  SpringApplication(BankAccountApplication.class);
		run.setBannerMode(Banner.Mode.OFF);
		run.run(args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//		System.out.println("Welcome on Bank Account Application");
//		System.out.println();
//
//		System.out.println("Chose operation:");
//		System.out.println("1 -> Read bank account");
//		System.out.println("2 -> View transactions");
//		System.out.println("3 -> Create money transfer");
//
//		String input = readInput();
//		manageInput(input);
//	}
//
//	private void manageInput(String input) {
//		
//		if (input.equals("1")) {
//			readAccount();
//		}
//	}
//
//	private void readAccount() {
//		System.out.print("Digit yuor accountId:");
//		String input = readInput();
//		CommandWrapper command = CommandWrapper.buildCommand(input);
//		BalanceView balance = bankAccountController.readBankAccount(command);
//		System.out.println();
//		System.out.println(balance);
//		
//	}
//
//	private String readInput() {
//		return new Scanner(System.in).nextLine();
//	}

}
