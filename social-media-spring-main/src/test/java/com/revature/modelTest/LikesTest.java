package com.revature.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.Likes;

@SpringBootTest(classes = Likes.class)
public class LikesTest {
	
	@Test
	void LikesToStringTest() {
		Likes like = new Likes(999, 9999, "testuser@gmail.com");
		
		assertEquals(like.toString() , 
				"Likes [ID=" + like.getID() + 
				", postID=" + like.getPostID() +
				", likedBy=" + like.getLikedBy() + "]"
				);
	}
}
