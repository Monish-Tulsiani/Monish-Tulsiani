package com.RiskManagement.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;

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
@SpringBootTest
class ServicesTest {
	@Autowired 
	private services serve ;
	@MockBean
	private ApplicationsRepo applirepo;
	@MockBean
	private UserRepository userrepo;
	@MockBean
	private ApprovedApplicationsRepo approvedrepo;
	@MockBean
	private ActionReportsRepo reportrepo;
	

	@Test
	void addapplicationtest() {
		Applications application = new Applications();
		application.setCid(101);
		application.setAmount(20000);
		application.setCompanyvalue(82222);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		
		when(applirepo.save(application)).thenReturn(application);
		assertEquals(application,serve.addapplication(application));
		
		
	}
	@Test
	void getapplicationtest() {
		JwtResponseGetApplication jwtresponse = new JwtResponseGetApplication();
		Applications application = new Applications();
		application.setCid(101);
		application.setAmount(20000);
		application.setCompanyvalue(82222);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		jwtresponse.setAppli(application);
		jwtresponse.setResult("success");
		jwtresponse.setMessage("application exis");
		serve.addapplication(application);
		when(applirepo.existsById(101)).thenReturn(true);
		when(applirepo.getById(101)).thenReturn(application);
		JwtResponseGetApplication result = serve.getapplication(101);
		assertEquals(jwtresponse.getResult(),result.getResult());
		
		
	}
	@Test
	void loansenctionedtest() {
		Applications application = new Applications();
		application.setCid(101);
		application.setAmount(200000);
		application.setCompanyvalue(82222);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		when(applirepo.getById(101)).thenReturn(application);
		String result = "loan cannot be granted";
		assertEquals(result,serve.loansenctioned(101, 200000));
		
				
		
		
		
	}
	@Test
	void changepassworddtest() {
		JwtResponse jwtresponse = new JwtResponse();
		User user = new User();
		user.setId(101);
		user.setEmail("abc@xyz.com");
		user.setUserName("abc");
		user.setPassword("hhh");
		jwtresponse.setResult("success");
		jwtresponse.setMessage("password changed successfully");
		when(userrepo.findByUserName("abc")).thenReturn(user);
		JwtResponse result = serve.changepassword("abc", "hhh", "kkk");
		assertEquals(jwtresponse.getMessage(),result.getMessage());
		
		
	}
	@Test
	void forgetpassworddtest() {
		JwtResponse jwtresponse = new JwtResponse();
		User user = new User();
		user.setId(101);
		user.setEmail("abc@xyz.com");
		user.setUserName("abc");
		user.setPassword("hhh");
		jwtresponse.setResult("success");
		jwtresponse.setMessage("password changed successfully");
		when(userrepo.findByUserName("abc")).thenReturn(user);
		JwtResponse result = serve.forgetpassword(101, "abc", "kkk");
		assertEquals(jwtresponse.getResult(),result.getResult());
	}
	@Test
	void deleteapplicationtest() {
		JwtResponse jwtresponse = new JwtResponse();
		Applications application = new Applications();
		application.setCid(101);
		application.setAmount(200000);
		application.setCompanyvalue(82222);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		jwtresponse.setResult("success");
		jwtresponse.setMessage("application has been deleted");
		when(applirepo.existsById(101)).thenReturn(true);
		
		JwtResponse result = serve.deleteapplication(application);
		assertEquals(jwtresponse.getResult(),result.getResult());
		
		
	}
	@Test
	void addreporttest() {
		JwtResponse jwtresponse = new JwtResponse();
		ActionReports report = new ActionReports();
		report.setBaid(101);
		report.setCid(3400);
		report.setDescription("example report");
		report.setId(1);
		report.setRejectingparameter("example parameter");
		report.setResult("arrroved");
		jwtresponse.setResult("success");
		jwtresponse.setMessage("report has been submitted");
		when(reportrepo.save(report)).thenReturn(report);
		JwtResponse result = serve.addreport(report);
		assertEquals(jwtresponse.getResult(),result.getResult());
		
		
		
	}
	@Test
	void approvereporttest() {
		 JwtResponse jwtresponse = new JwtResponse();
		 ApprovedApplications application = new  ApprovedApplications();
		 application.setId(1);
		 application.setAction("approved");
		 application.setAmount(200000);
		 application.setBaid(101);
		 application.setCid(3400);
		 application.setCompanyvalue(200000);
		 application.setCreditvalue(8);
		 application.setEmailid("abc@xyz.com");
		 application.setName("abc");
		 application.setTurnover(45000000);
		 jwtresponse.setResult("success");
		 jwtresponse.setMessage("report has been submitted");
		 when(approvedrepo.save(application)).thenReturn(application);
		 JwtResponse result = serve.approvereport(application);
		 assertEquals(jwtresponse.getResult(),result.getResult());
		 
		 
		 
		
	}
	@Test
	void getapplicationtest2() {
		JwtResponseGetApplication jwtresponse = new JwtResponseGetApplication();
		Applications application = new Applications();
		application.setCid(101);
		application.setAmount(20000);
		application.setCompanyvalue(82222);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		jwtresponse.setAppli(application);
		jwtresponse.setResult("faliur");
		jwtresponse.setMessage("application dosent  exis");
		serve.addapplication(application);
		when(applirepo.existsById(101)).thenReturn(false);
		when(applirepo.getById(101)).thenReturn(application);
		JwtResponseGetApplication result = serve.getapplication(101);
		assertEquals(jwtresponse.getResult(),result.getResult());
	}
	@Test
	void loansenctionedtest2() {
		Applications application = new Applications();
		application.setCid(101);
		application.setAmount(200000);
		application.setCompanyvalue(82200022);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(35500005);
		when(applirepo.getById(101)).thenReturn(application);
		String result = "loan can be granted";
		assertEquals(result,serve.loansenctioned(101, 200000));

	}
	
