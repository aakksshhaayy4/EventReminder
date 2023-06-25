package com.codewithakshay.birthdayreminder.postgres.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class Email {

	private String name;
	private String toEmail;
	private String attachment;
	private String body;
	private String subject;

}
