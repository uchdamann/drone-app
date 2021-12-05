package com.musala.devops.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.devops.dtos.DroneDTO;
import com.musala.devops.dtos.MedicationDTO;
import com.musala.devops.dtos.NewDroneDTO;
import com.musala.devops.dtos.ResponseDTO;
import com.musala.devops.enums.State;
import com.musala.devops.service.DroneService;

@RestController
@RequestMapping("/app/v1")
@CrossOrigin(maxAge = 3600, origins = "*")
public class DispatchController {

	
	
	//	TODO: Validate DTOs
	//	scheduling
	//	
	
	
	
	
	
	@Autowired
	public DroneService droneService;
	
	@PostMapping("/save-drone")
	public ResponseDTO<List<DroneDTO>> saveDrone(@RequestBody @Valid List<NewDroneDTO> newDroneDTOs) {
		return droneService.registerDrone(newDroneDTOs);
	}

	@GetMapping("/get-available-drones/{droneState}")
	public ResponseDTO<List<DroneDTO>> getAvailableDrones(@PathVariable State droneState) {
		return droneService.getAvailableDrones(droneState);
	}
	
	@PutMapping("/load-drone/{droneId}")
	public ResponseDTO<DroneDTO> loadDrone(@PathVariable Long droneId, @RequestBody @Valid List<MedicationDTO> medicationDTOs) {
		return droneService.loadDrone(droneId, medicationDTOs);
	}
	
	@GetMapping("/get-load/{droneId}")
	public ResponseDTO<List<MedicationDTO>> getDroneLoad (@PathVariable Long droneId) {
		return droneService.getMedications(droneId);
	}
	
	@GetMapping("/get-battery/{droneId}")
	public ResponseDTO<Double> getDroneBattery(@PathVariable Long droneId) {
		return droneService.getBatteryLevel(droneId);
	}
}