package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.AboutInfo;

public interface AboutRepository extends JpaRepository<AboutInfo, Integer> {

}
