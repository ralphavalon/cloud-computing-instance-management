package com.computing.cloud.exception;

@SuppressWarnings("serial")
public class NotFoundException extends ApiException {

	public NotFoundException(Class<? extends Object> klass) {
		super(String.format("Wrong identifier for resource %s", klass.getSimpleName()), 404);
	}

	public NotFoundException(String message, Exception cause) {
		super(message, 404, cause);
	}

}
