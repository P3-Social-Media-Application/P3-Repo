package com.revature.DtoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.dtos.LoginRequest;

@SpringBootTest(classes = LoginRequest.class)
public class LoginRequestTest {
	
	@Test
	void loginRequestToStringTest() {
		LoginRequest request = new LoginRequest("testuser@gmail.com", "password");
		
		assertEquals(request.toString(), "LoginRequest [email=" + request.getEmail() + ", password=" + request.getPassword() + "]");
	}
}
