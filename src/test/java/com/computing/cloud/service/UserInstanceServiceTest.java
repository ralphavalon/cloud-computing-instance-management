package com.computing.cloud.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

public class UserInstanceServiceTest {
	
	private UserInstanceService service = new UserInstanceServiceImpl();
	
	@Test
	public void shouldCreateFiveUserInstances() {
		final List<UserInstance> userInstances = service.createInstances(5, InstanceStatus.OFF);
		assertEquals(5, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.OFF, userInstance.getStatus());
		}
	}

}