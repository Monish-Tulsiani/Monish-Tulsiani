package com.RiskManagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.RiskManagement.dao.ActionReportsRepo;
import com.RiskManagement.dao.ApplicationsRepo;
import com.RiskManagement.dao.ApprovedApplicationsRepo;
import com.RiskManagement.dao.UserRepository;
import com.RiskManagement.entities.ActionReports;
import com.RiskManagement.entities.Applications;
import com.RiskManagement.entities.ApprovedApplications;
import com.RiskManagement.entities.JwtResponse;
import com.RiskManagement.entities.JwtResponseGetApplication;
import com.RiskManagement.entities.User;
@Service
public class Implementation implements services {
	@Autowired
	public UserRepository userrepo;
	@Autowired
	public ApplicationsRepo applicationrepo;
	@Autowired
	public ActionReportsRepo actionrepo;
	@Autowired
	public ApprovedApplicationsRepo approvedapplicationsrepo;
	
	

	@Override
	public Applications addapplication(Applications user) {
		applicationrepo.save(user);
		return user;
		
	}

	@Override
	public JwtResponseGetApplication getapplication(int cid) {
		JwtResponseGetApplication jwtresponse = new JwtResponseGetApplication();
		boolean flag = applicationrepo.existsById(cid);
		if (flag==false) {
			jwtresponse.setResult("faliur");
			jwtresponse.setMessage("application dosen't exists");
			return jwtresponse;
		}else {
		
		Applications application = this.applicationrepo.getById(cid);
		jwtresponse.setResult("success");
		jwtresponse.setMessage("application exist");
		jwtresponse.setAppli(application);
		return jwtresponse;
		
		
		
		
		}
	}

	@Override
	public List<Applications> getall() {
		return this.applicationrepo.findAll();
		
	}


	

	@Override
	public Page<Applications> Pagesort(int offset, int pagesize, String field) {
		Page<Applications> user = applicationrepo.findAll(PageRequest.of(offset, pagesize).withSort(Sort.by(Sort.Direction.DESC,field)));
 		
		return user;
		
	}

	@Override
	public String loansenctioned(int cid, int amount) {
		Applications user = applicationrepo.getById(cid);
		if(amount<=50000 && user.getCreditvalue()>=4 && user.getTurnover()>=20000 && user.getCompanyvalue()>=70000 ) {
			return "loan can be granted";
					
		} else if(amount<=100000 && user.getCreditvalue()>=5 && user.getTurnover()>=30000 && user.getCompanyvalue()>=120000) {
			  return "loan can be granted";
		} else if(amount<=1000000 && user.getCreditvalue()>=6 && user.getTurnover()>=80000 && user.getCompanyvalue()>=900000) {
			  return "loan can be granted";
		} else if(amount<=2000000 && user.getCreditvalue()>=6 && user.getTurnover()>=100000 && user.getCompanyvalue()>=1800000) {
			  return "loan can be granted";		  
		} else if(amount<=3200000 && user.getCreditvalue()>=7 && user.getTurnover()>=1000000 && user.getCompanyvalue()>=2500000) {
			  return "loan can be granted";		
		} else if(amount<=5000000 && user.getCreditvalue()>=8 && user.getTurnover()>= 1800000 && user.getCompanyvalue()>=4000000) {
			  return "loan can be granted";
		}  else if(amount<=8000000 && user.getCreditvalue()>=9 && user.getTurnover()>= 3500000 && user.getCompanyvalue()>=7500000) {
			  return "loan can be granted";
		}  else if(amount<=10000000 && user.getCreditvalue()>=10 && user.getTurnover()>=5000000 && user.getCompanyvalue()>=9000000) {
			  return "loan can be granted";
		}   else if(amount>10000000) {
			  return "the amount exeeds the limit and will be reviewed ny business analyst";
		}  else if(amount<30000) {
			  return "the amount is too small according to policies"; 
		} else {
			return "loan cannot be granted";
		}
	}

	@Override
	public JwtResponse changepassword(String username ,String oldpassword, String newpassword)  {
		 JwtResponse jwtresponse = new JwtResponse();
		 User user = userrepo.findByUserName(username);
		 String  password=user.getPassword();
		
		 if(password.equals(oldpassword)) {
			 
			 user.setPassword(newpassword);
			 this.userrepo.save(user);
			 jwtresponse.setResult("success");
			 jwtresponse.setMessage("password changed successfully");
			 return jwtresponse;
			 
		 }else {
			 jwtresponse.setResult("faliue");
			 jwtresponse.setMessage("enter correct old password");
			 return jwtresponse;
			
			 		
		 }
				 
		
	}

	@Override
	public JwtResponse forgetpassword(int id, String username, String newpassword) {
		User user = userrepo.findByUserName(username);
		JwtResponse jwtresponse = new JwtResponse();
		int secretid = user.getId();
		if(secretid==id) {
			
			user.setPassword(newpassword);
			this.userrepo.save(user);
			
			jwtresponse.setResult("success");
			jwtresponse.setMessage("the password has been changed");
			return jwtresponse;
			
					
		}else if(user==null){
			jwtresponse.setResult("faliure");
			jwtresponse.setMessage("invalid userid");
			return jwtresponse;
					
			
		}else {
			jwtresponse.setResult("faliure");
			jwtresponse.setMessage("invalid secreit id");
			return jwtresponse;
			
			
		}
		
	}

	@Override
	public JwtResponse deleteapplication(Applications user) {
		JwtResponse jwtresponse = new JwtResponse();
		boolean exist = applicationrepo.existsById(user.getCid());
		if(exist==false) {
			jwtresponse.setResult("faliure");
			jwtresponse.setMessage("application dosent exist");
			return jwtresponse;
		}else {
			this.applicationrepo.delete(user);
			jwtresponse.setResult("success");
			jwtresponse.setMessage("the application has been deleted");
			return jwtresponse;
		}
		
		
	}

	@Override
	public JwtResponse addreport(ActionReports report) {
		JwtResponse jwtresponse = new JwtResponse();
		this.actionrepo.save(report);
		jwtresponse.setResult("success");
		jwtresponse.setMessage("report has been saved successfully");
		return jwtresponse;
		
			
		
	}

	@Override
	public JwtResponse approvereport(ApprovedApplications report) {
		JwtResponse jwtresponse = new JwtResponse();
		this.approvedapplicationsrepo.save(report);
		jwtresponse.setResult("success");
		jwtresponse.setMessage("application has been saved successfully");
		return jwtresponse;
		
		
	}
	
		
		
	

}
