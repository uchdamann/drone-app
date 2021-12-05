package com.musala.devops.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResponseMessages {
	ERROR(99, "Sorry an error occurred", false), 
	SUCCESS(100, "Request has been processed Successfully", true),
	LOW_BATTERY(112, "Drone battery is lower than 25% ", false),
	DRONE_REGISTERED(200, "Drone has been registered successfully", true),
	OVERLOAD(122, "Load weight cannot exceed 500KG", true);
	
	private int code;
	private String message;
	private Boolean success;
}
