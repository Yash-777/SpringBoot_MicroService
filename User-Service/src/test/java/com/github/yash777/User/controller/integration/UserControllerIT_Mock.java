package com.github.yash777.User.controller.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.github.yash777.User.controller.UserController;
import com.github.yash777.User.repository.h2.UserRepository;
import com.github.yash777.User.service.UserService;

@org.springframework.boot.test.context.SpringBootTest // Use one per Project
@ActiveProfiles("test")
@AutoConfigureMockMvc // NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.test.web.servlet.MockMvc' available
public class UserControllerIT_Mock {

	@Autowired
	private MockMvc mockMvc;

	//@MockBean public UserRepository repo;
	//@MockBean public UserService service;
	@InjectMocks public UserController controller;

	@org.junit.jupiter.api.Test
	public void returnUserInit() throws Exception {
		MvcResult andReturn = this.mockMvc.perform(get("/user/init"))
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn();
		//.andExpect(content().string(containsString("Yash")));
		
		System.out.println("-----: "+andReturn.getResponse().getContentAsString());
	}
	
	@org.junit.jupiter.api.Test
	public void returnUserTest() throws Exception {
		MvcResult andReturn = this.mockMvc.perform(get("/user/list"))
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn();
		//.andExpect(content().string(containsString("Yash")));
		
		System.out.println("-----: "+andReturn.getResponse().getContentAsString());
	}
	
}

