package com.fabrick.bank.account.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix="sandbox")
public class SanboxConfig {

	public String getAuthSchema() {
		return authSchema;
	}
	public void setAuthSchema(String authSchema) {
		this.authSchema = authSchema;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getBaseUri() {
		return baseUri;
	}
	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	private String authSchema;
	private String apiKey;
	private String baseUri;
	private String balance;

}
