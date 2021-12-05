package com.musala.devops.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MedicationDTO {
	private Long id;
	private String name;
	private Double weight;
	private String code;
	private String image;
	
	public static MedicationDTOBuilder builder() {
		return new MedicationDTOBuilder();
	}
}
