package com.revature.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.UserController;
import com.revature.models.User;
import com.revature.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	MockHttpSession session = new MockHttpSession();

	private static ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void getUserTest() throws Exception {
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		session.setAttribute("user", user);
		
		when(userService.findById(user.getId())).thenReturn(user);
		
		MvcResult requestResult = mockMvc.perform(get("/user").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String actualResult = requestResult.getResponse().getContentAsString();
		
		User userResult = new ObjectMapper().readValue(actualResult, User.class);
		
		user.setPassword("");
		
		assertThat(userResult).isEqualTo(user);
	}
	
	@Test
	void checkUserExistsByEmailSuccessTest() throws Exception {
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		session.setAttribute("user", user);
		
		when(userService.findUserByEmail(user.getEmail())).thenReturn(user);
		
		String json = mapper.writeValueAsString(user);
		
		MvcResult requestResult = mockMvc.perform(post("/user").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String actualResult = requestResult.getResponse().getContentAsString();
		
		Boolean userResult = new ObjectMapper().readValue(actualResult, Boolean.class);
		
		assertThat(userResult).isTrue();
	}

	@Test
	void checkUserExistsByEmailFailureTest() throws Exception {
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		session.setAttribute("user", user);
		
		when(userService.findUserByEmail(user.getEmail())).thenReturn(null);
		
		String json = mapper.writeValueAsString(user);
		
		MvcResult requestResult = mockMvc.perform(post("/user").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String actualResult = requestResult.getResponse().getContentAsString();
		
		Boolean userResult = new ObjectMapper().readValue(actualResult, Boolean.class);
		
		assertThat(userResult).isFalse();
	}
}
