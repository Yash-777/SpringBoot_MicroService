package com.github.yash777.User;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.lang.Nullable;

import com.github.yash777.User.repository.h2.AddressRepositoryImpl;
import com.sun.istack.NotNull;


@Configuration
//@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
//@EnableAutoConfiguration
@PropertySource("classpath:application-h2db.properties")
@ComponentScan("com.github.yash777.User.repository.h2")
//@ComponentScan(
//	    basePackageClasses = {AddressRepositoryImpl.class}, 
//	    useDefaultFilters = false
//	    )
public class ConfigH2DataSource {
	
	@Value("${spring.datasource.url:}") private String dbURL;
	@Value("${spring.datasource.username:}") private String dbUser;
	@Value("${spring.datasource.secret:}") private String dbPassword;
	//@Value("${Key:DefaultVal}") // https://www.baeldung.com/spring-value-defaults
	/*
	DataSource configuration is controlled by external configuration properties in spring.datasource.
	For example, you might declare the following section in application.properties:

	Properties
	spring.datasource.url=jdbc:mysql://localhost/test
	spring.datasource.username=dbuser
	spring.datasource.password=dbpass
	
	spring.h2.datasource.url=jdbc:mysql://localhost/test
	spring.h2.datasource.username=dbuser
	spring.h2.datasource.password=dbpass
	*/
	
	@Bean @Profile("!test")
	@ConfigurationProperties(prefix = "spring-h2.datasource") // org.springframework.boot.context.properties
	public DataSourceProperties h2DataSourceProperties() {
		return new DataSourceProperties();
	}
	@Bean @Profile("!test")
	public DataSource h2DataSourceJDBC(@Qualifier("h2DataSourceProperties") @NotNull DataSourceProperties props) {
		System.out.println("H2 Datasource Properties:"+ props.getDriverClassName());
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(props.getDriverClassName());
		ds.setUrl(props.getUrl());
		ds.setUsername(props.getUsername());
		ds.setPassword(dbPassword); // props.getPassword()
		return ds;
	}
	@Bean @Profile("!test")
	public DataSource h22DataSourceJDBC() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(dbURL);
		ds.setUsername(dbUser);
		ds.setPassword(dbPassword);
		return ds;
	}
	@Bean @Profile("!test")
	public JdbcTemplate jdbcTemplate(@Qualifier("h2DataSourceJDBC") DataSource h2DataSource) {
		return new JdbcTemplate(h2DataSource);
	}
	@Bean @Profile("test")
	public JdbcTemplate jdbcTemplateTest(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "h2NamedJdbcTemplate") @Profile("!test")
	public NamedParameterJdbcTemplate h2NamedParameterJdbcTemplate(@Qualifier("h2DataSourceJDBC") DataSource h2DataSource) {
		return new NamedParameterJdbcTemplate(h2DataSource);
	}

	//NamedParameterJdbcTemplate for H2 Test database.
	@Bean(name = "h2NamedJdbcTemplate") @Profile("test")
	public NamedParameterJdbcTemplate h2TestNamedParameterJdbcTemplate(DataSource h2DataSourceJDBC) {
		return new NamedParameterJdbcTemplate(h2DataSourceJDBC);
	}
}