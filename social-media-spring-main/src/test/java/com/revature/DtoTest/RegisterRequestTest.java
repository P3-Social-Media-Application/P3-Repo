package com.revature.DtoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.dtos.RegisterRequest;

@SpringBootTest(classes = RegisterRequest.class)
public class RegisterRequestTest {
	
	@Test
	void loginRequestToStringTest() {
		RegisterRequest request = new RegisterRequest("testuser@gmail.com", "password", "test", "user");
		
		assertEquals(request.toString(), 
				"RegisterRequest [email=" + request.getEmail() + ", password=" + request.getPassword() + 
				", firstName=" + request.getFirstName() + ", lastName="
				+ request.getLastName() + "]");
	}
}
