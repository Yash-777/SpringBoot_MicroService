package com.github.yash777.Qualification.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// @Accessors(chain = true)
@Entity
@Data @Builder @AllArgsConstructor
@SequenceGenerator(name = "qualificaiton_seq", sequenceName = "qualificaiton_seq",allocationSize = 1)
public class Education {
		
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qualificaiton_seq")
	public Integer eid;

	@Column(name="user_unique_id", nullable = false)
	private String userUniqueId;
	
	// SSC(10th)/HSC(12th)/UG/PG/Any Degree
	private String qualificaiton;
	private String specialization;
	private String school_college;
	private Date year_of_pass;
	
//	@Column(name = "created_date", updatable = false)
//	private Timestamp createdDate;
//
//	@Column(name = "updated_date")
//	private Timestamp updatedDate;

//	@Column(name = "updated_by", nullable = true, insertable = true, updatable = true)
//	@LastModifiedBy
//	private String updatedBy;
}
