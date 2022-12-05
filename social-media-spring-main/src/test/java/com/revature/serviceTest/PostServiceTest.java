package com.revature.serviceTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.revature.models.Post;
import com.revature.models.User;
import com.revature.repositories.PostRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.PostService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PostServiceTest {
	@MockBean
	private PostRepository postRepository;

	@Autowired
	private PostService postService;

	@Test
	void getAllTest() {
		List<Post> posts = new ArrayList<>();
		posts.add(new Post(9999, "test post text", "no image", new ArrayList<>(), new User(), false));
		posts.add(new Post(10000, "test post text", "no image", new ArrayList<>(), new User(), false));
		
		when(postRepository.findAll()).thenReturn(posts);
		List<Post> actualResult = postService.getAll();
		assertThat(actualResult).isEqualTo(posts);
	}

	@Test
	void upsertTest() {
		List<Post> comments = new ArrayList<>();

		Post post = new Post(9999, "test post text", "no image", comments, new User(), false);

		when(postRepository.save(post)).thenReturn(post);
		Post actualResult = postService.upsert(post);
		assertThat(actualResult).isEqualTo(post);
	}

	@Test
	void deletePostTest() {
		List<Post> comments = new ArrayList<>();

		Post post = new Post(9999, "test post text", "no image", comments, new User(), false);

		this.postService.deletePost(post);
		verify(postRepository, times(1)).delete(post);
	}

	@Test
	void deleteCommentTest() {
		List<Post> comments = new ArrayList<>();

		Post post = new Post(9999, "test post text", "no image", comments, new User(), true);

		this.postService.deleteComment(post);
		verify(postRepository, times(1)).deleteComment(9999);
	}

	@Test
	void updatePostTest() {
		Post post = new Post(9999, "test post text", "no image", new ArrayList<>(), new User(), false);
		
		when(postRepository.save(post)).thenReturn(post);
		Post actualResult = postService.updatePost(post);
		assertThat(actualResult).isEqualTo(post);
	}
}
