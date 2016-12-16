package com.computing.cloud.exception;

import java.util.ArrayList;
import java.util.List;

import com.computing.cloud.validator.ValidationError;


@SuppressWarnings("serial")
public class ApiException extends RuntimeException {

	private int status;
	private List<ValidationError> errorsList = new ArrayList<ValidationError>();
	
	public ApiException(String message, int status) {
		super(message);
		this.status = status;
	}

	public ApiException(String message, int status, List<ValidationError> validationErrorsEntityList) {
		this(message, status);
		this.errorsList = validationErrorsEntityList;
	}

	public ApiException(String message, int status, Exception cause) {
		super(message, cause);
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
	
	public List<ValidationError> getErrorsList() {
		return errorsList;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}