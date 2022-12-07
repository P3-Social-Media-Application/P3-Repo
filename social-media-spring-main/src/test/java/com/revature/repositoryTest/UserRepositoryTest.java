package com.revature.repositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {
	@MockBean
	private UserRepository userRepository;
	
	User user;
	
	@BeforeEach
	void setUp() {
		this.user = new User(
				"testemail@example.com",
				"password",
				"firstName",
				"lastName");
	}
	
	@Test
	void findByEmailTest() {
		Optional<User> returningUser = Optional.of(this.user);
        when(userRepository.findByEmail("testemail@example.com")).thenReturn(returningUser);
        Optional<User> actualResult = userRepository.findByEmail("testemail@example.com");
        assertThat(actualResult.get().equals(returningUser));
    }
	
	@Test
	void findByEmailAndPasswordTest() {
		Optional<User> returningUser = Optional.of(this.user);
        when(userRepository.findByEmailAndPassword("testemail@example.com", "password")).thenReturn(returningUser);
        Optional<User> actualResult = userRepository.findByEmailAndPassword("testemail@example.com", "password");
        assertThat(actualResult.get().equals(returningUser));
	}
	
	@Test
	void getUserByIdTest() {
		when(userRepository.getUserById(1)).thenReturn(this.user);
		User actualResult = userRepository.getUserById(1);
        assertThat(actualResult.equals(this.user));
	}
	
	@Test
	void findUserByEmailTest() {
		when(userRepository.findUserByEmail("testemail@example.com")).thenReturn(this.user);
		User actualResult = userRepository.findUserByEmail("testemail@example.com");
        assertThat(actualResult.equals(this.user));
	}
}

