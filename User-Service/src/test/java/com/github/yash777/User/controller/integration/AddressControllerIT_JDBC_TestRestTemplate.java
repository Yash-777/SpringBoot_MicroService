package com.github.yash777.User.controller.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

// https://github.com/in28minutes/spring-unit-testing-with-junit-and-mockito/blob/master/src/test/java/com/in28minutes/unittesting/unittesting/controller/ItemControllerIT.java
@SpringBootTest(
		webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT
		,properties = { "spring.application.name=MyApp"}
		)
@ActiveProfiles("test")
public class AddressControllerIT_JDBC_TestRestTemplate {
	
// TestRestTemplate: To prevent injection problems this class intentionally does not extend RestTemplate.
// If you need access to the underlying RestTemplate use getRestTemplate(). 
// If you are using the @SpringBootTest annotation with an embedded server, a TestRestTemplate is automatically available and
// can be @Autowired into your test. 

	@Autowired
	private TestRestTemplate testRestTemplate;
	
// RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
// restTemplateBuilder.build();
// TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplateBuilder);

	@org.junit.jupiter.api.Test
	public void defaultMessage() throws Exception {
		String response = this.testRestTemplate.getForObject("/MyApp/address/init", String.class);
		System.out.println("-----: "+response);
	}
	
	@org.junit.jupiter.api.Test
	public void defaultMessageRest() throws Exception {
		RestTemplate restTemplate = this.testRestTemplate.getRestTemplate();
		String response = restTemplate.getForObject("/address/init", String.class);
		System.out.println("-----: "+response);
	}
	
}
