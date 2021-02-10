package com.carlot.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.carlot.exception.BusinessException;



public class DayOfBirth {
	
	public static final Logger log = LogManager.getFormatterLogger(DayOfBirth.class); // v2 set up

	public static Date toDate (String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
		sdf.setLenient(false);
		Date dob = null;
		try {
			dob = sdf.parse(s);
		} catch (ParseException e) {
			log.info("Invalid date format");
		}
		return dob;
	}
	
	public static Date validateAge (String s, LocalDate today) throws BusinessException {
		
		LocalDate dob = LocalDate.parse(s);
		int age = Period.between(dob, today).getYears();
		log.debug("age = "+ age);
		Date x = null;
		if (age > 21) { 
			x = toDate(s);
		} else { 
			throw new BusinessException ("You must be 21 or older to open an account");
		}
		return x;
		
	}

}
