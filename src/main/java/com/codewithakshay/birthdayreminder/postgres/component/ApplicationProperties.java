package com.codewithakshay.birthdayreminder.postgres.component;

import org.springframework.beans.factory.annotation.Value;

public class ApplicationProperties {

	@Value("${spring.mail.username}")
	public String fromEmail;

}
