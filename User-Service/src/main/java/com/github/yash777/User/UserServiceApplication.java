package com.github.yash777.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/* @EnableAutoConfiguration - Makes spring to guess the configuration based on classpath.
 * This annotation tells Spring Boot to “guess” how you want to configure Spring, based on the jar dependencies that you have added.
 * Since spring-boot-starter-web added Tomcat and Spring MVC, the auto-configuration assumes that you are developing a 
 * web application and sets up Spring accordingly. */
// @ComponentScan(useDefaultFilters=false)  // Disable component scanner
public class UserServiceApplication {
	// https://spring.io/blog/2015/07/14/microservices-with-spring
	public static void main(String[] args) {
		// Will configure using accounts-server.yml
		// System.setProperty("spring.config.name", "accounts-server");
		/* 
		By default Spring Boot applications look for an application.properties or application.yml file for configuration.
		By setting the spring.config.name property we can tell Spring Boot to look for a different file 
		- useful if you have multiple Spring Boot applications in the same project - as I will do shortly.
		 */
		
		//SpringApplication.run(UserServiceApplication.class, args);
		SpringApplication application = new SpringApplication(UserServiceApplication.class);
		//application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

}
