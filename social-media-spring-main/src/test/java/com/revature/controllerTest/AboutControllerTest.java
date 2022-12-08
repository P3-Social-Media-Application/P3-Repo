package com.revature.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AboutController;
import com.revature.models.AboutInfo;
import com.revature.models.User;
import com.revature.services.AboutService;

@WebMvcTest(AboutController.class)
public class AboutControllerTest {
	@MockBean
	private AboutService aboutService;

	@Autowired
	private MockMvc mockMvc;

	MockHttpSession session = new MockHttpSession();

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	void registerNewAccountTest() throws Exception {
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		AboutInfo info = new AboutInfo(user.getId(), "about me text");
		session.setAttribute("user", user);

		when(aboutService.save(info)).thenReturn(info);

		String json = mapper.writeValueAsString(info);

		mockMvc.perform(post("/about/about-info").session(session).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andReturn();

		verify(aboutService, times(1)).save(info);
	}

	@Test
	void getAboutInfoTest() throws Exception {
		User user = new User(99999, "testtestuser@example.com", "password", "test", "testuser");
		AboutInfo info = new AboutInfo(user.getId(), "about me text");

		Optional<AboutInfo> infoOpt = Optional.of(info);

		session.setAttribute("user", user);

		when(aboutService.getInfo(user.getId())).thenReturn(infoOpt);

		MvcResult requestResult = mockMvc.perform(get("/about/get-info").session(session)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))
				.andReturn();

		String actualResult = requestResult.getResponse().getContentAsString();

		AboutInfo aboutInfoResult = new ObjectMapper().readValue(actualResult, AboutInfo.class);

		assertThat(aboutInfoResult).isEqualTo(info);
	}
}
