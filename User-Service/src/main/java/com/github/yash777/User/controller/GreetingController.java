package com.github.yash777.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.yash777.User.service.GreetingService;


@RestController
public class GreetingController {

	@Autowired
	private GreetingService service;

//	public GreetingController(GreetingService service) {
//		this.service = service;
//	}

	@RequestMapping("/greeting")
	public @ResponseBody String greeting() {
		return service.greet();
	}

}
