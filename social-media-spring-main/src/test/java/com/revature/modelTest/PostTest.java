package com.revature.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.Post;
import com.revature.models.User;

@SpringBootTest(classes = Post.class)
public class PostTest {
	
	@Test
	void postContructorNoIdTest() {
		Post post = new Post("post text", "no image", new ArrayList<Post>(), new User(), false);
		
		assertEquals(post.getId(), 0);
	}
	
	@Test
	void postToStringTest() {
		Post post = new Post("post text", "no image", new ArrayList<Post>(), new User(), false);
		
		assertEquals(post.toString(), 
				"Post [id=" + post.getId() + ", text=" + post.getText() + ", imageUrl=" + post.getImageUrl() + ", comments=" + post.getComments() + ", author="
						+ post.getAuthor() + ", comment=" + post.isComment() + "]"
				);
	}
}
