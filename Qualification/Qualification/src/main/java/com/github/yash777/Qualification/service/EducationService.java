package com.github.yash777.Qualification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.yash777.Qualification.entity.Education;
import com.github.yash777.Qualification.repository.EducaitonRepo;

@Service
public class EducationService {
	@Autowired
	public EducaitonRepo repo;

	public Education save(Education education) {
		boolean status =  repo.save(education);
		if (status) {
			return repo.getUserEducaitonDetails(education.getUserUniqueId());
		} else {
			return Education.builder().build();
		}
	}

	public Education update(Education emp) {
		return repo.updateUserEducaitonDetails(emp);
	}
	
}
