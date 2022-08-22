package com.github.yash777.User.controller.integration;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.util.UriComponents;

import com.github.yash777.User.controller.UserController;
import com.github.yash777.User.entity.UserTable;

public class UserControllerTestIT extends AbstractIntegration_junit4 {
	
	
//	@MockBean public UserRepository repo;
//	@MockBean public UserService service;
	@InjectMocks public UserController controller;

	//private UriComponents uriComponents;
	@PostConstruct
	public void setup() throws SQLException { // Jetty started on port(s) 58741 (http/1.1) with context path '/'
		setupEnvironment();
		//uriComponents = uriComponentsBuilder.path("/user/init").build();
		
		// Using advanced try to catch to avoid connection leak, to close connection at the end.
		try (Connection connection = dataSource.getConnection()) {
			ScriptUtils.executeSqlScript(connection, UserScript);
		}
	}
	
	//@org.junit.jupiter.api.Test
	public void returnInitResponse() throws Exception {
		UriComponents uriComponents = uriComponents = uriComponentsBuilder.path("/user/init").build();
		final ResponseEntity<UserTable> response = getRest(UserTable.class, uriComponents);
		System.out.println("Response:"+response);
		
		System.out.println(response);
	}
	
	@org.junit.jupiter.api.Test
	public void returnListResponse() throws Exception {
		UriComponents uriComponents = uriComponents = uriComponentsBuilder.path("/user/list").build();
		final ResponseEntity<UserTable> response = getRest(UserTable.class, uriComponents);
		System.out.println("Response:"+response);
		
		System.out.println(response);
	}
	
//org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'com.github.yash777.User.controller.integration.UserControllerTestIT.ORIGINAL': Invocation of init method failed; 
	//nested exception is org.springframework.jdbc.datasource.init.ScriptStatementFailedException: Failed to execute SQL script statement #3 of class path resource [userDataSetup_jpa.sql]: insert into user_presonal_details (id, first_name, last_name, uniqueId) values (1, "Yash", "M", "Test_777"); nested exception is org.h2.jdbc.JdbcSQLSyntaxErrorException: Column "Yash" not found; SQL statement:
//insert into user_presonal_details (id, first_name, last_name, uniqueId) values (1, "Yash", "M", "Test_777") [42122-214]

}
