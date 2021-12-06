package com.musala.devops.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.devops.helpers.Converters;
import com.musala.devops.models.Drone;
import com.musala.devops.models.DroneBatteryLog;
import com.musala.devops.repository.DroneBatteryLogRepo;
import com.musala.devops.repository.DroneRepo;
import com.musala.devops.service.DroneBatteryLogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DroneBatteryLogServiceImpl implements DroneBatteryLogService {
	@Autowired
	private DroneRepo droneRepo;
	@Autowired
	private DroneBatteryLogRepo logRepo;
	@Autowired
	private Converters converters;
	
	@Override
	public void logBatteryLevel() {
		List<Drone> drones = droneRepo.findAll();
		if (drones.isEmpty()) {
			log.info("------->>>> No Drone Found");
			return;
		}
		List<DroneBatteryLog> droneLogs = converters.conv_Drones_DroneBatteryLogs(drones);
		logRepo.saveAll(droneLogs);
		log.info("--------->>>>> Successfully Logged "+drones.size()+" Drone(s)");
	}
}
