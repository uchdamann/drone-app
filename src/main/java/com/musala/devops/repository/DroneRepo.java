package com.musala.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.devops.models.Drone;

@Repository
public interface DroneRepo extends JpaRepository<Drone, Long>{
	
}
