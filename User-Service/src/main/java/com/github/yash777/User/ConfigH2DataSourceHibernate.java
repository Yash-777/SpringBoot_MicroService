package com.github.yash777.User;

import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.yash777.User.repository.h2.AccountsRepository;
import com.github.yash777.User.repository.h2.AddressRepositoryImpl;

//@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
//@EnableAutoConfiguration
//@PropertySource("classpath:application-h2db.properties")
//@ComponentScan("com.github.yash777.User.repository.h2")

//@EntityScan("sample.data.jpa.domain")
//@EnableJpaRepositories("sample.data.jpa.service")

/*
Field factory in com.github.yash777.User.repository.h2.AccountsRepository required a single bean, but 2 were found:
	- sessionFactory: defined by method 'sessionFactory' in class path resource [com/github/yash777/User/ConfigH2DataSourceHibernate.class]
	- h2EntityManagerFactory: defined by method 'h2EntityManagerFactory' in class path resource [com/github/yash777/User/ConfigH2DataSourceJPA.class]
 */
@Profile("!test")
//@Configuration
@EntityScan(basePackages = "com.github.yash777.User.entity")
@EnableTransactionManagement
@ComponentScan(
	    basePackageClasses = {AccountsRepository.class}, 
	    useDefaultFilters = false
	    )
@PropertySource("classpath:application-h2db.properties")
public class ConfigH2DataSourceHibernate {
	
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
	public DataSource h2DataSourceHibernate() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(dbURL);
		ds.setUsername(dbUser);
		ds.setPassword(dbPassword);
		return ds;
	}
	
	@Value("${entitymanager.packagesToScan}") private String packagesToScan;
	@Value("${hibernate.dialect}") private String dialect;
	
	// Hibernate - Session factory
	/*
	hibernate.dialect=org.hibernate.dialect.MySQL5Dialet
	hibernate.show_sql=true
	hibernate.hbm2ddl.auto=true
	entitymanager.packagesToScan=com.github.yash777.User.entity
	 */
	@Value("${hibernate.show_sql}") private String show_sql;
	@Value("${hibernate.hbm2ddl.auto:update}") private String hbm2ddl_auto;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFacotry = new LocalSessionFactoryBean();
		sessionFacotry.setDataSource(h2DataSourceHibernate());
		sessionFacotry.setPackagesToScan(packagesToScan);
		Properties props = new Properties();
		props.put("hibernate.dialect", dialect);
		props.put("hibernate.show_sql", show_sql);
		props.put("hibernate.hbm2ddl.auto", hbm2ddl_auto);
		
		sessionFacotry.setHibernateProperties(props);
		return sessionFacotry;
	}
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transaction = new HibernateTransactionManager();
		transaction.setSessionFactory( sessionFactory().getObject() );
		return transaction;
	}
}
