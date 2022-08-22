package com.github.yash777.Qualification;


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
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("!test")
@Configuration
@EntityScan(basePackages = "com.github.yash777.Qualification.entity")
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
// class org.springframework.orm.jpa.EntityManagerHolder cannot be cast to class org.springframework.orm.hibernate5.SessionHolder (org.springframework.orm.jpa.EntityManagerHolder and org.springframework.orm.hibernate5.SessionHolder are in unnamed module of loader 'app')
@PropertySource("classpath:application-h2db.properties")
public class ConfigH2DataSourceHibernate5 { // https://www.baeldung.com/hibernate-5-spring
	
	@Value("${spring.datasource.url}") private String dbURL;
	@Value("${spring.datasource.username}") private String dbUser;
	@Value("${spring.datasource.secret}") private String dbPassword;
	
	@Bean
	public DataSource h2DataSourceHibernate() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(dbURL);
		ds.setUsername(dbUser);
		ds.setPassword(dbPassword);
		return ds;
	}
	
	// Hibernate - Session factory
	/*
	hibernate.dialect=org.hibernate.dialect.MySQL5Dialet
	hibernate.show_sql=true
	hibernate.hbm2ddl.auto=create
	entitymanager.packagesToScan=com.github.yash777.Qualification.entity
	 */
	@Value("${entitymanager.packagesToScan}") private String packagesToScan;
	@Value("${hibernate.dialect:org.hibernate.dialect.H2Dialect}") private String dialect;
	@Value("${hibernate.show_sql:true}") private String show_sql;
	@Value("${hibernate.hbm2ddl.auto:create-drop}") private String hbm2ddl_auto;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFacotry = new LocalSessionFactoryBean();
		sessionFacotry.setDataSource( h2DataSourceHibernate() );
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