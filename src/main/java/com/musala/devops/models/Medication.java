package com.musala.devops.models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Medication extends CommonFields {
	private String name;
	private Double weight;
	private String code;
	@Lob
	private String image;
	@ManyToOne
	private Drone drone;
	
	public static MedicationBuilder builder() {
		return new MedicationBuilder();
	}
}