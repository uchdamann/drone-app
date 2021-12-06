package com.musala.devops.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("app.prop")
public class ConfigProperties {
	private Double maxLoadWeight;
	private Double maxBatteryLevel;
	private Double minBatteryLevel;
	private String encryptKey;
	private String url;
	private String driver;
	private String username;
	private String password;
	private int corePoolSize;
	private int maxPoolSize;
	private String threadPrefix;
}
