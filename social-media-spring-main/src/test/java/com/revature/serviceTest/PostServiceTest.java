package com.revature.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.revature.models.Post;
import com.revature.models.User;
import com.revature.repositories.PostRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.PostService;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PostServiceTest {
	@Mock
	private PostRepository postRepository;
	
	@Mock
	private UserRepository userRepository;
	
	private PostService postService;
	
	Post comment;
	Post post;
	User user;
	
	@BeforeEach
	void setUp() {
		this.postService = new PostService(this.postRepository);
		
		this.user = new User(
				999,
				"testemail@blahblah.com",
				"password",
				"test",
				"user"
			);
		
		this.comment = new Post(
				999999,
				"test comment",
				"no image",
				new ArrayList<Post>(),
				this.user,
				true
			);
		
		List<Post> comments = new ArrayList<Post>();
		comments.add(this.comment);
		
		this.post = new Post(
				"test post",
				"no image",
				comments,
				this.user,
				false
			);
		
		this.userRepository.save(this.user);
		this.postRepository.save(this.comment);
		this.postRepository.save(this.post);
	}
	
//	@Test
//	void getAllTest() {
//		
//	}
//	
//	@Test
//	void upsertTest() {
//		
//	}
//	
//	@Test
//	void deletePostTest() {
//		
//	}
	
	@Test
	void deleteCommentTest() {
		List<Post> commentList = postRepository.findAll();
		System.out.println(commentList.toString());
		
		postService.deleteComment(post);
		
		boolean containsComment = false;
		
		if (commentList.size() > 0) {
			for (Post p : commentList) {
				if (p.getId() == 999999) {
					containsComment = true;
				}
			}
		}
		
        assertThat(containsComment).isFalse();
	}
	
//	@Test
//	void updatePostTest() {
//		
//	}
}






