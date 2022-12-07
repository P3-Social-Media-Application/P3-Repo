package com.revature.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.revature.controllers.PostController;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.services.PostService;

@WebMvcTest(PostController.class)
public class PostControllerTest {

	@MockBean
	private PostService postService;

	@Autowired
	private MockMvc mockMvc;

	MockHttpSession session = new MockHttpSession();

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	void getAllPostsTest() throws Exception {
		List<Post> postList = new ArrayList<>();
		postList.add(new Post(9999, "post text", "no image", new ArrayList<Post>(), new User(), false));

		when(postService.getAll()).thenReturn(postList);

		MvcResult requestResult = mockMvc.perform(get("/post").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andReturn();

		String actualResult = requestResult.getResponse().getContentAsString();

		List<Post> postResult = new ObjectMapper().readValue(actualResult,
				TypeFactory.defaultInstance().constructCollectionType(List.class, Post.class));

		assertThat(postResult).isEqualTo(postList);
	}

	@Test
	void upsertPostTest() throws Exception {
		Post post = new Post(9999, "post text", "no image", new ArrayList<Post>(), new User(), false);

		when(postService.upsert(post)).thenReturn(post);

		String json = mapper.writeValueAsString(post);

		MvcResult requestResult = mockMvc.perform(put("/post").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();

		String actualResult = requestResult.getResponse().getContentAsString();

		Post postResult = new ObjectMapper().readValue(actualResult, Post.class);

		assertThat(postResult).isEqualTo(post);
	}

	@Test
	void deletePostSuccessTest() throws Exception {
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		Post postTest = new Post(9999, "post text", "no image", new ArrayList<Post>(), user, false);
		session.setAttribute("user", user);

		String json = mapper.writeValueAsString(postTest);

		MvcResult requestResult = mockMvc.perform(post("/post").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();

		String actualResult = requestResult.getResponse().getContentAsString();

		assertThat(actualResult).isEqualTo("Deleted post " + postTest.getId());
	}
	
	@Test
	void deletePostFailureTest() throws Exception {
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		User userWrong = new User(99998, "testuser@example.com", "password", "test", "user");
		Post postTest = new Post(9999, "post text", "no image", new ArrayList<Post>(), userWrong, false);
		session.setAttribute("user", user);

		String json = mapper.writeValueAsString(postTest);

		MvcResult requestResult = mockMvc.perform(post("/post").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();

		String actualResult = requestResult.getResponse().getContentAsString();

		assertThat(actualResult.length()).isEqualTo(0);
	}

	@Test
	void deleteCommentSuccessTest() throws Exception {
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		Post postTest = new Post(9999, "comment text", "no image", new ArrayList<Post>(), user, true);
		session.setAttribute("user", user);

		String json = mapper.writeValueAsString(postTest);

		MvcResult requestResult = mockMvc
				.perform(post("/post/comment").session(session).contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		String actualResult = requestResult.getResponse().getContentAsString();

		assertThat(actualResult).isEqualTo("Deleted comment " + postTest.getId());
	}
	
	@Test
	void deleteCommentFailureTest() throws Exception {
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		User userWrong = new User(99998, "testuser@example.com", "password", "test", "user");
		Post postTest = new Post(9999, "post text", "no image", new ArrayList<Post>(), userWrong, false);
		session.setAttribute("user", user);

		String json = mapper.writeValueAsString(postTest);

		MvcResult requestResult = mockMvc.perform(post("/post/comment").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();

		String actualResult = requestResult.getResponse().getContentAsString();

		assertThat(actualResult.length()).isEqualTo(0);
	}

	@Test
	void updatePostTest() throws Exception {
		Post post = new Post(9999, "post text", "no image", new ArrayList<Post>(), new User(), false);

		when(postService.updatePost(post)).thenReturn(post);

		String json = mapper.writeValueAsString(post);

		MvcResult requestResult = mockMvc.perform(put("/post/updatePost").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();

		String actualResult = requestResult.getResponse().getContentAsString();

		Post postResult = new ObjectMapper().readValue(actualResult, Post.class);

		assertThat(postResult).isEqualTo(post);
	}
}