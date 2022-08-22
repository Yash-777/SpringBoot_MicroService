package com.github.yash777.User.controller.integration;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import com.github.yash777.User.controller.AddressController;
import com.github.yash777.User.entity.AddressTableEntity;

public class AddressControllerIT_JDBC extends AbstractIntegration_junit4 {

	//@MockBean public AddressRepository repo;// It will create object for MockClass - @Profile("test")
	//@MockBean public AddressService service;
	@InjectMocks
	public AddressController controller;

	private UriComponents uriComponents;
	@PostConstruct
	public void setup() { // Jetty started on port(s) 58741 (http/1.1) with context path '/'
		setupEnvironment();
		uriComponents = uriComponentsBuilder.path("/address/init").build();
	}
	
//	@Autowired public DataSource dataSource;
//	@PostConstruct @SneakyThrows
//	public void setupJPA() {
//		setupEnvironment();
//		uriComponents = uriComponentsBuilder.path("/address/init").build();
//		// Using advanced try to catch to avoid connection leak, to close connection at the end.
//		try (Connection connection = dataSource.getConnection()) {
//			ScriptUtils.executeSqlScript(connection, sqlFileDbSetupScript);
//		}
//	}
	
	@org.junit.jupiter.api.Test
	public void shouldReturnDefaultMessageRest() throws Exception {
		// Invoke
		AddressTableEntity entityExpected = AddressTableEntity.builder().userUniqueId("GITHUB-777").present(1)
				.country("India").state("TS").city("Hyd")
				.build();
		final ResponseEntity<AddressTableEntity> response = getRest(AddressTableEntity.class, uriComponents);
		System.out.println("Response:"+response);
		org.junit.Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		org.junit.Assert.assertEquals(entityExpected.getUserUniqueId(), response.getBody().getUserUniqueId());
	}
	
	//@Test
	public void testGetEmployeeListSuccess() throws URISyntaxException 
	{
		System.out.println("GET: "+uriComponents.toUriString());
		System.out.println("Rest Temp:"+ restTemplate);
	    ResponseEntity<String> result = restTemplate.getForEntity(uriComponents.toUri(), String.class);
	     System.out.println("Response: "+result.getBody());
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody().contains("GIT"));
	}
	
	
	//@Test // https://github.com/ssouris/spring-tutorials/blob/master/rest-template/src/test/java/com/yetanotherdevblog/RestTemplateTest.java
    public void test_GET() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(uriComponents.toUriString(), String.class);
        ResponseEntity<String> responseExchange = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, null, String.class);

        URI uri = new URI(uriComponents.toUriString());
        ResponseEntity<String> responseURI = restTemplate.getForEntity(uri, String.class);
        ResponseEntity<String> responseExchangeURI = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

        System.out.println(response);
        System.out.println(responseExchange);

        System.out.println(responseURI);
        System.out.println(responseExchangeURI);
    }
	
//	@org.junit.Test // JUnit4(Spring Boot  - 2.5.13, Spring framework - 5.3.19)  - org.junit.Test , Junit5 - org.junit.jupiter.api.Test  
//	  public void testMpsAARByPolicyNo() {
//	    // Invoke
//	    final ResponseEntity<AssetRebalanceDTO> response = post(AssetRebalanceDTO.class, uriComponents, requestBody);
//	    // Assert expected result
//	    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
//	    Assert.assertEquals(expectedResult_Java, response.getBody()_SQLFile);
//	  }
}
