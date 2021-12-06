package com.musala.devops.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.musala.devops.service.DroneBatteryLogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CronJobs {
	@Autowired
	private DroneBatteryLogService droneLog;
	
	@Scheduled(cron = "${app.prop.cron.job}")
	public void testLog() {
		log.info("------------>>>> Logging Drone Battery Level");
		droneLog.logBatteryLevel();
	}

}
