package com.jwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class Home {
	@RequestMapping("/welcome")
	public String welcome() {
		String text = "Hello jwt";
		return text;
	}
	
	@RequestMapping("/getUsers")
	public String getUser() {
		return "{\"name\":\"Brijesh\"}";
	}
}
