package com.codewithakshay.birthdayreminder.postgres.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithakshay.birthdayreminder.postgres.model.Birthday;
import com.codewithakshay.birthdayreminder.postgres.model.EmailTemplates;
import com.codewithakshay.birthdayreminder.postgres.model.ValidList;
import com.codewithakshay.birthdayreminder.postgres.repository.EmailTemplateRepository;
import com.codewithakshay.birthdayreminder.postgres.service.EmailTemplateService;

@RestController
@RequestMapping("/emailtemplate")
@CrossOrigin(origins = "*")
public class EmailTemplateController {

	@Autowired
	private EmailTemplateRepository emailTemplateRepository;

	@Autowired
	private EmailTemplateService emailTemplateService;

	@PostMapping(value = "/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateEmailTemplate(@Valid @RequestBody EmailTemplates emailTemplates,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<ObjectError> allErrors = bindingResult.getAllErrors();
				return new ResponseEntity<>(allErrors, HttpStatus.BAD_REQUEST);
			}
			EmailTemplates savedData = emailTemplateRepository.save(emailTemplates);
			if (savedData != null)
				return new ResponseEntity<>(savedData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception er) {
			return new ResponseEntity<>(er.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list")
	public ResponseEntity<Object> getAllTemplates() {
		try {
			List<EmailTemplates> emailTemplateData = emailTemplateRepository.findAll();
			if (!emailTemplateData.isEmpty())
				return new ResponseEntity<>(emailTemplateData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception er) {
			return new ResponseEntity<>(er.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/list/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateEmailTemplateList(
			@Valid @RequestBody ValidList<EmailTemplates> emailTemplates, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<ObjectError> allErrors = bindingResult.getAllErrors();
				return new ResponseEntity<>(allErrors, HttpStatus.BAD_REQUEST);
			}
			List<EmailTemplates> savedData = emailTemplateRepository.saveAll(emailTemplates.getList());
			if (!savedData.isEmpty())
				return new ResponseEntity<>(savedData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception er) {
			return new ResponseEntity<>(er.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/saveorupdateforuser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateEmailTemplateById(@RequestBody EmailTemplates emailTemplate,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<ObjectError> allErrors = bindingResult.getAllErrors();
				return new ResponseEntity<>(allErrors, HttpStatus.BAD_REQUEST);
			}
			EmailTemplates templateData = emailTemplateService.saveOrUpdateTemplate(emailTemplate);
			if (templateData != null)
				return new ResponseEntity<>(templateData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception er) {
			return new ResponseEntity<>(er.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> searchTemplates(@RequestBody EmailTemplates emailTemplates,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors())
				return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
			Optional<EmailTemplates> searchData = emailTemplateService.searchTemplates(emailTemplates);
			if (!searchData.isEmpty())
				return new ResponseEntity<>(searchData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception er) {
			return new ResponseEntity<>(er.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getbyid/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getTemplateByUserId(@PathVariable Long userid) {
		try {
			List<EmailTemplates> templatesUnderUser = emailTemplateRepository.findByUserId(userid);
			if (!templatesUnderUser.isEmpty())
				return new ResponseEntity<>(templatesUnderUser, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception er) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
