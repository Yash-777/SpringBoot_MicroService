package com.github.yash777.User.entity;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@javax.persistence.Entity
@Data //@EqualsAndHashCode(callSuper=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_presonal_details")
// https://www.onlinetutorialspoint.com/spring-boot/spring-boot-h2-database-jdbc-example.html
public class UserTable /* extends AbstractAuditEntity */{ // implements org.springframework.security.core.userdetails.UserDetails 
// Change Class name User to UserTable to avoid below exception. - https://stackoverflow.com/a/61596969/5081877
// org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL "drop table if exists user CASCADE " via JDBC Statement
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Syntax error in SQL statement "create table [*]user (id integer not null, first_name varchar(255), last_name varchar(255), primary key (id))"; expected "identifier"; SQL statement:
	
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@Column(name="last_name", nullable = true)
	private String lastName;
	
	private String uniqueId; // pan or adhar or voter id
}
