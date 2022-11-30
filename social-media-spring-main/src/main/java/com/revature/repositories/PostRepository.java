package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Post;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Integer>{
	
	@Modifying
	@Query(value = "DELETE FROM posts_comments WHERE comments_id = :postId",
			nativeQuery = true)
	public void deleteComment(@Param("postId") int postId);
}
