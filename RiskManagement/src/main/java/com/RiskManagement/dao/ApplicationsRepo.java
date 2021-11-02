package com.RiskManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RiskManagement.entities.Applications;

public interface ApplicationsRepo extends JpaRepository<Applications,Integer> {
	
	
}
