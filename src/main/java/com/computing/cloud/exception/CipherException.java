package com.computing.cloud.exception;

@SuppressWarnings("serial")
public class CipherException extends Exception {

	public CipherException(String message, Exception e) {
		super(message, e);
	}

}
