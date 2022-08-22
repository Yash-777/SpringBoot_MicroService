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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.yash777.User.ConfigH2DataSourceJPA;
import com.github.yash777.User.entity.UserTable;
import com.github.yash777.User.repository.h2.UserRepository;

@RunWith(SpringRunner.class) @SpringBootTest @ActiveProfiles("test") // @ExtendWith({SpringExtension.class})//, H2Extension.class})
//@ContextConfiguration(
//		classes = { ConfigH2DataSourceJPA.class, UserRepository.class, UserTable.class}
//		, loader = AnnotationConfigContextLoader.class
//)
@TestPropertySource(
		locations = "classpath:db-test.properties"
		,properties = { "spring.jpa.hibernate.ddl-auto=create"} //, "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect" }
		)
public class UserRepository_Test {
	
	@Autowired public UserRepository userRepo;
	
	// @Qualifier("h2DataSourceJPA") - 'javax.sql.DataSource' available: expected single matching bean but found 2: h2DataSourceJDBC,h2DataSourceJPA
	@Autowired DataSource dataSource;
	@Value("classpath:userDataSetup_jpa.sql") protected org.springframework.core.io.Resource userScript;
	@PostConstruct
	public void setup() throws SQLException {
		// Using advanced try to catch to avoid connection leak, to close connection at the end.
		try (Connection connection = dataSource.getConnection()) {
			ScriptUtils.executeSqlScript(connection, userScript);
		}
	}
	
	@org.junit.jupiter.api.Test
	//@Sql("classpath:userDataSetup_jpa.sql")
	public void createValidUser() {
		//insert into user_presonal_details (first_name, last_name, uniqueId) values ('Yash', 'M', 'Test_777');
		UserTable validUser = UserTable.builder().firstName("Yash").build();
		userRepo.save(validUser);
	
		List<UserTable> findAll = userRepo.findAll();
		System.out.println("Find All:"+ findAll);
		assertEquals(validUser.getFirstName(), findAll.get(0).getFirstName()); // assertThat(user).isNotNull();
	}
}
