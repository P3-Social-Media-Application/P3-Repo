package com.revature.controllers;

import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.PwordModel;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000" }, allowCredentials = "true")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;

	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
		Optional<User> optional = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());

		if (!optional.isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		session.setAttribute("user", optional.get());

		return ResponseEntity.ok(optional.get());
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.removeAttribute("user");

		return ResponseEntity.ok().build();
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
		User created = new User(registerRequest.getEmail(), registerRequest.getPassword(),
				registerRequest.getFirstName(), registerRequest.getLastName());

		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(created));
	}

	@PostMapping("/change-password")
	public boolean changePass(@RequestBody PwordModel pword, HttpSession session) {
		User someUser = (User) session.getAttribute("user");
		if (authService.updatePassword(someUser.getEmail(), pword.getOldPass(), pword.getNewPass()) == true) {
			return true;
		}
		return false;

	}

	@GetMapping("/user")
	public Optional<User> user(HttpSession session) {
		User someUser = (User) session.getAttribute("user");
		return authService.findByCredentials(someUser.getEmail());

	}
}
