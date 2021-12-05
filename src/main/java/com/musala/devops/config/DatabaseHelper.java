package com.musala.devops.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseHelper {
	
	@Bean
	@Primary
	public DataSource getDatasource() {
		return null;
	}

}
