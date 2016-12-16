package com.computing.cloud.exception;

import java.util.List;

import com.computing.cloud.validator.ValidationError;

@SuppressWarnings("serial")
public class BadRequestException extends ApiException {

	public BadRequestException(Class<? extends Object> klass) {
		super("Badly formatted request", 400);
	}
	
	public BadRequestException(List<ValidationError> validationErrorsEntityList) {
		super("Badly formatted request", 400, validationErrorsEntityList);
	}
	
}
