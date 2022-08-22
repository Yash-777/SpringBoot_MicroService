package com.github.yash777.User.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;

import javax.activation.DataSource;
import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yash777.User.controller.AddressController;
import com.github.yash777.User.entity.AddressTableEntity;
import com.github.yash777.User.repository.h2.AddressRepository;
import com.github.yash777.User.service.AddressService;

import junit.framework.Assert;
import lombok.SneakyThrows;


@WebMvcTest(AddressController.class)
//@AutoConfigureMockMvc // NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.test.web.servlet.MockMvc' available
public class AddressController_JDBC_MVC {

	@Autowired
	private MockMvc mockMvc;

	@MockBean public AddressService service;
	@InjectMocks public AddressController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(service);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
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
		
		System.out.println("-----: "+andReturn.getResponse().getContentAsString());
	}
}
