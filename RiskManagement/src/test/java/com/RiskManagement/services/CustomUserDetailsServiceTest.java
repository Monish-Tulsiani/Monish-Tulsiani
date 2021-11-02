package com.RiskManagement.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import com.RiskManagement.dao.UserRepository;
import com.RiskManagement.entities.User;
@SpringBootTest
class CustomUserDetailsServiceTest {
	@MockBean
	private UserRepository repository;
	@Autowired
	private CustomUserDetailsService userservice;
	

	@Test
	void loadUserByUsernametest() {
		User user = new User();
		user.setId(101);
		user.setEmail("abc@xyz.com");
		user.setUserName("example");
		user.setPassword("test");
		String username = user.getUserName();
		when(repository.findByUserName(username)).thenReturn(user);
		UserDetails result = userservice.loadUserByUsername(username);
		String username1 = result.getUsername();
		assertEquals(user.getUserName(),username1);
		
		
		
		
		
	}

}
