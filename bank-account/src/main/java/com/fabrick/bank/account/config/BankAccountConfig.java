package com.fabrick.bank.account.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fabrick.bank.account.model.response.BalanceReponse;
import com.fabrick.bank.account.model.view.BalanceView;

@Configuration
public class BankAccountConfig {
	

	@Bean
	public ModelMapper mapperBalance() {

		ModelMapper modelMapper = new ModelMapper();
		TypeMap<BalanceReponse, BalanceView> typeMap = modelMapper.createTypeMap(BalanceReponse.class, BalanceView.class);

		typeMap.addMappings(mapper -> {
			mapper.map(src -> src.getPayload().getBalance(), BalanceView::setBalance);
			mapper.map(src -> src.getPayload().getAvailableBalance(), BalanceView::setAvailableBalance);
			mapper.map(src -> src.getPayload().getDate(), BalanceView::setDate);
			mapper.map(src -> src.getPayload().getCurrency(), BalanceView::setCurrency);

		});

		return modelMapper;
	}

}
