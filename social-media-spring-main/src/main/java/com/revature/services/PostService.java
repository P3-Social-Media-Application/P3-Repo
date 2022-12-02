package com.revature.services;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.revature.models.Post;
import com.revature.repositories.PostRepository;

@Service
public class PostService {

	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public List<Post> getAll() {
		return this.postRepository.findAll();
	}

	public Post upsert(Post post) {
		return this.postRepository.save(post);
	}

	public void deletePost(Post post) {
		this.postRepository.delete(post);
	}
	
	public void deleteComment(Post post) {
		this.postRepository.deleteComment(post.getId());
		this.postRepository.delete(post);
	}
	
	public Post updatePost(Post post) {
		return this.postRepository.save(post);
	}
}
