package com.musala.devops.service;

import java.util.List;

import com.musala.devops.dtos.DroneDTO;
import com.musala.devops.dtos.NewDroneDTO;
import com.musala.devops.dtos.ResponseDTO;
import com.musala.devops.enums.State;
import com.musala.devops.models.Drone;
import com.musala.devops.models.Medication;

public interface DroneService {
	public ResponseDTO<DroneDTO> registerDrone(NewDroneDTO newDroneDTO);
	public ResponseDTO<DroneDTO> loadDrone(Long droneId, List<MedicationDTO> medicationDTOs);
	public ResponseDTO<List<MedicationDTO>> getMedications(Long droneId);
	public ResponseDTO<List<DroneDTO>> getAvailableDrones(State droneState);
	public ResponseDTO<Double> getBatteryLevel(Long droneId);
}