package com.musala.devops.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.musala.devops.helpers.EncryptorUtil;

@Configuration
public class DatabaseHelper {
	@Autowired
	private ConfigProperties props;
	@Autowired
	private EncryptorUtil encryptUtil;
	
	@Bean
	@Primary
	@SuppressWarnings("rawtypes")
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(props.getDriver());
		dataSourceBuilder.url(props.getUrl());
		dataSourceBuilder.username(encryptUtil.decryptStringEncoded(props.getUsername()));
		dataSourceBuilder.password(encryptUtil.decryptStringEncoded(props.getPassword()));

		return dataSourceBuilder.build();
	}
}
