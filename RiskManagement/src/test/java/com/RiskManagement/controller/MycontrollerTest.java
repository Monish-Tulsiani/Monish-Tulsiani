package com.RiskManagement.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.RiskManagement.entities.ActionReports;
import com.RiskManagement.entities.Applications;
import com.RiskManagement.entities.ApprovedApplications;
import com.RiskManagement.entities.EmailRequest;
import com.RiskManagement.entities.JwtResponse;
import com.RiskManagement.entities.JwtResponseGetApplication;
import com.RiskManagement.entities.Otp;
import com.RiskManagement.entities.StoreOtp;
import com.RiskManagement.services.EmailService;
import com.RiskManagement.services.OtpService;
import com.RiskManagement.services.services;
@SpringBootTest
class MycontrollerTest {
	@Autowired
	Mycontroller controller;
    @MockBean
    services serve;
    @MockBean
    EmailService emailservice;
    @MockBean
    OtpService otpservice;
    
    
    
    
	@Test
	void addapplicationtest() {
		JwtResponse jwtresponse = new JwtResponse();
	    Applications application = new Applications();
		application.setCid(101);
		application.setAmount(20000);
		application.setCompanyvalue(82222);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		when(serve.addapplication(application)).thenReturn(application);
		when(serve.loansenctioned(101, 20000)).thenReturn("the amount is too small according to policies");
		jwtresponse.setMessage("the amount is too small according to policies");
		ResponseEntity<JwtResponse> result =  controller.addapplication(application);
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
		
	}
	@Test
	void reviewapplicationtest() {
		JwtResponseGetApplication jwtresponse1 = new JwtResponseGetApplication();
	    Applications application = new Applications();
		application.setCid(101);
		application.setAmount(20000);
		application.setCompanyvalue(82222);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		jwtresponse1.setAppli(application);
		jwtresponse1.setMessage("application exist");
		jwtresponse1.setResult("success");
		when(serve.getapplication(101)).thenReturn(jwtresponse1);
		JwtResponseGetApplication result = controller.getuser(101);
		assertEquals(jwtresponse1.getResult(),result.getResult());
		
		
		
	}
	
	@Test
	void changepasswordtest() {
		JwtResponse jwtresponse = new JwtResponse();
		
		jwtresponse.setResult("success");
		jwtresponse.setMessage("password changed successfully");
		when(serve.changepassword("abc", "hhh", "kkk")).thenReturn(jwtresponse);
		ResponseEntity<JwtResponse> result =  controller.changepassword("abc", "hhh", "kkk");
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
	}
	
	@Test
	void forgetpasswordtest() {
		JwtResponse jwtresponse = new JwtResponse();
	
		jwtresponse.setResult("success");
		jwtresponse.setMessage("password changed successfully");
		when(serve.forgetpassword(101, "abc", "kkk")).thenReturn(jwtresponse);
		ResponseEntity<JwtResponse> result =  controller.forgetpassword(101, "abc", "kkk");
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
		
	}
	
	@Test
	void deleteapplicationtest_applicationSouldBeDeleted() {
		JwtResponse jwtresponse = new JwtResponse();
		Applications application = new Applications();
		jwtresponse.setResult("success");
		jwtresponse.setMessage("application has been deleted");
		when(serve.deleteapplication(application)).thenReturn(jwtresponse);
		ResponseEntity<JwtResponse> result =  controller.deleteapplication(application);
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
		
	}
	@Test
	void  addreportstest() {
		JwtResponse jwtresponse = new JwtResponse();
		ActionReports report = new ActionReports();
		jwtresponse.setResult("success");
		jwtresponse.setMessage("report has been submitted");
		when(serve.addreport(report)).thenReturn(jwtresponse);
		ResponseEntity<JwtResponse> result =  controller.addreports(report);
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
		
	}
	@Test
	void  approveapplicationtest() {
		 ApprovedApplications application = new  ApprovedApplications();
		JwtResponse jwtresponse = new JwtResponse();
		
		jwtresponse.setResult("success");
		jwtresponse.setMessage("application has been submitted");
		when(serve.approvereport(application)).thenReturn(jwtresponse);
		ResponseEntity<JwtResponse> result =  controller.approveapplication(application);
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
		
	}
	@Test
	void  sendEmailtest() {
        JwtResponse jwtresponse = new JwtResponse();
		EmailRequest email = new EmailRequest();
		jwtresponse.setResult("success");
		jwtresponse.setMessage("mail has been send");
		when(emailservice.sendEmail(email.getSubject(), email.getMessage(), email.getTo())).thenReturn(true);
		ResponseEntity<JwtResponse> result =  controller.sendEmail(email);
		String String = result.getBody().getResult();
		assertEquals(jwtresponse.getResult(),String);
		
		
	}
	@Test
	void  generateotptest() {
        JwtResponse jwtresponse = new JwtResponse();
        EmailRequest email = new EmailRequest();
        int a = 123;
		Otp otp = new Otp();
		jwtresponse.setResult("faliure");
		jwtresponse.setMessage("application has been submitted");
		when(otpservice.generateOTP(otp.getEmailid())).thenReturn(a);
		email.setMessage(String.valueOf(a));
		email.setTo(otp.getEmailid());
		when(emailservice.sendEmail(email.getSubject(), email.getMessage(), email.getTo())).thenReturn(true);
		ResponseEntity<JwtResponse> result =  controller.generateotp(otp);
		String String = result.getBody().getResult();
		assertEquals(jwtresponse.getResult(),String);
	}
	@Test
	void  verifyotptest() {
		StoreOtp body = new StoreOtp();
		body.setOtp(123);
		JwtResponse jwtresponse = new JwtResponse();
		jwtresponse.setResult("success");
		jwtresponse.setMessage("corrected otp");
		when(otpservice.getOtp(body.getEmailid())).thenReturn(123);
		ResponseEntity<JwtResponse> result =  controller.verifyotp(body);
		String String = result.getBody().getResult();
		assertEquals(jwtresponse.getResult(),String);
	}
	@Test
	void addapplicationtest2() {
		JwtResponse jwtresponse = new JwtResponse();
	    Applications application = new Applications();
		application.setCid(101);
		application.setAmount(2000000);
		application.setCompanyvalue(822);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		when(serve.addapplication(application)).thenReturn(application);
		when(serve.loansenctioned(101, 2000000)).thenReturn("loan cannot be granted");
		jwtresponse.setMessage("loan cannot be granted");
		ResponseEntity<JwtResponse> result =  controller.addapplication(application);
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
		
	}
	@Test
	void addapplicationtest3() {
		JwtResponse jwtresponse = new JwtResponse();
	    Applications application = new Applications();
		application.setCid(101);
		application.setAmount(2000000);
		application.setCompanyvalue(822000000);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(355500000);
		when(serve.addapplication(application)).thenReturn(application);
		when(serve.loansenctioned(101, 2000000)).thenReturn("loan can be granted");
		jwtresponse.setMessage("loan can be granted");
		ResponseEntity<JwtResponse> result =  controller.addapplication(application);
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
		
	}	
		
