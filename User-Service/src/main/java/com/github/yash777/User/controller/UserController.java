package com.github.yash777.User.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.yash777.User.entity.UserTable;
import com.github.yash777.User.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	public UserService service;
	
	@GetMapping("/init")
	public List<UserTable> init() {
		UserTable json = UserTable.builder()
				.firstName("Yash").lastName("M")
				.uniqueId("GITHUB-777")
				.build();
		
		service.saveUser(json);
		
		return service.getUsers();
	}
	
	@PostMapping("/save")
	public UserTable saveUser(@RequestBody UserTable user) {
		return service.saveUser(user);
	}

	@GetMapping("/list")
	public List<UserTable> getUsers() {
		return service.getUsers();
	}
}
