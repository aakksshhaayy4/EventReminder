package com.codewithakshay.birthdayreminder.postgres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithakshay.birthdayreminder.postgres.model.Login;
import com.codewithakshay.birthdayreminder.postgres.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping(value = "/user")
	public ResponseEntity<Object> loginUser(@RequestBody Login login) {
		try {
			String loginStatus = loginService.loginUser(login);
			if (loginStatus != null)
				return new ResponseEntity<>(loginStatus, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception er) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
