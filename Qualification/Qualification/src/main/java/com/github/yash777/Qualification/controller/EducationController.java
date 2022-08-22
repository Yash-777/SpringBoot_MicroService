package com.github.yash777.Qualification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.yash777.Qualification.entity.Education;
import com.github.yash777.Qualification.service.EducationService;

@RestController
@RequestMapping("/eucation")
public class EducationController {
	
	@Autowired
	public EducationService service;
	
	@GetMapping
	@RequestMapping("/init")
	public Education init() {
		Education emp = Education.builder()
				.userUniqueId("GITHUB-777")
				.qualificaiton("PG")
				.school_college("OU").specialization("Computers")
				.build();
		
		return service.save(emp);
	}
	
	@GetMapping("/update")
	public Education initUpdate() {
		Education emp = Education.builder()
				.userUniqueId("GITHUB-777")
				.specialization("Computers777")
				.build();
		
		return service.update(emp);
	}

}
