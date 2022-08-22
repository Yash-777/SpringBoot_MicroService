package com.github.yash777.User.controller.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;


public class HttpRequestTest extends AbstractIntegration_junit4 {

	@org.junit.jupiter.api.Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		String forObject = this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class);
		assertThat(forObject).contains("Welcome to userService");
	}
	
}
