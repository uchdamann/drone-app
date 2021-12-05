package com.musala.devops.serviceImpl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.devops.dtos.DroneDTO;
import com.musala.devops.dtos.MedicationDTO;
import com.musala.devops.dtos.NewDroneDTO;
import com.musala.devops.dtos.ResponseDTO;
import com.musala.devops.enums.State;
import com.musala.devops.exceptions.DroneDetailsException;
import com.musala.devops.exceptions.LoadWeightRestrictionException;
import com.musala.devops.helpers.Converters;
import com.musala.devops.models.Drone;
import com.musala.devops.models.Medication;
import com.musala.devops.repository.DroneRepo;
import com.musala.devops.repository.MedicationRepo;
import com.musala.devops.service.DroneService;

import static com.musala.devops.enums.ResponseMessages.*;

@Service
public class DroneServiceImpl implements DroneService{
	
	@Autowired
	public DroneRepo droneRepo;
	@Autowired
	public MedicationRepo medicationRepo;
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
		Double availableSpace = 500.0 - drone.getCurrentLoadWeight();
		if (medicationDTOs.isEmpty()) {
			throw new LoadWeightRestrictionException("No Medications found");
		}

		Double totalLoad = medicationDTOs.stream().mapToDouble(each-> each.getWeight())
				.reduce(0, (total, eachWeight) -> total + eachWeight);
		if (totalLoad <= availableSpace) {
			drone.setCurrentLoadWeight(drone.getCurrentLoadWeight() + totalLoad);
			droneRepo.save(drone);
			
			for (MedicationDTO medDTO: medicationDTOs) {
				Medication med = converters.conv_MedicationDTO_Medication(medDTO);
				med.setHasBeenLoaded(true);
				med.setDrone(drone);
				medicationRepo.save(med);
			}
			
			message = "All medications were loaded on drone";
		}
		else {
			int counter = 0;
			medicationDTOs.sort((med1, med2) -> med2.getWeight().compareTo(med1.getWeight()));
			for (MedicationDTO medDTO : medicationDTOs) {
				if (medDTO.getWeight() <= availableSpace) {
					Medication med = converters.conv_MedicationDTO_Medication(medDTO);
					med.setHasBeenLoaded(true);
					med.setDrone(drone);
					medicationRepo.save(med);
					counter++;
					
					availableSpace -= medDTO.getWeight();
					if (availableSpace == 0) {
						break;
					}
				}
			}
			
			message = "Only "+counter+" medications were added due to weight constraint";
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