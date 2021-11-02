package com.RiskManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RiskManagement.entities.ApprovedApplications;



public interface ApprovedApplicationsRepo extends JpaRepository<ApprovedApplications,Integer>{

}
