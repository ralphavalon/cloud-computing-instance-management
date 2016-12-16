package com.computing.cloud.exception;

@SuppressWarnings("serial")
public class ConflictException extends ApiException {

	public ConflictException(Class<? extends Object> klass) {
		super(String.format("Resource %s already exists", klass.getSimpleName()), 409);
	}

	public ConflictException(String message, Exception cause) {
		super(message, 409, cause);
	}

}
