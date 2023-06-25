package com.codewithakshay.birthdayreminder.postgres.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithakshay.birthdayreminder.postgres.model.Birthday;
import com.codewithakshay.birthdayreminder.postgres.model.UserManagement;
import com.codewithakshay.birthdayreminder.postgres.repository.BirthdayRepository;
import com.codewithakshay.birthdayreminder.postgres.repository.UserManagementRepository;

@RestController
@RequestMapping("/birthday")
@CrossOrigin(origins = "*")
public class BirthdayController {

	@Autowired
	private BirthdayRepository birthdayRepository;

	@PostMapping(value = "/saveorupdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrUpdateBirthday(@Valid @RequestBody Birthday birthday,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<ObjectError> allErrors = bindingResult.getAllErrors();
				return new ResponseEntity<>(allErrors, HttpStatus.BAD_REQUEST);
			}
			Birthday savedData = birthdayRepository.save(birthday);
			return new ResponseEntity<>(savedData, HttpStatus.OK);
		} catch (Exception er) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllBirthdays() {
		try {
			List<Birthday> birthdayData = birthdayRepository.findAll();
			if (!birthdayData.isEmpty())
				return new ResponseEntity<>(birthdayData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception er) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getbyid/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBirthdaysByUser(@PathVariable Long userid) {
		try {
			List<Birthday> birthdaysUnderUser = birthdayRepository.findByUserId(userid);
			if (!birthdaysUnderUser.isEmpty())
				return new ResponseEntity<>(birthdaysUnderUser, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception er) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getbirthday/{userid}/{birthdayid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBirthdaysByUserIdAndBirthdayId(@PathVariable Long userid,
			@PathVariable Long birthdayid) {
		try {
			List<Birthday> birthdaysUnderUser = birthdayRepository.findByUserIdAndBirthdayId(userid, birthdayid);
			if (!birthdaysUnderUser.isEmpty())
				return new ResponseEntity<>(birthdaysUnderUser, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception er) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteBirthday(@RequestBody Birthday birthday) {
		try {
			birthdayRepository.deleteByUserIdAndBirthdayId(birthday.getUserId(), birthday.getBirthdayId());
			return new ResponseEntity<>("record deleted successfully ! ", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
