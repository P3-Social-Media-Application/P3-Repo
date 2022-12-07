package com.revature.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.UserService;

@SpringBootTest()
public class UserServiceTest {
	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	User user;

	@BeforeEach
	void setUp() {
		this.user = new User("testemail@example.com", "password", "firstName", "lastName");
	}

	@Test
	void findByCredentialsEmailPasswordTest() {
		Optional<User> returningUser = Optional.of(this.user);
		when(userRepository.findByEmailAndPassword("testemail@example.com", "password")).thenReturn(returningUser);
		Optional<User> actualResult = userService.findByCredentials("testemail@example.com", "password");
		assertThat(actualResult).isEqualTo(returningUser);
	}

	@Test
	void saveTest() {
		when(userRepository.save(this.user)).thenReturn(user);
		User actualResult = userService.save(user);
		assertThat(actualResult).isEqualTo(user);
	}

	@Test
	void findAllTest() {
		List<User> userList = new ArrayList<>();
		userList.add(this.user);
		userList.add(this.user);
		
		when(userRepository.findAll()).thenReturn(userList);
		List<User> actualResult = userService.findAll();
		assertThat(actualResult).isEqualTo(userList);
	}

	@Test
	void findByCredentialsEmailTest() {
		Optional<User> returningUser = Optional.of(this.user);
		when(userRepository.findByEmail("testemail@example.com")).thenReturn(returningUser);
		Optional<User> actualResult = userService.findByCredentials("testemail@example.com");
		assertThat(actualResult).isEqualTo(returningUser);
	}

	@Test
	void findByIdTest() {
		when(userRepository.getUserById(1)).thenReturn(user);
		User actualResult = userService.findById(1);
		assertThat(actualResult).isEqualTo(user);
	}

	@Test
	void findUserByEmailTest() {
		when(userRepository.findUserByEmail("testemail@example.com")).thenReturn(user);
		User actualResult = userService.findUserByEmail("testemail@example.com");
		assertThat(actualResult).isEqualTo(user);
	}
}
