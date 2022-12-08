package com.revature.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.AboutInfo;

@SpringBootTest(classes = AboutInfo.class)
public class AboutInfoTest {
	
	@Test
	void AboutInfoToStringTest() {
		AboutInfo info = new AboutInfo(999, "about me text");
		
		assertEquals(info.toString(), "AboutInfo [userID=" + info.getUserID() + ", about_Me=" + info.getAbout_Me() + "]");
	}
}
