package com.computing.cloud.exception;

@SuppressWarnings("serial")
public class InternalServerException extends ApiException {

	public InternalServerException() {
		super("An internal error occurred in server", 500);
	}

	public InternalServerException(String message, Exception cause) {
		super(message, 500, cause);
	}
	
}
