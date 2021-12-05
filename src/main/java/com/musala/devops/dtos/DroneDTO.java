package com.musala.devops.dtos;

import java.util.List;

import com.musala.devops.enums.Model;
import com.musala.devops.enums.State;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class DroneDTO {
	private Long id;
	private String serialNumber;
	private State state;
	private Model model;
	private Double weightLimit;
	private Double batteryCapacity;
	private List<MedicationDTO> medicationDTOs;
	
	public static DroneDTOBuilder builder() {
		return new DroneDTOBuilder();
	}
}