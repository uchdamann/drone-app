package com.musala.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.musala.devops.config.ConfigProperties;
import com.musala.devops.dtos.MedicationDTO;
import com.musala.devops.dtos.NewDroneDTO;
import com.musala.devops.enums.Model;
import static com.musala.devops.enums.State.*;
import com.musala.devops.models.Drone;
import com.musala.devops.repository.DroneRepo;
import com.musala.devops.service.DroneService;

@SpringBootTest
class MusalaDroneAppApplicationTests {
	@Autowired
	private DroneService droneService;
	@Autowired
	private ConfigProperties props;
	private Drone drone;
	private List<MedicationDTO> medicationDTOs;
	@Autowired
	private DroneRepo droneRepo;

	@BeforeEach
	public void init() {
		final int INIT_MED = 5;
		final double BASE_WEIGHT = 10.0;
		
		drone = new Drone();
		drone.setModel(Model.HEAVYWEIGHT);
		drone.setCurrentLoadWeight(0.0);
		drone.setBatteryCapacity(100.0);
		drone.setSerialNumber(String.valueOf(System.currentTimeMillis()));
		drone.setWeightLimit(props.getMaxLoadWeight());
		drone = droneRepo.save(drone);
		
		medicationDTOs = new ArrayList<>();

		for (int count = 0; count < INIT_MED; count++) {
			MedicationDTO medicationDTO = MedicationDTO.builder().code(String.valueOf(count) + new Date())
					.name(new Date().toString()).weight((count + 1) * BASE_WEIGHT).build();

			medicationDTOs.add(medicationDTO);
		}
		
		droneService.loadDrone(drone.getId(), medicationDTOs).getMessage();
	}

	@Test
	public void registerDrone() {
		List<NewDroneDTO> drones = new ArrayList<>();

		NewDroneDTO drone_ = new NewDroneDTO();
		drone_.setModel(Model.HEAVYWEIGHT);
		drone_.setBatteryCapacity(100.0);
		drone_.setSerialNumber("374893821917");

		drones.add(drone_);

		assertEquals(200, droneService.registerDrone(drones).getResponseCode());
	}

	@Test
	public void loadDrone() {
		final int INIT_MED = 10;
		final double BASE_WEIGHT = 50.0;
		
		medicationDTOs = new ArrayList<>();

		for (int count = 0; count < INIT_MED; count++) {
			MedicationDTO medicationDTO = MedicationDTO.builder().code(String.valueOf(count) + new Date())
					.name(new Date().toString()).weight((count + 1) * BASE_WEIGHT).build();

			medicationDTOs.add(medicationDTO);
		}
		
		assertEquals("Only 1 medications were added due to weight constraint", 
				droneService.loadDrone(drone.getId(), medicationDTOs).getMessage());
	}

	@Test
	public void availableDroneTest() {
		assertNotNull(droneService.getAvailableDrones(IDLE));
	}

	@Test
	public void getMedicationsTest() {
		assertNotNull(droneService.getMedications(drone.getId()).getData());
	}
	
	@Test
	public void getBatteryLevelTest() {
		assertEquals("The battery level is currently 100.0%", droneService.getBatteryLevel(drone.getId()).getData());
	}
	
	@Test
	public void getCurrentLoadWeightTest() {
		assertEquals(150.0, droneService.getCurrentLoadWeight(drone.getId()));
	}
}