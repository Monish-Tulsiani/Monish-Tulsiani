package com.RiskManagement.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.RiskManagement.entities.ActionReports;
import com.RiskManagement.entities.Applications;
import com.RiskManagement.entities.ApprovedApplications;
import com.RiskManagement.entities.JwtResponse;
import com.RiskManagement.entities.JwtResponseGetApplication;

public interface services {
	public Applications addapplication(Applications user);
	public JwtResponseGetApplication getapplication(int cid);
	public List<Applications> getall();
	public Page<Applications> Pagesort(int offset , int pagesize,String field);
	public String loansenctioned(int cid , int amount);
	public JwtResponse changepassword(String username, String oldpassword , String newpassword);
	public JwtResponse forgetpassword(int id , String username , String newpassword);
	public JwtResponse deleteapplication(Applications user);
	public JwtResponse addreport(ActionReports report);
	public JwtResponse approvereport(ApprovedApplications report);
	
	
	
	
	
	
	
	
	
	

}
