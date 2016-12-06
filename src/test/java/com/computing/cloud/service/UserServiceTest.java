package com.computing.cloud.service;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.computing.cloud.domain.Authentication;
import com.computing.cloud.exception.AuthenticationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Autowired
	private UserService service;
	
	protected void expect(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
        thrown.expectMessage(message);
	}
	
	@Test
	public void shouldReturnTokenWhenUsernameAndPasswordAreRight() throws AuthenticationException {
		final Authentication authentication = service.login("userOne", "passOne");
		assertEquals("token", authentication.getToken());
	}
	
	@Test
	public void shouldThrowExceptionWhenUsernameIsNotRight() throws AuthenticationException {
		expect(AuthenticationException.class, "Username and/or password are incorrect");
		service.login("wrong_username", "passOne");
	}
	
	@Test
	public void shouldThrowExceptionWhenPasswordIsNotRight() throws AuthenticationException {
		expect(AuthenticationException.class, "Username and/or password are incorrect");
		service.login("userOne", "wrong_password");
	}
	
	@Test
	public void shouldThrowExceptionWhenUsernameAndPasswordAreNotRight() throws AuthenticationException {
		expect(AuthenticationException.class, "Username and/or password are incorrect");
		service.login("wrong_username", "wrong_password");
	}

}