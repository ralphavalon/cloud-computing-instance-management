package com.computing.cloud;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

public abstract class AbstractTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	protected void expect(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
        thrown.expectMessage(message);
	}

}
