package com.fabrick.bank.account.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.response.Errors;
import com.fabrick.bank.account.model.response.MoneyTransferResponse;
import com.fabrick.bank.account.model.response.Operation;
import com.fabrick.bank.account.model.response.PayloadTransaction;
import com.fabrick.bank.account.model.response.TransactionResponse;
import com.fabrick.bank.account.model.view.BalanceView;
import com.fabrick.bank.account.model.view.ErrorView;
import com.fabrick.bank.account.model.view.MoneyTransferView;
import com.fabrick.bank.account.model.view.OperationView;
import com.fabrick.bank.account.model.view.PayloadTransactionView;
import com.fabrick.bank.account.model.view.TransactionView;
import com.google.gson.Gson;

@Configuration
public class MapperConfig {

	@Bean
	public ModelMapper mapperBalance() {

		ModelMapper modelMapper = new ModelMapper();

		TypeMap<Errors, ErrorView> typeMapError = modelMapper.createTypeMap(Errors.class, ErrorView.class);

		typeMapError.addMappings(mapper -> {
			mapper.map(src -> src.getCode(), ErrorView::setCode);
			mapper.map(src -> src.getDescription(), ErrorView::setDescription);
			mapper.map(src -> src.getParams(), ErrorView::setParams);
		});

		TypeMap<BalanceReponse, BalanceView> typeMap = modelMapper.createTypeMap(BalanceReponse.class,
				BalanceView.class);

		typeMap.addMappings(mapper -> {
			mapper.map(src -> src.getPayload().getBalance(), BalanceView::setBalance);
			mapper.map(src -> src.getPayload().getAvailableBalance(), BalanceView::setAvailableBalance);
			mapper.map(src -> src.getPayload().getDate(), BalanceView::setDate);
			mapper.map(src -> src.getPayload().getCurrency(), BalanceView::setCurrency);
			mapper.map(src -> src.getStatus(), BalanceView::setStatus);
			mapper.map(src -> src.getErrors(), BalanceView::setErrors);
		});

		return modelMapper;
	}

	@Bean
	public ModelMapper mapperTransaction() {

		ModelMapper modelMapper = new ModelMapper();

		TypeMap<Errors, ErrorView> typeMapError = modelMapper.createTypeMap(Errors.class, ErrorView.class);

		typeMapError.addMappings(mapper -> {
			mapper.map(src -> src.getCode(), ErrorView::setCode);
			mapper.map(src -> src.getDescription(), ErrorView::setDescription);
			mapper.map(src -> src.getParams(), ErrorView::setParams);
		});

		TypeMap<Operation, OperationView> typeMapOperation = modelMapper.createTypeMap(Operation.class,
				OperationView.class);

		typeMapOperation.addMappings(mapper -> {
			mapper.map(src -> src.getAmount(), OperationView::setAmount);
			mapper.map(src -> src.getAccountingDate(), OperationView::setAccountingDate);
			mapper.map(src -> src.getCurrency(), OperationView::setCurrency);
			mapper.map(src -> src.getDescription(), OperationView::setDescription);
			mapper.map(src -> src.getValueDate(), OperationView::setValueDate);

		});

		TypeMap<PayloadTransaction, PayloadTransactionView> typeMapPayload = modelMapper
				.createTypeMap(PayloadTransaction.class, PayloadTransactionView.class);

		typeMapPayload.addMappings(mapper -> {
			mapper.map(src -> src.getList(), PayloadTransactionView::setOperations);

		});

		TypeMap<TransactionResponse, TransactionView> typeMapTransaction = modelMapper
				.createTypeMap(TransactionResponse.class, TransactionView.class);

		typeMapTransaction.addMappings(mapper -> {
			mapper.map(src -> src.getStatus(), TransactionView::setStatus);
			mapper.map(src -> src.getErrors(), TransactionView::setErrors);
			mapper.map(src -> src.getPayload(), TransactionView::setPayloadTransactionView);

		});

		return modelMapper;
	}

	@Bean
	public ModelMapper mapperMoneyTransfer() {

		ModelMapper modelMapper = new ModelMapper();

		TypeMap<Errors, ErrorView> typeMapError = modelMapper.createTypeMap(Errors.class, ErrorView.class);

		typeMapError.addMappings(mapper -> {
			mapper.map(src -> src.getCode(), ErrorView::setCode);
			mapper.map(src -> src.getDescription(), ErrorView::setDescription);
			mapper.map(src -> src.getParams(), ErrorView::setParams);

		});

		TypeMap<MoneyTransferResponse, MoneyTransferView> typeMap = modelMapper
				.createTypeMap(MoneyTransferResponse.class, MoneyTransferView.class);

		typeMap.addMappings(mapper -> {
			mapper.map(src -> src.getStatus(), MoneyTransferView::setStatus);
			mapper.map(src -> src.getErrors(), MoneyTransferView::setErrors);
		});

		return modelMapper;

	}

}
