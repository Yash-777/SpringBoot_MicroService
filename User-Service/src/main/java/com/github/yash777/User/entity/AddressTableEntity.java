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
@Data @EqualsAndHashCode(callSuper=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="address") // , schema = "db"

public class AddressTableEntity  extends AbstractAuditEntity {

	private static final long serialVersionUID = 1L;

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="user_unique_id", nullable = false)
	private String userUniqueId;
	
	private String country;
	private String state;
	private String city;
	private String fullAddress;
	
	private int present; // 1 as Present, else 0
}
