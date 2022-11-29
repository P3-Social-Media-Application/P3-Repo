package com.revature.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.AboutInfo;
import com.revature.services.AboutService;

@RestController
@RequestMapping("/about")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class AboutController {
	
	 private final AboutService aboutService;

	    public AboutController(AboutService aboutService) {
	        this.aboutService = aboutService;
	        
	    }
	    
	    @PostMapping("/about-info")
		
		public void registerNewAccount(@RequestBody AboutInfo info) {
			aboutService.save(info);
		}

}
