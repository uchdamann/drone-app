package com.musala.devops.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.devops.enums.State;
import com.musala.devops.models.Drone;

@Repository
public interface DroneRepo extends JpaRepository<Drone, Long>{
	public Optional<List<Drone>> findByState(State state);
}
