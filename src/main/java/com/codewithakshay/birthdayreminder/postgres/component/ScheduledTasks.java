package com.codewithakshay.birthdayreminder.postgres.component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.codewithakshay.birthdayreminder.postgres.model.Birthday;
import com.codewithakshay.birthdayreminder.postgres.model.Email;
import com.codewithakshay.birthdayreminder.postgres.model.EmailTemplates;
import com.codewithakshay.birthdayreminder.postgres.model.UserManagement;
import com.codewithakshay.birthdayreminder.postgres.repository.EmailTemplateRepository;
import com.codewithakshay.birthdayreminder.postgres.repository.UserManagementRepository;
import com.codewithakshay.birthdayreminder.postgres.service.BirthdayService;
import com.codewithakshay.birthdayreminder.postgres.service.EmailService;

@Component
public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	@Autowired
	private BirthdayService birthdaySerive;

	@Autowired
	private EmailService emailService;

	@Autowired
	private EmailTemplateRepository emailTemplateRepository;

	@Autowired
	private UserManagementRepository userManagementRepository;

//	@Scheduled(cron = "0 0 0 * * *")
//	@Scheduled(cron = "* * * * * *")
//	@Scheduled(cron = "0 43 12 ? * *")
	@Scheduled(cron = "0 05 15 ? * *")
	public void birthdayCheckCron() throws MessagingException {
		String message = null;
		List<Birthday> todaysBithdays = birthdaySerive.getTodaysBithdays();
		if (!todaysBithdays.isEmpty()) {
			for (Birthday birthday : todaysBithdays) {
				Optional<EmailTemplates> birthdayTemplate = emailTemplateRepository.findById(1L);
				Optional<EmailTemplates> userTemplate = emailTemplateRepository.findById(2L);
				Optional<UserManagement> userData = userManagementRepository.findById(birthday.getUserId());
				Email email = emailService.setEmailDetails(birthday.getName(), birthday.getContact().getEmailId(), null,
						birthdayTemplate.get().getMessage().replace("${friend}", birthday.getName())
								.replace("${username}", userData.get().getUserName())
								.replace("${uphone}", userData.get().getPhoneNumber())
								.replace("${uemail}", userData.get().getEmail()),
						birthdayTemplate.get().getSubject().replace("${username}", userData.get().getUserName()));
				message = emailService.sendEMail(email);
				if (message != null) {
					email = emailService.setEmailDetails(userData.get().getUserName(), userData.get().getEmail(), null,
							userTemplate.get().getMessage().replace("${username}", userData.get().getUserName())
									.replaceAll("\\$\\{friend\\}", birthday.getName())
									.replace("${femail}", birthday.getContact().getEmailId()).replace("${fphone}",
											birthday.getContact().getContactNumber()),
							userTemplate.get().getSubject());
					message = emailService.sendEMail(email);
				}
			}
		}
	}

}
