package com.github.yash777.User.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.yash777.User.entity.UserTable;
import com.github.yash777.User.repository.h2.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
//@NoArgsConstructor
//@AllArgsConstructor
public class UserService {
	
	@Autowired
	public UserRepository repo;

	public UserTable saveUser(UserTable user) {
		return repo.save(user);
	}

	public List<UserTable> getUsers() {
		List<UserTable> findAll = repo.findAll();
		System.out.println("-= :"+findAll);
		return findAll;
	}

}
