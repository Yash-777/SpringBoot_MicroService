package com.github.yash777.Qualification.repsitoryIT;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.yash777.Qualification.entity.Education;
import com.github.yash777.Qualification.repository.EducaitonRepo;

@ExtendWith(SpringExtension.class) @ActiveProfiles("test")
@DataJpaTest
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class
})
@TestPropertySource(
		locations = "classpath:db-test.properties"
		,properties = { "spring.jpa.hibernate.ddl-auto=create", "org.hibernate.cfg.Environment:classpath:db-test.properties" }
		// DEBUG org.hibernate.cfg.Environment - HHH000206: hibernate.properties not found
		)
public class EducationRepoIT {
	
	static SessionFactory getSessionFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		.configure() // configures settings from hibernate.cfg.xml
		.build();
		
		SessionFactory factory = new MetadataSources(registry)
				.buildMetadata().buildSessionFactory();
		
		return factory;
	}
	
	private static SessionFactory sessionFactory;
    private Session session;
     
    @BeforeAll
    public static void setup() {
        sessionFactory = getSessionFactory();
        System.out.println("SessionFactory created");
    }
     
    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }
    
	//@MockBean org.hibernate.SessionFactory factory;
	@InjectMocks public EducaitonRepo repo;
	
//	//@Autowired DataSource dataSource;
//	//@Value("classpath:userDataSetup_jpa.sql")  protected org.springframework.core.io.Resource UserScript;
//	@PostConstruct
//	public void setup() throws SQLException {
//		// Using advanced try to catch to avoid connection leak, to close connection at the end.
//		try (Connection connection = dataSource.getConnection()) {
//			//ScriptUtils.executeSqlScript(connection, UserScript);
//		}
//	}
	
	@org.junit.jupiter.api.Test
	@Sql("classpath:eucationData.sql")
	public void createValidUser() {
		Education education = Education.builder()
				.userUniqueId("GIT777")
				.qualificaiton("PG")
				.build();
		repo.save(education);
	
		Education edu = repo.getUserEducaitonDetails("GIT777");
		assertEquals(edu.getQualificaiton(), education.getQualificaiton());
	}
}