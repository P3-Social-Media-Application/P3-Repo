package com.revature.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.models.Likes;
import com.revature.repositories.LikesRepository;
import com.revature.services.LikesService;

@SpringBootTest(classes = LikesService.class)
public class LikesServiceTest {
	@MockBean
	private LikesRepository likesRepository;
	
	@Autowired
	private LikesService likesService;
	
	@Test
	void saveTest() {
		Likes like = new Likes(999, 9999, "testuser@gmail.com");

		when(likesRepository.save(like)).thenReturn(like);

		Likes actualResult = likesService.save(like);
		
		assertThat(actualResult).isEqualTo(like);
	}
	
	@Test
	void getLikesTest() {
		List<Likes> likesList = new LinkedList<>();
		Likes like1 = new Likes(999, 9999, "testuser@gmail.com");
		Likes like2 = new Likes(1000, 9999, "testuser2@gmail.com");
		likesList.add(like1);
		likesList.add(like2);

		when(likesRepository.findAllByPostID(like1.getPostID())).thenReturn(likesList);
		
		List<Likes> actualResult = likesService.getLikes(like1.getPostID());
		
		assertThat(actualResult).isEqualTo(likesList);
	}
}
