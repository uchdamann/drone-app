package com.musala.devops.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.devops.dtos.DroneDTO;
import com.musala.devops.dtos.MedicationDTO;
import com.musala.devops.dtos.NewDroneDTO;
import com.musala.devops.dtos.ResponseDTO;
import com.musala.devops.enums.State;
import com.musala.devops.exceptions.DroneDetailsException;
import com.musala.devops.helpers.Converters;
import com.musala.devops.models.Drone;
import com.musala.devops.models.Medication;
import com.musala.devops.repository.DroneRepo;
import com.musala.devops.service.DroneService;

import static com.musala.devops.enums.ResponseMessages.*;

@Service
public class DroneServiceImpl implements DroneService{
	
	@Autowired
	public DroneRepo droneRepo;
	@Autowired
	public Converters converters;

	@Override
	public ResponseDTO<List<DroneDTO>> registerDrone(List<NewDroneDTO> newDroneDTOs) {
		List<Drone> drones = converters.conv_NewDroneDTOs_Drones(newDroneDTOs);
		drones = droneRepo.saveAll(drones);
		return ResponseDTO.newInstance(DRONE_REGISTERED.getCode(), DRONE_REGISTERED.getMessage(),
				converters.conv_Drones_DroneDTOs(drones));
	}

	@Override
	public ResponseDTO<List<MedicationDTO>> getMedications(Long droneId) {
		Drone drone = droneRepo.findById(droneId).get();
		List<Medication> medications = drone.getMedications();
		return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(),
				converters.conv_Medications_MedicationDTOs(medications));
	}

	@Override
	public ResponseDTO<List<DroneDTO>> getAvailableDrones (State droneState) {
		List<Drone> drones = droneRepo.findByState(droneState).get();
		if (drones.isEmpty()) {
			throw new DroneDetailsException("No drone in "+droneState+" state currently!");
		}
		else {
			return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(),
					converters.conv_Drones_DroneDTOs(drones));
		}
	}

	@Override
	public ResponseDTO<DroneDTO> loadDrone(Long droneId, List<MedicationDTO> medicationDTOs) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResponseDTO<Double> getBatteryLevel(Long droneId) {
		// TODO Auto-generated method stub
		return null;
	}
}