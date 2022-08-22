package com.github.yash777.User;

import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
//@EnableAutoConfiguration
//@PropertySource("classpath:application-h2db.properties")
//@ComponentScan("com.github.yash777.User.repository.h2")

//@EntityScan("sample.data.jpa.domain")
//@EnableJpaRepositories("sample.data.jpa.service")

@Profile("!test")
@Configuration
@EntityScan(basePackages = "com.github.yash777.User.entity")
@EnableJpaRepositories(
  basePackages = {"com.github.yash777.User.repository.h2"},
  //includeFilters = @ComponentScan.Filter(UserRepository.class), // https://stackoverflow.com/a/39660348/5081877
  entityManagerFactoryRef = "h2EntityManagerFactory",
  transactionManagerRef = "h2TransactionManager"
  )
//Field factory in com.github.yash777.User.repository.h2.AccountsRepository required a single bean, but 2 were found:
//	- sessionFactory: defined by method 'sessionFactory' in class path resource [com/github/yash777/User/ConfigH2DataSourceHibernate.class]
//	- h2EntityManagerFactory: defined by method 'h2EntityManagerFactory' in class path resource [com/github/yash777/User/ConfigH2DataSourceJPA.class]
// changed jpa file to jpa folder - basePackages = {"com.github.yash777.User.repository.h2.jpa"}

// ### https://stackoverflow.com/questions/5640778/hibernate-sessionfactory-vs-jpa-entitymanagerfactory
@EnableTransactionManagement
@PropertySource("classpath:application-h2db.properties")
/*
Positive matches:
-----------------

   HibernateJpaAutoConfiguration matched:
      - @ConditionalOnClass found required classes 'org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean', 'javax.persistence.EntityManager', 'org.hibernate.engine.spi.SessionImplementor' (OnClassCondition)

   JpaBaseConfiguration.JpaWebConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.web.servlet.config.annotation.WebMvcConfigurer' (OnClassCondition)
      - found 'session' scope (OnWebApplicationCondition)
      - @ConditionalOnProperty (spring.jpa.open-in-view=true) matched (OnPropertyCondition)


Negative matches:
-----------------

   HibernateJpaConfiguration:
      Did not match:
         - @ConditionalOnSingleCandidate (types: javax.sql.DataSource; SearchStrategy: all) did not find a primary bean from beans 'h2DataSourceJPA', 'h2DataSourceJDBC' (OnBeanCondition)
 */
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
public class ConfigH2DataSourceJPA {
	
	@Value("${spring.datasource.url}") private String dbURL;
	@Value("${spring.datasource.username}") private String dbUser;
	@Value("${spring.datasource.secret}") private String dbPassword;
	
	/*
	DataSource configuration is controlled by external configuration properties in spring.datasource.
	For example, you might declare the following section in application.properties:

	Properties
	spring.h2.datasource.url=jdbc:mysql://localhost/test
	spring.h2.datasource.username=dbuser
	spring.h2.datasource.password=dbpass
	
	@Bean  @ConfigurationProperties(prefix = "spring.h2.datasource")
	public DataSourceProperties h2DataSourceProperties() {
		return new DataSourceProperties();
	}
	@Bean
	public DataSource h2DataSource() {
		DataSourceProperties h2DataSourceProperties = h2DataSourceProperties();
		return DataSourceBuilder.create()
			.driverClassName(h2DataSourceProperties.getDriverClassName())
			.url(h2DataSourceProperties.getUrl())
			.username(h2DataSourceProperties.getUsername())
			.password(h2DataSourceProperties.getPassword())
			.build();
	}
	*/
	@Bean
	public DataSource h2DataSourceJPA() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(dbURL);
		ds.setUsername(dbUser);
		ds.setPassword(dbPassword);
		return ds;
	}
	
	@Value("${entitymanager.packagesToScan}") private String packagesToScan;
	@Value("${hibernate.dialect:}") private String dialect;
	@Value("${hibernate.hbm2ddl.auto:update}") private String hbm2ddl;
	
	// JPA
	@Bean
	public PlatformTransactionManager h2TransactionManager() {
		return new JpaTransactionManager(h2EntityManagerFactory().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(h2DataSourceJPA());
		factory.setPackagesToScan( packagesToScan );
	
		final Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", dialect);
		jpaProperties.put("hibernate.hbm2ddl.auto", hbm2ddl); // To create
		factory.setJpaProperties(jpaProperties);
	
		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factory.setJpaVendorAdapter(vendorAdapter);// .setPersistenceProviderClass(HibernatePersistence.class);
		
		final HashMap<String, Object> properties = new HashMap<>(1);
		properties.put("hibernate.dialect", dialect);
		factory.setJpaPropertyMap(properties);
		return factory;
	}
	
	// Hibernate - Session factory
	/*
	hibernate.dialect=org.hibernate.dialect.MySQL5Dialet
	hibernate.show_sql=true
	hibernate.hbm2ddl.auto=true
	entitymanager.packagesToScan=com.github.yash777.User.entity
	 */
//	@Value("${hibernate.show_sql}") private String show_sql;
//	@Value("${hibernate.hbm2ddl.auto}") private String hbm2ddl_auto;
//	
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFacotry = new LocalSessionFactoryBean();
//		sessionFacotry.setDataSource(h2DataSource());
//		sessionFacotry.setPackagesToScan(packagesToScan);
//		Properties props = new Properties();
//		props.put("hibernate.dialect", dialect);
//		props.put("hibernate.show_sql", show_sql);
//		props.put("hibernate.hbm2ddl.auto", hbm2ddl_auto);
//		
//		sessionFacotry.setHibernateProperties(props);
//		return sessionFacotry;
//	}
//	@Bean
//	public HibernateTransactionManager transactionManager() {
//		HibernateTransactionManager transaction = new HibernateTransactionManager();
//		transaction.setSessionFactory( sessionFactory().getObject() );
//		return transaction;
//	}
}
