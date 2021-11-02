package com.RiskManagement.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class OtpServiceTest {
	@Autowired
	OtpService otpservice;
	

	@Test
	void generateOTPtest() {
		String key = "abc@xyz.com";
		int result = otpservice.generateOTP(key);
		boolean flag;
		if (result!=0) {
			flag=true;
			
		}else {
			flag=false;
			
		}
		assertEquals(true,flag);
				
		
	}
	@Test
	void getOtptest() {
		String key = "abc@xyz.com";
		int result = otpservice.generateOTP(key);
		int actualresult = otpservice.getOtp(key);
		assertEquals(result,actualresult);
		
	}
	@Test
	void clearOTPtest() {
		String key = "abc@xyz.com";
		otpservice.generateOTP(key);
		otpservice.clearOTP(key);
		int result = otpservice.getOtp(key);
		assertEquals(0,result);
		
	}

	@Test
	void generateOTPtest2() {
		String key = "";
		int result = otpservice.generateOTP(key);
		boolean flag;
		if (result!=0) {
			flag=false;
			
		}else {
			flag=true;
			
		}
		assertEquals(false,flag);
				
		
	}
	@Test
	void getOtptest2() {
		String key = "abc@xyz.com";
		int result = otpservice.generateOTP(key);
		int actualresult = otpservice.getOtp("abd");
		assertNotSame(result,  actualresult);
		
	}
	@Test
	void clearOTPtest2() {
		String key = "abc@xyz.com";
		otpservice.generateOTP(key);
		otpservice.clearOTP("abd");
		int result = otpservice.getOtp(key);
		assertNotSame(0,result);
		
	}	
	@Test
	void getOtptest3() {
		String key = "abc@xyz.com";
		int result = otpservice.generateOTP("abc");
		int actualresult = otpservice.getOtp(key);
		assertNotSame(result,  actualresult);
		
	}
	@Test
	void clearOTPtest3() {
		String key = "abc@xyz.com";
		otpservice.generateOTP("abc");
		otpservice.clearOTP(key);
		int result = otpservice.getOtp("abc");
		assertNotSame(0,result);
		
	}	
	
	

}
