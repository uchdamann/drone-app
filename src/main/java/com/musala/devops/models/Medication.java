package com.musala.devops.models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Medication extends CommonFields {
	private String name;
	private Double weight;
	private String code;
	private Boolean hasBeenLoaded;
	@Lob
	private String image;
	@ManyToOne
	private Drone drone;
}