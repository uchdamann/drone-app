package com.musala.devops.serviceImpl;

import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.devops.config.ConfigProperties;
import com.musala.devops.dtos.DroneDTO;
import com.musala.devops.dtos.MedicationDTO;
import com.musala.devops.dtos.NewDroneDTO;
import com.musala.devops.dtos.ResponseDTO;
import com.musala.devops.enums.State;
import com.musala.devops.exceptions.DroneDetailsException;
import com.musala.devops.exceptions.LoadWeightRestrictionException;
import com.musala.devops.exceptions.LowBatteryException;
import com.musala.devops.helpers.Converters;
import com.musala.devops.helpers.Util;
import com.musala.devops.models.Drone;
import com.musala.devops.models.Medication;
import com.musala.devops.repository.DroneRepo;
import com.musala.devops.repository.MedicationRepo;
import com.musala.devops.service.DroneService;

import static com.musala.devops.enums.ResponseMessages.*;

@Service
public class DroneServiceImpl implements DroneService{
	
	@Autowired
	private DroneRepo droneRepo;
	@Autowired
	private Converters converters;
	@Autowired
	private Util util;
	@Autowired
	private ConfigProperties props;

	@Override
	@Transactional
	public ResponseDTO<List<DroneDTO>> registerDrone(List<NewDroneDTO> newDroneDTOs) {
		List<Drone> drones = converters.conv_NewDroneDTOs_Drones(newDroneDTOs);
		drones = droneRepo.saveAll(drones);
		return ResponseDTO.newInstance(DRONE_REGISTERED.getCode(), DRONE_REGISTERED.getMessage(),
				converters.conv_Drones_DroneDTOs(drones));
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
		String message;
		Drone drone = droneRepo.findById(droneId).orElseThrow(()-> new DroneDetailsException("No such drone found"));
		
		if (drone.getBatteryCapacity() < props.getMinBatteryLevel()) {
			throw new LowBatteryException();
		}
		
		Double availableSpace = props.getMaxLoadWeight() - drone.getCurrentLoadWeight();
		
		if (medicationDTOs.isEmpty()) {
			throw new LoadWeightRestrictionException("No Medications found");
		}

		Double totalLoad = medicationDTOs.stream().mapToDouble(each-> each.getWeight())
				.reduce(0, (total, eachWeight) -> total + eachWeight);
		if (totalLoad <= availableSpace) {
			message = util.normalMedWeight(drone, medicationDTOs, totalLoad);
		}
		else {
			message = util.excessMedWeight(drone, medicationDTOs, totalLoad,
					availableSpace);
		}
		
		return ResponseDTO.newInstance(SUCCESS.getCode(), message,
				converters.conv_Drone_DroneDTO(drone));	
	}

	@Override
	public ResponseDTO<List<MedicationDTO>> getMedications(Long droneId) {
		Drone drone = droneRepo.findById(droneId).orElseThrow(()-> new DroneDetailsException("No such drone found"));
		List<Medication> medications = drone.getMedications();
		if (medications.isEmpty())
			throw new LoadWeightRestrictionException("No Medications found");
		
		return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(),
				converters.conv_Medications_MedicationDTOs(medications));
	}
	
	@Override
	public ResponseDTO<Double> getBatteryLevel(Long droneId) {
		// TODO Auto-generated method stub
		return null;
	}
}