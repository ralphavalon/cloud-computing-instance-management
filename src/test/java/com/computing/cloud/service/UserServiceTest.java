package com.computing.cloud.service;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.computing.cloud.domain.Authentication;
import com.computing.cloud.exception.AuthenticationException;

public class UserServiceTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	private UserService service = new UserServiceImpl();
	
	protected void expect(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
        thrown.expectMessage(message);
	}
	
	@Test
	public void shouldReturnTokenWhenUsernameAndPasswordAreRight() throws AuthenticationException {
		final Authentication authentication = service.login("username", "password");
		assertEquals("token", authentication.getToken());
	}
	
	@Test
	public void shouldThrowExceptionWhenUsernameIsNotRight() throws AuthenticationException {
		expect(AuthenticationException.class, "Username and/or password are incorrect");
		service.login("wrong_username", "password");
	}
	
	@Test
	public void shouldThrowExceptionWhenPasswordIsNotRight() throws AuthenticationException {
		expect(AuthenticationException.class, "Username and/or password are incorrect");
		service.login("username", "wrong_password");
	}
	
	@Test
	public void shouldThrowExceptionWhenUsernameAndPasswordAreNotRight() throws AuthenticationException {
		expect(AuthenticationException.class, "Username and/or password are incorrect");
		service.login("wrong_username", "wrong_password");
	}

}