	@Test
	void addapplicationtest4() {
		JwtResponse jwtresponse = new JwtResponse();
	    Applications application = new Applications();
		application.setCid(101);
		application.setAmount(20000000);
		application.setCompanyvalue(822000000);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(355500000);
		when(serve.addapplication(application)).thenReturn(application);
		when(serve.loansenctioned(101, 20000000)).thenReturn("the amount exeeds the limit and will be reviewed ny business analyst");
		jwtresponse.setMessage("the amount exeeds the limit and will be reviewed ny business analyst");
		ResponseEntity<JwtResponse> result =  controller.addapplication(application);
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
		
	}
	@Test
	void reviewapplicationtest2() {
		JwtResponseGetApplication jwtresponse1 = new JwtResponseGetApplication();
	    Applications application = new Applications();
		application.setCid(101);
		application.setAmount(20000);
		application.setCompanyvalue(82222);
		application.setCreditvalue(8);
		application.setEmailid("abs@xyz.com");
		application.setName("abs");
		application.setTurnover(3555);
		jwtresponse1.setAppli(application);
		jwtresponse1.setMessage("application does not exist");
		jwtresponse1.setResult("faliure");
		when(serve.getapplication(101)).thenReturn(jwtresponse1);
		JwtResponseGetApplication result = controller.getuser(101);
		assertEquals(jwtresponse1.getResult(),result.getResult());
		
		
		
	}
	
	@Test
	void changepasswordtest2() {
		JwtResponse jwtresponse = new JwtResponse();
		
		jwtresponse.setResult("faliure");
		jwtresponse.setMessage("password cannot be changed");
		when(serve.changepassword("abc", "hhh", "kkk")).thenReturn(jwtresponse);
		ResponseEntity<JwtResponse> result =  controller.changepassword("abc", "hhh", "kkk");
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
	}
	@Test
	void forgetpasswordtest2() {
		JwtResponse jwtresponse = new JwtResponse();
	
		jwtresponse.setResult("faliure");
		jwtresponse.setMessage("password cannor be changed");
		when(serve.forgetpassword(101, "abc", "kkk")).thenReturn(jwtresponse);
		ResponseEntity<JwtResponse> result =  controller.forgetpassword(101, "abc", "kkk");
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
		
	}
	@Test
	void  addreportstest2() {
		JwtResponse jwtresponse = new JwtResponse();
		ActionReports report = new ActionReports();
		jwtresponse.setResult("faliure");
		jwtresponse.setMessage("report has not been submitted");
		when(serve.addreport(report)).thenReturn(jwtresponse);
		ResponseEntity<JwtResponse> result =  controller.addreports(report);
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
		
	}
	@Test
	void  approveapplicationtest2() {
		 ApprovedApplications application = new  ApprovedApplications();
		JwtResponse jwtresponse = new JwtResponse();
		
		jwtresponse.setResult("faliure");
		jwtresponse.setMessage("application has not been submitted");
		when(serve.approvereport(application)).thenReturn(jwtresponse);
		ResponseEntity<JwtResponse> result =  controller.approveapplication(application);
		String String = result.getBody().getMessage();
		assertEquals(jwtresponse.getMessage(),String);
		
	}
	@Test
	void  sendEmailtest2() {
        JwtResponse jwtresponse = new JwtResponse();
		EmailRequest email = new EmailRequest();
		jwtresponse.setResult("faliure");
		jwtresponse.setMessage("mail has not  been send");
		when(emailservice.sendEmail(email.getSubject(), email.getMessage(), email.getTo())).thenReturn(false);
		ResponseEntity<JwtResponse> result =  controller.sendEmail(email);
		String String = result.getBody().getResult();
		assertEquals(jwtresponse.getResult(),String);
		
		
	}
	@Test
	void  verifyotptest2() {
		StoreOtp body = new StoreOtp();
		body.setOtp(1234);
		JwtResponse jwtresponse = new JwtResponse();
		jwtresponse.setResult("faliure");
		jwtresponse.setMessage("incorrect otp");
		when(otpservice.getOtp(body.getEmailid())).thenReturn(123);
		ResponseEntity<JwtResponse> result =  controller.verifyotp(body);
		String String = result.getBody().getResult();
		assertEquals(jwtresponse.getResult(),String);
	}

}
