package com.oss.order_service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductOrderConfiguration {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
