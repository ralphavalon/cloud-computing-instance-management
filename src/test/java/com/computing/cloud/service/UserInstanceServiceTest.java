package com.computing.cloud.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.computing.cloud.domain.UserInstance;

public class UserInstanceServiceTest {
	
	private UserInstanceService service = new UserInstanceServiceImpl();
	
	@Test
	public void shouldCreateFiveUserInstances() {
		final List<UserInstance> userInstances = service.createInstances(5);
		assertEquals(5, userInstances.size());
	}

}