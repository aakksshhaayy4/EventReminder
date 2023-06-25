package com.codewithakshay.birthdayreminder.postgres.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(schema = "public", name = "user_management")
public class UserManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;
	@NotBlank(message = "username should not be blank")
	@Column(name = "user_name")
	private String userName;
	@NotEmpty(message = "password should not be empty")
	private String password;
	@NotNull(message = "phone number should not be empty")
	@Column(name = "phone_number")
	private String phoneNumber;
	private String email;
	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;

}
