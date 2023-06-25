package com.codewithakshay.birthdayreminder.postgres.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "birthday", schema = "public")
public class Birthday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long birthdayId;
	@NotEmpty(message = "please enter name of person, whose details you've to save")
	private String name;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id")
	private Contact contact;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	@NotEmpty(message = "please enter your relationship with this person")
	private String relation;
	@NotEmpty(message = "please select appropriate gender")
	private String gender;
//	@NotEmpty(message = "select date of birth")
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private String date;
	private Long userId;
	@UpdateTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;

}
