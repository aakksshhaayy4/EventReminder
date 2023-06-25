package com.codewithakshay.birthdayreminder.postgres.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithakshay.birthdayreminder.postgres.model.SMS;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@RequestMapping("/sms")
public class SMSController {

	@PostMapping(value = "/send")
	public ResponseEntity<String> sendSMS(@RequestBody SMS sms) {
		try {
			Twilio.init(System.getenv("AC6a4a5b120bc21886d5ddd9e0db6d4872"),
					System.getenv("159c76b32f8fc7169b0a4621e3dd37ba"));

			Message.creator(new PhoneNumber(sms.getToPhone()), new PhoneNumber("+12708174050"), "Hello from Twilio 📞")
					.create();

			return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
		} catch (Exception er) {
			return new ResponseEntity<>(er.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
