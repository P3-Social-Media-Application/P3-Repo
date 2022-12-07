package com.revature.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import com.revature.services.PostService;
import com.revature.services.UserService;

@SpringBootTest(classes = AuthService.class)
public class AuthServiceTest {
	@MockBean
	private UserService userService;

	@Autowired
	private AuthService authService;

	User user;

	@BeforeEach
	void setUp() {
		this.user = new User("testemail@example.com", "password", "firstName", "lastName");
	}

	@Test
	void findByCredentialsEmailPasswordTest() {
		Optional<User> returningUser = Optional.of(this.user);
		when(userService.findByCredentials("testemail@example.com", "password")).thenReturn(returningUser);
		Optional<User> actualResult = authService.findByCredentials("testemail@example.com", "password");
		assertThat(actualResult).isEqualTo(returningUser);
	}

	@Test
	void findByCredentialsEmailTest() {
		Optional<User> returningUser = Optional.of(this.user);
		when(userService.findByCredentials("testemail@example.com")).thenReturn(returningUser);
		Optional<User> actualResult = authService.findByCredentials("testemail@example.com");
		assertThat(actualResult).isEqualTo(returningUser);
	}

	@Test
	void registerTest() {
		when(userService.save(this.user)).thenReturn(user);
		User actualResult = authService.register(user);
		assertThat(actualResult).isEqualTo(user);
	}

	@Test
	void updatePasswordSuccessTest() {
		List<User> userList = new LinkedList<>();
		userList.add(user);
		userList.add(new User());

		when(userService.findAll()).thenReturn(userList);
		when(userService.save(user)).thenReturn(user);
		
		boolean actualResult = authService.updatePassword("testemail@example.com", "password", "password1");
		
		assertThat(actualResult).isTrue();
	}

	@Test
	void updatePasswordFailureTest() {
		User otherUser = new User("testemail@example.com", "password", "firstName", "lastName");
		List<User> userList = new LinkedList<>();
		userList.add(user);
		userList.add(otherUser);

		when(userService.findAll()).thenReturn(userList);
		
		boolean actualResult = authService.updatePassword("testemailfail@example.com", "password", "password1");
		
		assertThat(actualResult).isFalse();
	}
}
