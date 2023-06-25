package com.codewithakshay.birthdayreminder.postgres.service;

import javax.mail.MessagingException;

import com.codewithakshay.birthdayreminder.postgres.model.Email;

public interface EmailService {

	public String sendEMail(Email email) throws MessagingException;

	public Email setEmailDetails(String name, String toEmail, String attachment, String body, String subject);

}
