package com.github.yash777.User.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.yash777.User.entity.UserTable;
import com.github.yash777.User.repository.h2.UserRepository;

// https://reflectoring.io/spring-boot-data-jpa-test/

@RunWith(SpringRunner.class) //or @ExtendWith(SpringExtension.class)
@DataJpaTest
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class
})
@TestPropertySource(
		locations = "classpath:db-test.properties"
		,properties = {
			//"spring.jpa.hibernate.ddl-auto=validate" // org.hibernate.tool.schema.spi.SchemaManagementException: Schema-validation: missing table
			"spring.jpa.hibernate.ddl-auto=create"
				}
		)
//@DataJpaTest(properties = {
//		  "spring.test.database.replace=NONE",
//		  "spring.datasource.url=jdbc:tc:postgresql:12:///springboot"
//		})
public class UserRepository_DataJpaTest {

	@Autowired
	private UserRepository userRepo;

	@Autowired DataSource dataSource;
	@Value("classpath:userDataSetup_jpa.sql")  protected org.springframework.core.io.Resource uerScript;
	//@Value("classpath:accountDataSetup_hibernate.sql")  protected org.springframework.core.io.Resource accountScriopt;
	@PostConstruct
	public void setup() throws SQLException {
		// Using advanced try to catch to avoid connection leak, to close connection at the end.
		try (Connection connection = dataSource.getConnection()) {
			ScriptUtils.executeSqlScript(connection, uerScript);
			//ScriptUtils.executeSqlScript(connection, accountScriopt);
		}
	}
	
	@org.junit.jupiter.api.Test
	//@Sql("userDataSetup_jpa.sql")
	public void createValidUser() {
		//insert into user_presonal_details (first_name, last_name, uniqueId) values ('Yash', 'M', 'Test_777');
		UserTable validUser = UserTable.builder().firstName("Yash").build();
		//userRepo.save(validUser);
	
		List<UserTable> findAll = userRepo.findAll();
		System.out.println("Find All:"+ findAll);
		assertEquals(validUser.getFirstName(), findAll.get(0).getFirstName()); // assertThat(user).isNotNull();
	}

}