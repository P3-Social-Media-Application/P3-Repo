package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.AboutInfo;
import com.revature.models.Likes;
import com.revature.models.User;
import com.revature.services.LikesService;

@RestController
@RequestMapping("/likes")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000" }, allowCredentials = "true")
public class LikesController {

	private final LikesService likesService;

	public LikesController(LikesService likesService) {
		this.likesService = likesService;
	}

	@PostMapping("/addlike")
	public void addLikes(@RequestBody Likes info, HttpSession session) {
		likesService.save(info);
	}

	@GetMapping("/getlikes/{postid}")
	public List<Likes> getLikes(@PathVariable("postid") int id) {
		return likesService.getLikes(id);
	}
}
