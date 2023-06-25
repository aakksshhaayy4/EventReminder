package com.codewithakshay.birthdayreminder.postgres.model;

import lombok.Data;

@Data
public class SMS {

	private String toPhone;
	private String fromPhone;

}
