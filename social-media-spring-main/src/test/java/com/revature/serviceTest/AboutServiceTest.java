package com.revature.serviceTest;

import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.models.AboutInfo;
import com.revature.repositories.AboutRepository;
import com.revature.services.AboutService;
import com.revature.services.PostService;

@SpringBootTest(classes = AboutService.class)
public class AboutServiceTest {
	@MockBean
	private AboutRepository aboutRepository;

	@Autowired
	private AboutService aboutService;

	@Test
	void saveTest() {
		AboutInfo info = new AboutInfo(999, "about text");

		when(aboutRepository.save(info)).thenReturn(info);
		AboutInfo actualResult = aboutService.save(info);
		assertThat(actualResult).isEqualTo(info);
	}

	@Test
	void getInfoTest() {
		AboutInfo info = new AboutInfo(999, "about text");
		Optional<AboutInfo> infoOpt = Optional.of(info);

		when(aboutRepository.findByUserID(999)).thenReturn(infoOpt);
		Optional<AboutInfo> actualResult = aboutService.getInfo(999);
		assertThat(actualResult).isEqualTo(infoOpt);
	}
}
