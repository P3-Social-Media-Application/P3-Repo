package com.revature.repositoryTest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void findUserByEmail() {
		User user = new User(
				"testemail@example.com",
				"password",
				"firstName",
				"lastName");
        userRepository.save(user);
        Optional<User> actualResult = userRepository.findByEmail("testemail@example.com");
        assertThat(actualResult.get().equals(user));
    }
	
	@Test
	void 
}