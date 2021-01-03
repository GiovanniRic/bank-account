package com.fabrick.bank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;

import com.fabrick.bank.account.config.SanboxConfig;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(SanboxConfig.class)
public class BankAccountApplication {
	

	public static void main(String[] args) {
		//SpringApplication.run(BankAccountApplication.class, args);
		SpringApplication app = new SpringApplication(BankAccountApplication.class);
		app.setAdditionalProfiles("application-runner");
		app.run();
	}
}
