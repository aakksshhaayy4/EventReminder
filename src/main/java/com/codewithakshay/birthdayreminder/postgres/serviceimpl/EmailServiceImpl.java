package com.codewithakshay.birthdayreminder.postgres.serviceimpl;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.codewithakshay.birthdayreminder.postgres.model.Email;
import com.codewithakshay.birthdayreminder.postgres.model.EmailTemplates;
import com.codewithakshay.birthdayreminder.postgres.repository.EmailTemplateRepository;
import com.codewithakshay.birthdayreminder.postgres.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Value("${spring.mail.username}")
	public String fromMail;

	@Value("${employee.name}")
	public static String name;

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private EmailTemplateRepository emailTemplateRepository;

	@Override
	public String sendEMail(Email email) throws MessagingException {
		String message = null;
//		Optional<EmailTemplates> emailTemplate = emailTemplateRepository.findById(1L);
		if (email.getAttachment() == null)
			message = sendSimpleMail(email);
		else {
			message = sendMessageWithAttachment(email);
		}
		return message;
	}

	public String sendSimpleMail(Email email) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(fromMail);
		helper.setSubject(email.getSubject());
		helper.setTo(email.getToEmail());
		helper.setText(email.getBody().replace("${name}", email.getName()));
		emailSender.send(message);
		return "Email Sent Successfully";
	}

	public String sendMessageWithAttachment(Email email) throws MessagingException {
		FileSystemResource file = null;
		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(fromMail);
		helper.setTo(email.getToEmail());
		helper.setSubject(email.getSubject());
		helper.setText(email.getBody());

		file = new FileSystemResource(new File(email.getAttachment()));
		helper.addAttachment("Invoice", file);

		emailSender.send(message);
		return "Email Sent Successfully";
	}

	public Email setEmailDetails(String name, String toEmail, String attachment, String body, String subject) {
		Email email = new Email();
		email.setName(name);
		email.setAttachment(attachment);
		email.setToEmail(toEmail);
		email.setBody(body);
		email.setSubject(subject);
		return email;
	}

}
