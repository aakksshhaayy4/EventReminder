package com.codewithakshay.birthdayreminder.postgres.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "address", schema = "public")
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long addressId;
	@NotEmpty(message = "please enter city name")
	private String city;
	@NotEmpty(message = "please enter state name")
	private String state;
	@NotEmpty(message = "please enter country name")
	private String country;
	@NotEmpty(message = "please enter district")
	private String district;
	@NotEmpty(message = "please enter taluka")
	private String taluka;
	@NotEmpty(message = "please enter pincode")
	private String pincode;
	@UpdateTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;

}
