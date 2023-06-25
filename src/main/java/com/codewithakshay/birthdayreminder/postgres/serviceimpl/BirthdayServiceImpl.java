package com.codewithakshay.birthdayreminder.postgres.serviceimpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithakshay.birthdayreminder.postgres.model.Birthday;
import com.codewithakshay.birthdayreminder.postgres.repository.BirthdayRepository;
import com.codewithakshay.birthdayreminder.postgres.service.BirthdayService;

@Service
public class BirthdayServiceImpl implements BirthdayService {

	@Autowired
	private BirthdayRepository birthdayRepository;

	@Override
	public List<Birthday> getTodaysBithdays() {
		String day = null;
		String month = null;
		LocalDate localDate = LocalDate.now();
		String date = localDate.toString().split("-")[1] + "-" + localDate.toString().split("-")[2];
		List<Birthday> birthdayData = birthdayRepository.findByDateLike("%" + date + "%");
		System.out.println("here - " + birthdayData);
		return birthdayData;
	}

}
