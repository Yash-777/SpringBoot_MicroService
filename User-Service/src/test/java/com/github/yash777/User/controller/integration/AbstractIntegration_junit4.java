package com.github.yash777.User.controller.integration;

import java.util.Collections;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@ContextConfiguration
@TestPropertySource(locations = "classpath:db-test.properties", properties = {"spring.profiles.active = test"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT // Jetty started on port(s) 58910 (http/1.1) with context path '/'
		
		)
//@ActiveProfiles("test")

//@WebAppConfiguration
/*
@RunWith(SpringRunner.class) – tells JUnit to run using Spring’s testing support. SpringRunner is the new name for SpringJUnit4ClassRunner. It enables full support of spring context loading and dependency injection of the beans in the tests.
@SpringBootTest – annotation that can be specified on a test class that runs Spring Boot based tests. It provides ability to start a fully running web server listening on any defined or random port.
webEnvironment – enables requests and responses to travel over network and mock servlet container is not involved.
@LocalServerPort – injects the HTTP port that got allocated at runtime.
 */
public abstract class AbstractIntegration_junit4 {
	
	@Value("classpath:accountDataSetup_hibernate.sql")  protected Resource accountScript;
	@Value("classpath:userDataSetup_jpa.sql")  protected Resource UserScript;
	
	@Autowired DataSource dataSource;

	static String HTTP_PROTOCOL = "http", LOCALHOST = "localhost", HTTP_LOCALHOST = HTTP_PROTOCOL+"://"+LOCALHOST+":";
	@LocalServerPort protected String port;
	
	String HOST_URL;
	UriComponentsBuilder uriComponentsBuilder;
	protected void setupEnvironment() {
		HOST_URL = HTTP_LOCALHOST + port;
		uriComponentsBuilder = UriComponentsBuilder.newInstance()
		.scheme(HTTP_PROTOCOL).host(LOCALHOST).port(port);
	}
	
//RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
//restTemplateBuilder.build();
//TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplateBuilder);

	protected RestTemplate restTemplate;
	@BeforeEach
	public void beforeTest() {
		restTemplate = new RestTemplate();
	}
	
	// https://www.tutorialspoint.com/spring_boot/spring_boot_rest_template.htm
	protected <T> ResponseEntity<T> getRest(final Class<T> responseType, final UriComponents uri) {
		System.out.println("GET: "+uri.toUriString());
		final HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		final HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		return this.restTemplate.exchange(uri.toUriString(), HttpMethod.GET, requestEntity, responseType);
	}
	// ObjectMapper mapper = new ObjectMapper(); - mapper.writeValueAsString(emp)
	protected <T> ResponseEntity<T> postRest(final Class<T> responseType, final UriComponents uri, final Object body) {
		System.out.println("POST: "+uri.toUriString());
		final HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		final HttpEntity<?> requestEntity = new HttpEntity<>(body, headers);
		return this.restTemplate.exchange(uri.toUriString(), HttpMethod.POST, requestEntity, responseType);
	}
}
