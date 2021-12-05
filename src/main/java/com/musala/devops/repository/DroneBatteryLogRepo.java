package com.musala.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.devops.models.DroneBatteryLog;

@Repository
public interface DroneBatteryLogRepo extends JpaRepository<DroneBatteryLog, Long> {

}
