package com.revature.services;

import com.revature.models.User;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

	private final UserService userService;

	public AuthService(UserService userService) {
		this.userService = userService;
	}

	public Optional<User> findByCredentials(String email, String password) {
		return userService.findByCredentials(email, password);
	}

	public Optional<User> findByCredentials(String email) {
		return userService.findByCredentials(email);
	}

	public User register(User user) {
		return userService.save(user);
	}

	public boolean updatePassword(String email, String password, String newPass) {
		List<User> dbAccounts = userService.findAll();
		for (User a : dbAccounts) {
			if ((a.getEmail().equals(email)) && (a.getPassword().equals(password))) {
				a.setPassword(newPass);
				userService.save(a);
				return true;
			}
		}
		return false;
	}
}
