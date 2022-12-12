package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.models.Likes;
import com.revature.repositories.LikesRepository;

@Service
public class LikesService {

	private LikesRepository likesRepo;

	public LikesService(LikesRepository likesRepo) {
		this.likesRepo = likesRepo;
	}

	public Likes save(Likes info) {
		return likesRepo.save(info);
	}

	public List<Likes> getLikes(int id) {
		return likesRepo.findAllByPostID(id);
	}
}
