package com.revature.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.AboutInfo;
import com.revature.models.User;
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
		public void registerNewAccount(@RequestBody AboutInfo info, HttpSession session ) {
	    	User someUser = (User)session.getAttribute("user");
	    	info.setUserID(someUser.getId());
	    	aboutService.save(info);
		}
	    
	    @GetMapping("/get-info")
	    public Optional<AboutInfo> getAboutInfo(HttpSession session) {
	    	User someUser = (User)session.getAttribute("user");
	    	return aboutService.getInfo(someUser.getId());
	    }
}
