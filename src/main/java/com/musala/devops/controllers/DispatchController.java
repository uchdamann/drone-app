package com.musala.devops.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musala.devops.dtos.DroneDTO;
import com.musala.devops.dtos.NewDroneDTO;
import com.musala.devops.dtos.ResponseDTO;
import com.musala.devops.models.Medication;
import com.musala.devops.service.DroneService;

@RestController
@RequestMapping("/app/v1")
@CrossOrigin(maxAge = 3600, origins = "*")
public class DispatchController {
	
	@Autowired
	public DroneService droneService;
	
	@PostMapping("/savedrone")
	public ResponseDTO<DroneDTO> saveDrone(@RequestBody NewDroneDTO newDroneDTO) {
		return droneService.registerDrone(newDroneDTO);
	}
	
	@PutMapping("/loaddrone")
	public ResponseDTO<DroneDTO> loadDrone(@RequestBody List<Medication> medications) {
		return null;
	}
	
	@GetMapping("/getload")
	public ResponseDTO<List<Medication>> getDroneLoad (@PathVariable Long droneId) {
		return null;
	}
	
	@GetMapping("/getavailabledrones")
	public ResponseDTO<DroneDTO> getAvailableDrones(@RequestParam String droneState) {
		return null;
	}
	
	@GetMapping("/getbattery")
	public ResponseDTO<Double> getDroneBattery(@PathVariable Long droneId) {
		return null;
	}
}