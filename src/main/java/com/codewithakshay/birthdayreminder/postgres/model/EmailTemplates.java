package com.codewithakshay.birthdayreminder.postgres.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EmailTemplates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "email_template_id")
	private long emailTempleId;
	private String subject;
	private String message;
	private Long userId;

}
