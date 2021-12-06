package com.musala.devops;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.musala.devops.config.ConfigProperties;
import com.musala.devops.dtos.MedicationDTO;
import com.musala.devops.enums.Model;
import com.musala.devops.helpers.Util;
import com.musala.devops.models.Drone;
import com.musala.devops.service.DroneService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MusalaDroneAppApplicationTests {
	@Autowired
	private DroneService droneService;
	@Autowired
	private Util util;
	@Autowired
	private ConfigProperties props;

	@Test
	public void manageNormalMedWeight() {
		Drone drone = new Drone();
		drone.setModel(Model.HEAVYWEIGHT);
		drone.setCurrentLoadWeight(0.0);
		drone.setBatteryCapacity(100.0);
		drone.setSerialNumber("374893821917");
		drone.setWeightLimit(props.getMaxLoadWeight());
		List<MedicationDTO> medicationDTOs = new ArrayList<>();
//		assertEquals(prop.getAccountNumberLength(), accountNumber1.length()); 
	}

}