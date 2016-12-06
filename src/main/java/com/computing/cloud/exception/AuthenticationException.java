package com.computing.cloud.exception;

public class AuthenticationException extends Exception {

	private static final long serialVersionUID = -5104159654013912937L;
	
	@Override
	public String getMessage() {
		return "Username and/or password are incorrect";
	}

}