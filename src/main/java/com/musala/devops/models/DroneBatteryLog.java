package com.musala.devops.models;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class DroneBatteryLog extends CommonFields {
	private Long droneId;
	private Double batteryLevel;
}
