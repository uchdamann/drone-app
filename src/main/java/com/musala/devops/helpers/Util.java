package com.musala.devops.helpers;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.devops.dtos.MedicationDTO;
import static com.musala.devops.enums.State.*;
import com.musala.devops.models.Drone;
import com.musala.devops.models.Medication;
import com.musala.devops.repository.DroneRepo;
import com.musala.devops.repository.MedicationRepo;
import com.musala.devops.service.DroneService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Util {
	
	@Autowired
	private DroneRepo droneRepo;
	@Autowired
	private MedicationRepo medicationRepo;
	@Autowired
	private Converters converters;
	@Autowired
	private DroneService droneService;
	
	@Transactional
	public String manageNormalMedWeight(Drone drone, List<MedicationDTO> medicationDTOs, Double totalLoad) {		
		drone.setCurrentLoadWeight(droneService.getCurrentLoadWeight(drone.getId()) + totalLoad);
		drone.setState(LOADING);
		drone = droneRepo.save(drone);
		log.info("-------------->>> Drone id:- {} is {} with current load of weight: {}", 
				drone.getId(), drone.getState(), drone.getCurrentLoadWeight());
		
		List<Medication> meds = new ArrayList<>();
		for (MedicationDTO medDTO: medicationDTOs) {
			Medication med = converters.conv_MedicationDTO_Medication(medDTO);
			med.setHasBeenLoaded(true);
			med.setDrone(drone);
			meds.add(medicationRepo.save(med));
		}
		drone.setMedications(meds);
		drone.setState(LOADED);
		drone = droneRepo.save(drone);
		log.info("-------------->>> Drone id:- {} is {} with current load of weight: {}", 
				drone.getId(), drone.getState(), drone.getCurrentLoadWeight());
		
		return "All medications were loaded on drone";
	}
	
	@Transactional
	public String manageExcessMedWeight(Drone drone, List<MedicationDTO> medicationDTOs, 
			Double totalLoad, Double availableSpace) {
		int counter = 0;
		Double currentLoadWeight = drone.getCurrentLoadWeight();
		drone.setState(LOADING);
		drone = droneRepo.save(drone);
		log.info("-------------->>> Drone id:- {} is {} with current load of weight: {}", 
				drone.getId(), drone.getState(), drone.getCurrentLoadWeight());
		
		medicationDTOs.sort((med1, med2) -> med2.getWeight().compareTo(med1.getWeight()));
		
		for (MedicationDTO medDTO : medicationDTOs) {
			if (medDTO.getWeight() <= availableSpace) {
				Medication med = converters.conv_MedicationDTO_Medication(medDTO);
				med.setHasBeenLoaded(true);
				med.setDrone(drone);
				medicationRepo.save(med);
				counter++;
				
				availableSpace -= medDTO.getWeight();
				currentLoadWeight += medDTO.getWeight();
				if (availableSpace == 0) {
					break;
				}
			}
		}
		drone.setCurrentLoadWeight(currentLoadWeight);
		drone.setState(LOADED);
		drone = droneRepo.save(drone);
		log.info("-------------->>> Drone id:- {} is {} with current load of weight: {}", 
				drone.getId(), drone.getState(), drone.getCurrentLoadWeight());
		
		return "Only "+counter+" medications were added due to weight constraint";
	}
}
