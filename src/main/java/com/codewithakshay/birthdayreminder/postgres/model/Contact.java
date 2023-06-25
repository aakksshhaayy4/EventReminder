package com.codewithakshay.birthdayreminder.postgres.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "contact", schema = "public")
@Data
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contact_id")
	private Long contactId;
	@NotNull(message = "please enter contact number")
	@Column(name = "contact_number")
	private String contactNumber;
	@NotEmpty(message = "please enter email id")
	@Column(name = "email_id")
	private String emailId;
	@UpdateTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;

}
