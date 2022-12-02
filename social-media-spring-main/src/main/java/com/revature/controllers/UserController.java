package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.PwordModel;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.PostService;
import com.revature.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
        this.userService = userService;
    }
	
	@Authorized
	@GetMapping
	public ResponseEntity<User> getUser(HttpSession session) {
		User currentUser = (User) session.getAttribute("user");
		User retrievedUser = userService.findById(currentUser.getId());
		
		retrievedUser.setPassword("");
		
		return ResponseEntity.ok(retrievedUser);
	}
	
	@PostMapping
	public ResponseEntity<Boolean> checkUserExistsByEmail(@RequestBody User user,HttpSession session) {
		User retrievedUser = userService.findUserByEmail(user.getEmail());
		
		if (retrievedUser != null) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.ok(false);
		}
		
		
	}
	
}
