package com.github.yash777.User;

import java.util.HashMap;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.yash777.User.repository.h2.UserRepository;

//@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
//@EnableAutoConfiguration
//@PropertySource("classpath:application-h2db.properties")
//@ComponentScan("com.github.yash777.User.repository.h2")

//@EntityScan("sample.data.jpa.domain")
//@EnableJpaRepositories("sample.data.jpa.service")

@Profile("!test")
//@Configuration
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

@EnableTransactionManagement
@PropertySource("classpath:application-h2db.properties")
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
public class ConfigH2DataSourceORM {
	
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
//	@Autowired @Qualifier(value = "h2DataSourceORM")
//	public DataSource dsORM;
	@Bean
	public DataSource h2DataSourceORM() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(dbURL);
		ds.setUsername(dbUser);
		ds.setPassword(dbPassword);
		return ds;
	}
	
	@Value("${entitymanager.packagesToScan}") private String packagesToScan;
	
	// Hibernate - Properties
	/*
	hibernate.dialect=org.hibernate.dialect.MySQL5Dialet
	hibernate.show_sql=true
	hibernate.hbm2ddl.auto=true
	entitymanager.packagesToScan=com.github.yash777.User.entity
	 */
	@Value("${hibernate.show_sql}") private String show_sql;
	@Value("${hibernate.hbm2ddl.auto:}") private String hbm2ddl_auto;
	@Value("${hibernate.dialect:}") private String dialect;
	
	// JPA _Properties
	@Value("${spring.jpa.hibernate.ddl-auto:}") private String dialectJPA;
	
	// JPA
	@Bean
	public PlatformTransactionManager h2TransactionManager() {
		return new JpaTransactionManager(h2EntityManagerFactory().getObject());
	}
	
	public HashMap<String, Object> getHibernatePropertiesMap() {
		final HashMap<String, Object> properties = new HashMap<>(1);
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.hbm2ddl.auto", hbm2ddl_auto);
		return properties;
	}
	public Properties getHibernateProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", dialect);
		props.put("hibernate.show_sql", show_sql);
		props.put("hibernate.hbm2ddl.auto", hbm2ddl_auto);
		return props;
	}
	public Properties getJPAProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.put("spring.jpa.hibernate.ddl-auto", dialectJPA);
		return jpaProperties;
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource( h2DataSourceORM() ); // HibernateTransactionManager
	
		//factoryBean.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
		//factoryBean.setValidationMode(ValidationMode.CALLBACK);
		//factoryBean.setJpaDialect(new HibernateJpaDialect());
		factoryBean.setJpaProperties( getJPAProperties() ); // JpaTransactionManager
		factoryBean.setPackagesToScan( packagesToScan );
		//String packageName = UserRepository.class.getPackage().getName();
		//factoryBean.setPackagesToScan(new String[] { packageName });
		//factoryBean.setPackagesToScan(StringUtils.substring(packageName, 0, StringUtils.lastIndexOf(packageName, '.')));
		//factoryBean.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		//vendorAdapter.setGenerateDdl(Boolean.TRUE);
		//vendorAdapter.setShowSql(Boolean.TRUE);
		factoryBean.setJpaVendorAdapter( vendorAdapter ); // AbstractEntityManagerFactoryBean
		
		factoryBean.setJpaPropertyMap( getHibernatePropertiesMap() ); // AbstractEntityManagerFactoryBean
		
		return factoryBean;
	}
	
	// Hibernate - SessionFactory
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFacotry = new LocalSessionFactoryBean();
//		sessionFacotry.setDataSource( h2DataSourceORM() );
//		sessionFacotry.setPackagesToScan(packagesToScan);
//		
//		sessionFacotry.setHibernateProperties( getHibernateProperties() );
//		return sessionFacotry;
//	}
//	@Bean
//	public HibernateTransactionManager transactionManager() {
//		HibernateTransactionManager transaction = new HibernateTransactionManager();
//		transaction.setDataSource( h2DataSourceORM() );
//		transaction.setSessionFactory( sessionFactory().getObject() );
//		return transaction;
//	}
}
