package com.codewithakshay.birthdayreminder.postgres.service;

import java.util.List;
import java.util.Optional;

import com.codewithakshay.birthdayreminder.postgres.model.EmailTemplates;

public interface EmailTemplateService {

	public Optional<EmailTemplates> searchTemplates(EmailTemplates emailTemplates);

	public EmailTemplates saveOrUpdateTemplate(EmailTemplates emailTemplates);

}
