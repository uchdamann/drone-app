package com.musala.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.devops.models.Medication;

@Repository
public interface MedicationRepo extends JpaRepository<Medication, Long>{
	
}