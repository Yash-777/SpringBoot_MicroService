package com.github.yash777.User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.yash777.User.entity.AddressTableEntity;
import com.github.yash777.User.repository.h2.AddressRepository;

@Service
public class AddressService {
	@Autowired
	public AddressRepository repo;
	
	public AddressTableEntity getUserCurrentAddr(String userUniqueId) {
		return repo.getCurrentAddress(userUniqueId);
	}

	public AddressTableEntity saveAddress(AddressTableEntity entity) {
		return repo.saveAddress(entity);
	}
}
