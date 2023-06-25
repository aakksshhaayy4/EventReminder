package com.codewithakshay.birthdayreminder.postgres.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithakshay.birthdayreminder.postgres.model.EmailTemplates;
import com.codewithakshay.birthdayreminder.postgres.repository.EmailTemplateRepository;
import com.codewithakshay.birthdayreminder.postgres.service.EmailTemplateService;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

	@Autowired
	private EmailTemplateRepository emailTemplateRepository;

	@Override
	public Optional<EmailTemplates> searchTemplates(EmailTemplates emailTemplates) {
		Optional<EmailTemplates> templateData = null;
		if (Long.valueOf(emailTemplates.getEmailTempleId()) != null)
			templateData = emailTemplateRepository.findById(emailTemplates.getEmailTempleId());
		return templateData;
	}

	@Override
	public EmailTemplates saveOrUpdateTemplate(EmailTemplates emailTemplates) {
		List<EmailTemplates> tempalateData = emailTemplateRepository.findByUserId(emailTemplates.getUserId());
		emailTemplates.setEmailTempleId(tempalateData.get(0).getEmailTempleId());

		return emailTemplateRepository.save(emailTemplates);
	}

}
