package com.github.yash777.User.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//Project Lombok : The Boilerplate Code Extractor
//@Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED)
@MappedSuperclass @EntityListeners(AuditingEntityListener.class)
@Data
// https://www.baeldung.com/database-auditing-jpa
// https://www.programmingmitra.com/2017/02/automatic-spring-data-jpa-auditing-saving-CreatedBy-createddate-lastmodifiedby-lastmodifieddate-automatically.html
public abstract class AbstractAuditEntity implements Serializable {

	private static final long serialVersionUID = 7787725460840663583L;

	// Spring Data Annotations @CreatedBy, @CreatedDate, @LastModifiedBy and @LastModifiedDate
	
	@Temporal(TemporalType.TIMESTAMP) @DateTimeFormat(pattern = "YYYY-MM-DD") // HH:mm:ss
	@Column(name = "created", nullable = true, insertable = true, updatable = false)
	@CreatedDate
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = true, insertable = false, updatable = true)
	@LastModifiedDate
	private Date updated;

	@Column(name = "created_by", nullable = true, insertable = true, updatable = false)
	@CreatedBy
	private String createdBy;

	@Column(name = "updated_by", nullable = true, insertable = true, updatable = true)
	@LastModifiedBy
	private String updatedBy;

	// @PrePersist, @PreUpdate and @PreRemove
	@PrePersist
	protected void onCreate() {
		updated = created = new Date();
		createdBy = getUsernameOfLoggedinUser();
	}
	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
		String updatedUser = getUsernameOfLoggedinUser();
		if (!"anonymousUser".equalsIgnoreCase(updatedUser))
			updatedBy = getUsernameOfLoggedinUser();
	}

	//import org.springframework.security.core.Authentication;
	//import org.springframework.security.core.context.SecurityContextHolder;
	private String getUsernameOfLoggedinUser() {
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		return authentication.getName();*/
		return "admin";
	}
}
