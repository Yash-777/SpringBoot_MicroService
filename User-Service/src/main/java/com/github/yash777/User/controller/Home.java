package com.github.yash777.User.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Home {
	
	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Welcome to userService";
	}

}
