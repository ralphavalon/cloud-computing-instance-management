package com.computing.cloud.exception;

import java.util.List;

import com.computing.cloud.validator.ValidationError;

@SuppressWarnings("serial")
public class UnprocessableEntityException extends ApiException {

	public UnprocessableEntityException(Class<? extends Object> klass) {
		super("The entity could not be processed", 422);
	}
	
	public UnprocessableEntityException(List<ValidationError> validationErrorsEntityList) {
		super("Badly formatted request", 422, validationErrorsEntityList);
	}
}
