package com.codewithakshay.birthdayreminder.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BirthdayReminder1Application {

	public static void main(String[] args) {
		try {
			SpringApplication.run(BirthdayReminder1Application.class, args);
		} catch (Exception er) {
			er.printStackTrace();
		}
	}

}
