package com.musala.devops.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.musala.devops.enums.Model;
import com.musala.devops.enums.State;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Drone extends CommonFields {
	private String serialNumber;
	@Enumerated(EnumType.STRING)
	private Model model;
	private Double weightLimit = 500.0;
	private Double batteryCapacity;
	@Enumerated(EnumType.STRING)
	private State state;
	@OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
	private List<Medication> medications;
	
	public static DroneBuilder builder() {
		return new DroneBuilder();
	}
}