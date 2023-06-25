package com.codewithakshay.birthdayreminder.postgres.service;

import java.util.List;

import com.codewithakshay.birthdayreminder.postgres.model.Birthday;

public interface BirthdayService {

	public List<Birthday> getTodaysBithdays();

}
