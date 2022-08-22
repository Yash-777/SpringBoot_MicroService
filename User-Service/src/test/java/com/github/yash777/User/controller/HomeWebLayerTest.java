package com.github.yash777.User.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest
public class HomeWebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	@org.junit.jupiter.api.Test
	public void shouldReturnDefaultMessage() throws Exception {
		MvcResult andReturn = this.mockMvc.perform(get("/")).andDo(print())
				.andExpect(status().isOk()).andReturn();
					//.andExpect(content().string(containsString("Welcome to userService")));
				
				System.out.println(andReturn);
	}
}