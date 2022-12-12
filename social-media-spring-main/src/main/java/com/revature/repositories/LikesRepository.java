package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Likes;

public interface LikesRepository extends JpaRepository<Likes, Integer>{

	Optional<Likes> findByPostID(int id);

	List<Likes> findAllByPostID(int id);
}
