package com.musala.devops.dtos;

import com.musala.devops.enums.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewDroneDTO {
	private String serialNumber;
	private Model model;
	private Double batteryCapacity;
}