package com.gabryel.login.validation;

import java.time.LocalDateTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MaxYearValidator implements ConstraintValidator<MaxYearBirth, Integer>{

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value == null) return false;
		int currentYear = LocalDateTime.now().getYear();
		return value <= currentYear;
	}

}
