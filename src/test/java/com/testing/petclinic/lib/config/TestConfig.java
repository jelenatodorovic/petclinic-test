package com.testing.petclinic.lib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.testing.petclinic.lib.utilities.Driver;

@Configuration
public class TestConfig {

	@Bean
	public Driver getDriver() {
		return new Driver();
	}
}
