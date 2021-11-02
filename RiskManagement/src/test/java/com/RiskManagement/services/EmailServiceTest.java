package com.RiskManagement.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class EmailServiceTest {
	@Autowired
	EmailService emailservice;
	

	@Test
	void sendEmailtest() {
		String to = "dubeybitthal@gmail.com";
	    String subject = "test";
	    String message = "this is test email";
	  boolean result =  emailservice.sendEmail(subject, message, to);
	  assertEquals(true,result);
		
		
	}
	@Test
	void sendEmail2test() {
		String to = "dubeybitthal";
	    String subject = "test";
	    String message = "this is test email";
	  boolean result =  emailservice.sendEmail(subject, message, to);
	  assertEquals(false,result);
		
	}
	@Test
	void sendEmailtest2() {
		//String to = "dubeybitthal@gmail.com";
	    String subject = "";
	    String message = "this is test email";
	  boolean result =  emailservice.sendEmail(subject, message, null);
	  assertEquals(false,result);
		
		
	}
}
