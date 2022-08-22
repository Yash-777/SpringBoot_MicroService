package com.github.yash777.User.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.github.yash777.User.service.GreetingService;

// Typically @WebMvcTest is used in combination with @MockBean or @Import to create any collaborators required by your @Controllerbeans.
// When using JUnit 4, this annotation should be used in combination with @RunWith(SpringRunner.class).

// Using this annotation will disable full auto-configuration and instead apply only configuration relevant to MVC tests 
// (i.e. @Controller, @ControllerAdvice, @JsonComponent, Converter/GenericConverter, Filter, WebMvcConfigurerand HandlerMethodArgumentResolver 
// beans but not @Component, @Service or @Repository beans). 
@WebMvcTest(GreetingController.class)
public class GreetingController_JUNIT5_MVC { // org.junit.jupiter.api.Test

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GreetingService service;

	@org.junit.jupiter.api.Test
	public void greetingShouldReturnMessageFromService() throws Exception {
		when(service.greet()).thenReturn("Hello, Mock");
		this.mockMvc.perform(get("/greeting"))
			.andDo(print())
			.andExpect(status().isOk());
				//.andExpect(content().string(containsString("Hello, World")));
	}
}