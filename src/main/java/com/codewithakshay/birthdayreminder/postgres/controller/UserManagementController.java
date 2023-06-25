package com.codewithakshay.birthdayreminder.postgres.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithakshay.birthdayreminder.postgres.model.UserManagement;
import com.codewithakshay.birthdayreminder.postgres.model.ValidList;
import com.codewithakshay.birthdayreminder.postgres.repository.UserManagementRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserManagementController {

	@Autowired
	private UserManagementRepository userManagementRepository;

	@PostMapping(value = "/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateUsers(@Valid @RequestBody UserManagement userManagement,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<ObjectError> allErrors = bindingResult.getAllErrors();
				return new ResponseEntity<>(allErrors, HttpStatus.BAD_REQUEST);
			}
			UserManagement savedData = userManagementRepository.save(userManagement);
			if (savedData != null)
				return new ResponseEntity<>(savedData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception er) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllUsers() {
		try {
			List<UserManagement> findAll = userManagementRepository.findAll();
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} catch (Exception er) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
