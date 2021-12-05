package com.musala.devops.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.devops.helpers.Converters;
import com.musala.devops.repository.DroneBatteryLogRepo;
import com.musala.devops.repository.DroneRepo;
import com.musala.devops.service.DroneBatteryLogService;

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
		
		
	}
}
