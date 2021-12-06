package com.musala.devops.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.musala.devops.enums.State;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class DroneBatteryLog extends CommonFields {
	private Long droneId;
	private Double batteryLevel;
	private String droneSerialNumber;
	@Enumerated(EnumType.STRING)
	private State droneState;
}
