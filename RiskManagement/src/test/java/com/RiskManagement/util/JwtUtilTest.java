package com.RiskManagement.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import com.RiskManagement.dao.UserRepository;
import com.RiskManagement.entities.User;
import com.RiskManagement.services.CustomUserDetailsService;
@SpringBootTest
class JwtUtilTest {
	@Autowired
	JwtUtil util ;
	@MockBean
	private UserRepository repository;
	@Autowired
	private CustomUserDetailsService userservice;
	
	

	@Test
	void extractUsernametest() {
		
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXZhdGVjaGllIiwiZXhwIjoxNjM1ODYyMjQzLCJpYXQiOjE2MzU4MjYyNDN9.5uR6j5isjrYJWyNPCQ8cuHx6NFplvKGm7GUXfxt4PV0";
		String username = util.extractUsername(token);
		
		assertEquals("javatechie",username);
		
		
	}
	@Test
	void extractExpirationtest() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXZhdGVjaGllIiwiZXhwIjoxNjM1ODYyMjQzLCJpYXQiOjE2MzU4MjYyNDN9.5uR6j5isjrYJWyNPCQ8cuHx6NFplvKGm7GUXfxt4PV0";
		
        Date date = util.extractExpiration(token);
    	System.out.println(date);
    	date.toString();
    	
    	assertEquals("Tue Nov 02 19:40:43 IST 2021",date.toString());
	
	}
	@Test
	void generateTokentest() {
		String token = util.generateToken("test1");
		assertNotNull(token);
		
	}
	@Test
	void validateToken() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXZhdGVjaGllIiwiZXhwIjoxNjM1ODYyMjQzLCJpYXQiOjE2MzU4MjYyNDN9.5uR6j5isjrYJWyNPCQ8cuHx6NFplvKGm7GUXfxt4PV0";
		User user = new User();
		user.setId(101);
		user.setEmail("abc@xyz.com");
		user.setUserName("example");
		user.setPassword("test");
		String username = user.getUserName();
		when(repository.findByUserName(username)).thenReturn(user);
		UserDetails result = userservice.loadUserByUsername(username);
		boolean result1 = util.validateToken(token, result);
		assertEquals(false,result1);
		
		
	}
	

}