	@Test
	void forgetpassworddtest2() {
		JwtResponse jwtresponse = new JwtResponse();
		User user = new User();
		//user.setId(101);
		user.setEmail("abc@xyz.com");
        user.setUserName("abc");
		user.setPassword("hhh");
		jwtresponse.setResult("faliure");
		jwtresponse.setMessage("cannot change password");
		when(userrepo.findByUserName("abc")).thenReturn(user);
		JwtResponse result = serve.forgetpassword(101, "abc", "kkk");
		assertEquals(jwtresponse.getResult(),result.getResult());
	}
	@Test
	void deleteapplicationtest2() {
		JwtResponse jwtresponse = new JwtResponse();
		Applications application = new Applications();
		application.setCid(101);
		application.setAmount(200000);
		application.setCompanyvalue(82222);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		jwtresponse.setResult("faliure");
		jwtresponse.setMessage("application has been deleted");
		when(applirepo.existsById(101)).thenReturn(false);
		
		JwtResponse result = serve.deleteapplication(application);
		assertEquals(jwtresponse.getResult(),result.getResult());
		
		
	}
	@Test
	void addreporttest2() {
		JwtResponse jwtresponse = new JwtResponse();
		ActionReports report = new ActionReports();
		report.setBaid(101);
		report.setCid(3400);
		report.setDescription("example report");
		report.setId(1);
		report.setRejectingparameter("example parameter");
		report.setResult("arrroved");
		jwtresponse.setResult("success");
		jwtresponse.setMessage("report has been submitted");
		when(reportrepo.save(report)).thenReturn(report);
		JwtResponse result = serve.addreport(report);
		assertEquals(jwtresponse.getResult(),result.getResult());
		
		
		
	}
	@Test
	void loansenctionedtest3() {
		Applications application = new Applications();
		application.setCid(101);
		application.setAmount(100000000);
		application.setCompanyvalue(82222);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		when(applirepo.getById(101)).thenReturn(application);
		String result ="the amount exeeds the limit and will be reviewed ny business analyst";
		assertEquals(result,serve.loansenctioned(101, 100000000));
		
				
		
		
		
	}
	@Test
	void loansenctionedtest4() {
		Applications application = new Applications();
		application.setCid(101);
		application.setAmount(200);
		application.setCompanyvalue(82222);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		when(applirepo.getById(101)).thenReturn(application);
		String result = "the amount is too small according to policies";
		assertEquals(result,serve.loansenctioned(101, 200));
		
				
		
		
		
	}
	
	}		
