package com.musala.devops.helpers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.devops.config.ConfigProperties;
import com.musala.devops.dtos.DroneDTO;
import com.musala.devops.dtos.MedicationDTO;
import com.musala.devops.dtos.NewDroneDTO;
import com.musala.devops.models.Drone;
import com.musala.devops.models.DroneBatteryLog;
import com.musala.devops.models.Medication;

@Component
public class Converters {
	@Autowired
	private ConfigProperties props;
	
	public Drone conv_NewDroneDTO_Drone(NewDroneDTO newDroneDTO) {
		Drone drone = new Drone();
		drone.setModel(newDroneDTO.getModel());
		drone.setSerialNumber(newDroneDTO.getSerialNumber());
		drone.setBatteryCapacity(newDroneDTO.getBatteryCapacity());
		drone.setWeightLimit(props.getMaxLoadWeight());

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
		Medication medication = new Medication();
		medication.setName(medicationDTO.getName());
		medication.setWeight(medicationDTO.getWeight());
		medication.setCode(medicationDTO.getCode());
		medication.setImage(medicationDTO.getImage());
		
		return medication;
	}
	
	public List<Medication> conv_MedicationDTOs_Medications(List<MedicationDTO> medicationDTOs){
		return medicationDTOs.stream().map(this::conv_MedicationDTO_Medication).collect(Collectors.toList());
	}
	
	public DroneBatteryLog conv_Drone_DroneBatteryLog(Drone drone) {
		DroneBatteryLog droneLog = new DroneBatteryLog();
		droneLog.setDroneId(drone.getId());
		droneLog.setDroneSerialNumber(drone.getSerialNumber());
		droneLog.setDroneState(drone.getState());
		droneLog.setBatteryLevel(drone.getBatteryCapacity());
		return droneLog;
	}
	
	public List<DroneBatteryLog> conv_Drones_DroneBatteryLogs(List<Drone> drones) {
		return drones.stream().map(this::conv_Drone_DroneBatteryLog).collect(Collectors.toList());
	}
}
