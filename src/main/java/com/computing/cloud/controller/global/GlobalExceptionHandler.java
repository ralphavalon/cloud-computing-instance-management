package com.computing.cloud.controller.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.computing.cloud.exception.ApiException;
import com.computing.cloud.validator.RestrictionType;
import com.computing.cloud.validator.ValidationError;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final String ERRORS_LIST_FIELD = "errorsList";
	private static final String MESSAGE_FIELD = "message";

	@ExceptionHandler({ApiException.class})
	public ResponseEntity<Map<String, Object>> apiError(ApiException e) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put(MESSAGE_FIELD, e.getMessage());
		
		if( ! e.getErrorsList().isEmpty() ) {
			response.put(ERRORS_LIST_FIELD, e.getErrorsList());
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.valueOf(e.getStatus()));
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Map<String, String>> genericError(Exception e) {
		Map<String, String> response = new HashMap<String, String>();
		response.put(MESSAGE_FIELD, e.getMessage());
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value={HttpMessageNotReadableException.class, TypeMismatchException.class})
	public ResponseEntity<Map<String, Object>> badRequest(Exception e) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put(MESSAGE_FIELD, "Invalid json for this action");
		List<ValidationError> errorsList = new ArrayList<ValidationError>();
		errorsList.add( new ValidationError("request", RestrictionType.INVALID, e.getMessage()) );
		response.put(ERRORS_LIST_FIELD, errorsList);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
}
