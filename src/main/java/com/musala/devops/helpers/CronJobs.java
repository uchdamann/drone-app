package com.musala.devops.helpers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CronJobs {
	@Scheduled(cron = "${app.prop.cron.job}")
	public void testLog() {
		log.info("------------>>>> Logging Drone Battery Level");
	}

}
