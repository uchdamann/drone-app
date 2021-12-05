package com.musala.devops.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.musala.devops.helpers.EncryptorUtil;

@Configuration
public class UtilConfig {
	
	@Autowired
	private ConfigProperties props;
	
	@Bean
	public EncryptorUtil getEncryptorUtil() {
		return new EncryptorUtil(props.getEncryptKey());
	}
}
