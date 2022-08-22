package com.github.yash777.User.controller.integration;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yash777.User.controller.AddressController;
import com.github.yash777.User.entity.AddressTableEntity;

@org.springframework.boot.test.context.SpringBootTest // Use one per Project
@ActiveProfiles("test")
@AutoConfigureMockMvc // NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.test.web.servlet.MockMvc' available
public class AddressControllerIT_JDBC_Mock {

	@Autowired
	private MockMvc mockMvc; // @AutoConfigureMockMvc, @SpringBootTest
	
//	@Autowired public WebApplicationContext context;
//	@Before public void setUp() { // initialize web context
		//mockMvc = MockMvcBuilders.standaloneSetup(UserController.class).build();
//				//.webAppContextSetup(context).build();
//	}
	
	//@MockBean public AddressRepository repo;// It will create object for MockClass - @Profile("test")
	//@MockBean public AddressService service;
	@InjectMocks public AddressController controller;
	
	@org.junit.jupiter.api.Test
	public void shouldReturnDefaultMessage() throws Exception {
		MvcResult andReturn = this.mockMvc.perform(
				MockMvcRequestBuilders.get("/address/init")
				.accept(MediaType.APPLICATION_JSON)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		//.andExpect(content().string(containsString("Yash")));
		MockHttpServletResponse response = andReturn.getResponse();
		System.out.println("-----: "+response.getContentAsString());
		
		// Assert expected result
		org.junit.Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
		boolean canSerialize = mapper.canSerialize(AddressTableEntity.class);
		System.out.println("canSerialize:"+canSerialize);
		if (canSerialize) {
			AddressTableEntity obj = mapper.readValue(response.getContentAsString(), AddressTableEntity.class);
			System.out.println("Obj:"+obj); 
			assertEquals("HYD", obj.getCity());
		}
	}
}

