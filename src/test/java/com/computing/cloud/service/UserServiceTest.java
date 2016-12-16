package com.computing.cloud.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.computing.cloud.AbstractTest;
import com.computing.cloud.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest extends AbstractTest {
	
	@Autowired
	private UserService service;
	
	@Test
	public void shouldValidateUserAndSaveValidUser() {
		User user = User.builder()
					.username("username")
					.password("password")
					.creditCard("creditCard")
					.email("email")
					.status(Boolean.TRUE)
					.build();
		user = service.create(user);
		assertNotNull(user.getId());
		assertTrue(user.getId() > 0);
	}
	
	@Test
	public void shouldReturnTokenWhenUsernameAndPasswordAreRight() {
		final User user = service.findByUsernameAndPassword("userOne", "passOne");
		assertNotNull(user);
	}
	
	@Test
	public void shouldThrowExceptionWhenUsernameIsNotRight() {
		final User user = service.findByUsernameAndPassword("wrong_username", "passOne");
		assertNull(user);
	}
	
	@Test
	public void shouldThrowExceptionWhenPasswordIsNotRight() {
		final User user = service.findByUsernameAndPassword("userOne", "wrong_password");
		assertNull(user);
	}
	
	@Test
	public void shouldThrowExceptionWhenUsernameAndPasswordAreNotRight() {
		final User user = service.findByUsernameAndPassword("wrong_username", "wrong_password");
		assertNull(user);
	}

}