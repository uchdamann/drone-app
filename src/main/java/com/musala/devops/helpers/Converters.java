package com.musala.devops.helpers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.devops.config.ConfigProperties;
import com.musala.devops.dtos.DroneDTO;
import com.musala.devops.dtos.MedicationDTO;
import com.musala.devops.dtos.NewDroneDTO;
import static com.musala.devops.enums.State.*;
import com.musala.devops.models.Drone;
import com.musala.devops.models.Medication;

@Component
public class Converters {
	@Autowired
	private ConfigProperties props;
	
	public Drone conv_NewDroneDTO_Drone(NewDroneDTO newDroneDTO) {
		final double INITIALWEIGHT = 0.0;
		Drone drone = Drone.builder().model(newDroneDTO.getModel()).serialNumber(newDroneDTO.getSerialNumber())
				.batteryCapacity(newDroneDTO.getBatteryCapacity()).state(IDLE)
				.weightLimit(props.getMaxLoadWeight())
				.currentLoadWeight(INITIALWEIGHT)
				.build();
		return drone;
	}
	
	public List<Drone> conv_NewDroneDTOs_Drones(List<NewDroneDTO> newDroneDTOs){
		return newDroneDTOs.stream().map(this::conv_NewDroneDTO_Drone).collect(Collectors.toList());
	}
	
	public DroneDTO conv_Drone_DroneDTO(Drone drone) {
		DroneDTO droneDTO = null;
		if (drone.getMedications()==null || drone.getMedications().isEmpty()) {
			droneDTO = DroneDTO.builder().id(drone.getId()).model(drone.getModel())
					.serialNumber(drone.getSerialNumber()).batteryCapacity(drone.getBatteryCapacity())
					.state(drone.getState()).weightLimit(drone.getWeightLimit()).build();
		}
		else {
			droneDTO = DroneDTO.builder().id(drone.getId()).model(drone.getModel())
					.serialNumber(drone.getSerialNumber()).batteryCapacity(drone.getBatteryCapacity())
					.state(drone.getState()).weightLimit(drone.getWeightLimit())
					.medicationDTOs(conv_Medications_MedicationDTOs(drone.getMedications())).build();
		}
		
		return droneDTO;
	}
	
	public List<DroneDTO> conv_Drones_DroneDTOs(List<Drone> drones){
		return drones.stream().map(this::conv_Drone_DroneDTO).collect(Collectors.toList());
	}
	
	public MedicationDTO conv_Medication_MedicationDTO(Medication medication) {
		MedicationDTO medicationDTO = MedicationDTO.builder().id(medication.getId())
				.name(medication.getName()).weight(medication.getWeight()).code(medication.getCode())
				.image(medication.getImage()).build();
		return medicationDTO;
	}
	
	public List<MedicationDTO> conv_Medications_MedicationDTOs(List<Medication> medications){
		return medications.stream().map(this::conv_Medication_MedicationDTO).collect(Collectors.toList());
	}
	
	public Medication conv_MedicationDTO_Medication(MedicationDTO medicationDTO) {
		Medication medication = Medication.builder().name(medicationDTO.getName())
				.weight(medicationDTO.getWeight()).code(medicationDTO.getCode())
				.image(medicationDTO.getImage()).build();
		return medication;
	}
	
	public List<Medication> conv_MedicationDTOs_Medications(List<MedicationDTO> medicationDTOs){
		return medicationDTOs.stream().map(this::conv_MedicationDTO_Medication).collect(Collectors.toList());
	}
	
//	public State checkDroneState(Drone drone) {
//		State state;
//		
//		if (drone.getMedications().isEmpty()){
//			state = State.IDLE;			
//		}
//		else {
//			if ()
//		}
//		return state;
//	}
}
