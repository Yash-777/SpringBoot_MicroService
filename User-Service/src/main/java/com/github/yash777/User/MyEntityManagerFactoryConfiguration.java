package com.github.yash777.User;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.github.yash777.User.entity.UserTable;

//@Configuration(proxyBeanMethods = false)
//@EnableJpaRepositories(basePackageClasses = UserTable.class, entityManagerFactoryRef = "h2EntityManagerFactory")
public class MyEntityManagerFactoryConfiguration {
	@Bean @Primary
	@ConfigurationProperties("spring.h2.datasource") // Default: @ConfigurationProperties(prefix = "spring.datasource")
	public DataSourceProperties h2DataSourceProperties() {
		return new DataSourceProperties();
	}
	@Bean @Primary
	@ConfigurationProperties("app.datasource.first.configuration")
	public DataSource h2DataSource(DataSourceProperties firstDataSourceProperties) {
		DataSourceProperties h2DataSourceProperties = h2DataSourceProperties();
		return DataSourceBuilder.create()
			.driverClassName(h2DataSourceProperties.getDriverClassName())
			.url(h2DataSourceProperties.getUrl())
			.username(h2DataSourceProperties.getUsername())
			.password(h2DataSourceProperties.getPassword())
			.build();
	}
	@Bean
	@ConfigurationProperties("spring.h2.jpa") // Default: @ConfigurationProperties(prefix = "spring.jpa")
	public JpaProperties h2JpaProperties() {
		return new JpaProperties();
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(DataSource h2DataSource, JpaProperties h2JpaProperties) {
		EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(h2JpaProperties);
		return builder.dataSource(h2DataSource).packages(UserTable.class).persistenceUnit("firstDs").build();
	}
	private org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(JpaProperties jpaProperties) {
		JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(jpaProperties);
		return new EntityManagerFactoryBuilder(jpaVendorAdapter, jpaProperties.getProperties(), null);
	}
	private JpaVendorAdapter createJpaVendorAdapter(JpaProperties jpaProperties) {
		// ... map JPA properties as needed
		return new HibernateJpaVendorAdapter();
	}
}
