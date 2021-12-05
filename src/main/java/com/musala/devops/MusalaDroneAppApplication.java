package com.musala.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Musala API", version = "1.0", description = "Musala Drone App"))
public class MusalaDroneAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusalaDroneAppApplication.class, args);
	}

}
