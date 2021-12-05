package com.musala.devops.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.musala.devops.enums.Model;
import com.musala.devops.enums.State;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Drone extends CommonFields {
	private String serialNumber;
	@Enumerated(EnumType.STRING)
	private Model model;
	private Double weightLimit;
	private Double batteryCapacity;
	@Enumerated(EnumType.STRING)
	private State state;
	@OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
	private List<Medication> medications;
	
	public static DroneBuilder builder() {
		return new DroneBuilder();
	}
	

	public static class DroneBuilder{
		private String serialNumber;
		@Enumerated(EnumType.STRING)
		private Model model;
		private Double weightLimit;
		private Double batteryCapacity;
		@Enumerated(EnumType.STRING)
		private State state;
		private List<Medication> medications;
		
		
		public DroneBuilder serialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
			return this;
		}
		
		public DroneBuilder model(Model model) {
			this.model = model;
			return this;
		}
		
		public DroneBuilder weightLimit(Double weightLimit) {
			this.weightLimit = weightLimit;
			return this;
		}
		
		public DroneBuilder batteryCapacity(Double batteryCapacity) {
			this.batteryCapacity = batteryCapacity;
			return this;
		}
		
		public DroneBuilder state(State state) {
			this.state = state;
			return this;
		}
		
		public DroneBuilder medications(List<Medication> medications) {
			this.medications = medications;
			return this;
		}
		
		public Drone build() {
			Drone drone = new Drone();
			drone.serialNumber = this.serialNumber;
			drone.model = this.model;
			drone.weightLimit = this.weightLimit;
			drone.batteryCapacity = this.batteryCapacity;
			drone.state = this.state;
			drone.medications = this.medications;
			
			return drone;
		}
	}
}