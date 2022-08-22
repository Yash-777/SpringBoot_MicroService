package com.github.yash777.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.yash777.User.entity.AddressTableEntity;
import com.github.yash777.User.service.AddressService;

@RestController
@RequestMapping(value="/address")
public class AddressController {
	@Autowired
	public AddressService service;
	
	@GetMapping(path="/init", produces = "application/json")
	public AddressTableEntity insert() {
		AddressTableEntity entity = AddressTableEntity.builder().userUniqueId("GITHUB-777").present(1)
				.country("India").state("TS").city("Hyd")
				.build();
		
		return service.saveAddress(entity);
	}
	
	// [org.springframework.web.bind.MissingServletRequestParameterException: Required request parameter 'id' for method parameter type String is not present]
	@GetMapping("/current")
	public AddressTableEntity getUsers(@RequestParam String id) {
		return service.getUserCurrentAddr(id);
	}
}
