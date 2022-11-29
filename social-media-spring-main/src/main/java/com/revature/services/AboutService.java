package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.models.AboutInfo;
import com.revature.repositories.AboutRepository;

@Service
public class AboutService {

	private AboutRepository aboutRepository;
	
	public AboutService(AboutRepository aboutRepository) {
		this.aboutRepository = aboutRepository;
	}
	
	 public AboutInfo save(AboutInfo info) {
	        return aboutRepository.save(info);
	    }
	 
}
