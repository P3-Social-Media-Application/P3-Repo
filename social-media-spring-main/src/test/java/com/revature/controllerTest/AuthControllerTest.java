package com.revature.controllerTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.revature.controllers.AuthController;
import com.revature.controllers.PostController;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.Post;
import com.revature.models.PwordModel;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.PostService;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {
	@MockBean
	private AuthService authService;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	MockHttpSession session;

	private static ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void loginSuccessTest() throws Exception {
		LoginRequest login = new LoginRequest("testtestuser@example.com", "password");
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		Optional<User> userOpt = Optional.of(user);

		when(authService.findByCredentials(login.getEmail(), login.getPassword())).thenReturn(userOpt);

		String json = mapper.writeValueAsString(login);

		MvcResult requestResult = mockMvc.perform(post("/auth/login").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();

		String actualResult = requestResult.getResponse().getContentAsString();

		User userResult = new ObjectMapper().readValue(actualResult, User.class);

		assertThat(userResult).isEqualTo(user);
	}
	
	@Test
	void loginFailureTest() throws Exception {
		LoginRequest login = new LoginRequest("testtestuser@example.com", "password");
		Optional<User> userOpt = Optional.empty();

		when(authService.findByCredentials(login.getEmail(), login.getPassword())).thenReturn(userOpt);

		String json = mapper.writeValueAsString(login);

		MvcResult requestResult = mockMvc.perform(post("/auth/login").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();

		int actualResult = requestResult.getResponse().getStatus();
		
		assertThat(actualResult).isEqualTo(400); // 400 is http code for "bad request"
	}
	
	@Test
	void logoutTest() throws Exception {
		doNothing().when(session).removeAttribute("user");
		
		mockMvc.perform(post("/auth/logout").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andReturn();
		
		verify(session, times(1)).removeAttribute("user");
	}
	
	@Test
	void registerTest() throws Exception {
		RegisterRequest register = new RegisterRequest("testtestuser@example.com", "password", "test", "testuser");
		User user = new User("testtestuser@example.com", "password", "test", "testuser");

		when(authService.register(user)).thenReturn(user);

		String json = mapper.writeValueAsString(register);

		MvcResult requestResult = mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();

		String actualResult = requestResult.getResponse().getContentAsString();

		User userResult = new ObjectMapper().readValue(actualResult, User.class);

		assertThat(userResult).isEqualTo(user);
	}
	
	@Test
	void changePassSuccessTest() throws Exception {
		PwordModel passwords = new PwordModel("password", "password1");
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		
		when(authService.updatePassword(user.getEmail(), passwords.getOldPass(), passwords.getNewPass())).thenReturn(true);
		when(session.getAttribute("user")).thenReturn(user);
		
		String json = mapper.writeValueAsString(passwords);

		mockMvc.perform(post("/auth/change-password").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		verify(authService, times(1)).updatePassword(user.getEmail(), passwords.getOldPass(), passwords.getNewPass());
	}
	
	@Test
	void changePassFailureTest() throws Exception {
		PwordModel passwords = new PwordModel("password", "password1");
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		
		when(authService.updatePassword(user.getEmail(), passwords.getOldPass(), passwords.getNewPass())).thenReturn(false);
		when(session.getAttribute("user")).thenReturn(user);
		
		String json = mapper.writeValueAsString(passwords);

		MvcResult requestResult = mockMvc.perform(post("/auth/change-password").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String actualResult = requestResult.getResponse().getContentAsString();
		
		Boolean passwordChangeResult = new ObjectMapper().readValue(actualResult, Boolean.class);
		
		assertThat(passwordChangeResult).isFalse();
	}
	
	@Test
	void userTest() throws Exception {
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		Optional<User> userOpt = Optional.of(user);
		
		when(session.getAttribute("user")).thenReturn(user);
		when(authService.findByCredentials(user.getEmail())).thenReturn(userOpt);

		MvcResult requestResult = mockMvc.perform(get("/auth/user").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String actualResult = requestResult.getResponse().getContentAsString();

		User userResult = new ObjectMapper().readValue(actualResult, User.class);
		
		assertThat(userResult).isEqualTo(user);
	}
}
