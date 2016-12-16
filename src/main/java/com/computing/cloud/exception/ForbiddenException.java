package com.computing.cloud.exception;

@SuppressWarnings("serial")
public class ForbiddenException extends ApiException {

	public ForbiddenException() {
		super("Account not found", 403);
	}
	
}
