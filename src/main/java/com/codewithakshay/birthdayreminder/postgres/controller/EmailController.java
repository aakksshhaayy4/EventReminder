package com.codewithakshay.birthdayreminder.postgres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithakshay.birthdayreminder.postgres.model.Email;
import com.codewithakshay.birthdayreminder.postgres.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> sendEmail(@RequestBody Email email, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors())
				return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
			String emailData = emailService.sendEMail(email);
			if (emailData != null)
				return new ResponseEntity<>(emailData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception er) {
			return new ResponseEntity<>(er.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
