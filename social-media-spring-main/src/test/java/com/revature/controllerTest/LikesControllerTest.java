package com.revature.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.revature.controllers.LikesController;
import com.revature.models.Likes;
import com.revature.models.Post;
import com.revature.services.LikesService;

@WebMvcTest(LikesController.class)
public class LikesControllerTest {
	
	@MockBean
	private LikesService likesService;
	
	@Autowired
	private MockMvc mockMvc;
	
	MockHttpSession session = new MockHttpSession();
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void addLikesTest() throws Exception {
		Likes like = new Likes(999, 9999, "testuser@gmail.com");

		when(likesService.save(like)).thenReturn(like);

		String json = mapper.writeValueAsString(like);

		mockMvc.perform(post("/likes/addlike").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();

		verify(likesService, times(1)).save(like);
	}
	
	@Test
	void getLikesTest() throws Exception {
		List<Likes> likesList = new LinkedList<>();
		Likes like1 = new Likes(999, 9999, "testuser@gmail.com");
		Likes like2 = new Likes(1000, 9999, "testuser2@gmail.com");
		likesList.add(like1);
		likesList.add(like2);

		when(likesService.getLikes(like1.getPostID())).thenReturn(likesList);

		MvcResult requestResult = mockMvc.perform(get("/likes/getlikes/" + like1.getPostID()).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String actualResult = requestResult.getResponse().getContentAsString();
		
		List<Likes> likesResult = new ObjectMapper().readValue(actualResult,
				TypeFactory.defaultInstance().constructCollectionType(List.class, Likes.class));
		
		assertThat(likesResult).isEqualTo(likesList);
	}
}











