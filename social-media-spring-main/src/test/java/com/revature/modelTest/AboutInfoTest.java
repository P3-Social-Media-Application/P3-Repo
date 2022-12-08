package com.revature.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.AboutInfo;

@SpringBootTest(classes = AboutInfo.class)
public class AboutInfoTest {
	
	@Test
	void AboutInfoToStringTest() {
		AboutInfo info = new AboutInfo(99, "about me text", 999);
		
		assertEquals(info.toString(), "AboutInfo [id=" + info.getId() + ", aboutMe=" + info.getAboutMe() + ", userID=" + info.getUserID() + "]");
	}
}
