package com.musala.devops.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.musala.devops.enums.Model;
import com.musala.devops.enums.State;
import static com.musala.devops.enums.State.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Drone extends CommonFields {
	@Column(unique = true)
	private String serialNumber;
	@Enumerated(EnumType.STRING)
	private Model model;
	private Double weightLimit = 500.0;
	private Double batteryCapacity;
	@Enumerated(EnumType.STRING)
	private State state = IDLE;
	private Double currentLoadWeight = 0.0;
	@OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
	private List<Medication> medications;
}