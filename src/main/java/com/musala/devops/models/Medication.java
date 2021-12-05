package com.musala.devops.models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Medication extends CommonFields {
	private String name;
	private Double weight;
	private String code;
	private Boolean hasBeenLoaded;
	@Lob
	private String image;
	@ManyToOne
	private Drone drone;
	
	public static MedicationBuilder builder() {
		return new MedicationBuilder();
	}
	
	public static class MedicationBuilder{
		private String name;
		private Double weight;
		private String code;
		private Boolean hasBeenLoaded;
		private String image;
		private Drone drone;
		
		
		public MedicationBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public MedicationBuilder weight(Double weight) {
			this.weight = weight;
			return this;
		}
		
		public MedicationBuilder weightLimit(String code) {
			this.code = code;
			return this;
		}
		
		public MedicationBuilder hasBeenLoaded(Boolean hasBeenLoaded) {
			this.hasBeenLoaded = hasBeenLoaded;
			return this;
		}
		
		public MedicationBuilder image(String image) {
			this.image = image;
			return this;
		}
		
		public MedicationBuilder drone(Drone drone) {
			this.drone = drone;
			return this;
		}
		
		public Medication build() {
			Medication medication = new Medication();
			medication.name = this.name;
			medication.weight = this.weight;
			medication.code = this.code;
			medication.hasBeenLoaded = this.hasBeenLoaded;
			medication.image = this.image;
			medication.drone = this.drone;
			
			return medication;
		}
	}
}