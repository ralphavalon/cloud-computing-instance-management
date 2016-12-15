package com.computing.cloud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.computing.cloud.AbstractTest;
import com.computing.cloud.domain.Authentication;
import com.computing.cloud.domain.User;
import com.computing.cloud.exception.AuthenticationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest extends AbstractTest {
	
	@Autowired
	private UserService service;
	
	@Test
	public void shouldValidateUserAndSaveValidUser() throws AuthenticationException {
		User user = User.builder()
					.username("username")
					.password("password")
					.creditCard("creditCard")
					.email("email")
					.build();
		user = service.create(user);
		assertNotNull(user.getId());
		assertTrue(user.getId() > 0);
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