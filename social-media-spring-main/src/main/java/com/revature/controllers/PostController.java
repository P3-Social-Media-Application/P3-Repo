package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.services.PostService;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000" }, allowCredentials = "true")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@Authorized
	@GetMapping
	public ResponseEntity<List<Post>> getAllPosts() {
		return ResponseEntity.ok(this.postService.getAll());
	}

	@Authorized
	@PutMapping
	public ResponseEntity<Post> upsertPost(@RequestBody Post post) {
		return ResponseEntity.ok(this.postService.upsert(post));
	}

	@Authorized
	@PostMapping
	public ResponseEntity<Object> deletePost(@RequestBody Post post, HttpSession session) {

		User currentUser = (User) session.getAttribute("user");

		if (currentUser.getId() == post.getAuthor().getId()) {
			this.postService.deletePost(post);
			return ResponseEntity.ok("Deleted post " + post.getId());
		} else {
			return null;
		}
	}

	@Authorized
	@PostMapping("/comment")
	public ResponseEntity<Object> deleteComment(@RequestBody Post post, HttpSession session) {

		User currentUser = (User) session.getAttribute("user");

		if (currentUser.getId() == post.getAuthor().getId()) {
			this.postService.deleteComment(post);
			return ResponseEntity.ok().build();
		} else {
			return null;
		}
	}

	@Authorized
	@PutMapping(value = "/updatePost", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Post> updatePost(HttpServletRequest request, @RequestBody Post post) {
		return ResponseEntity.ok(this.postService.updatePost(post));
	}
}
