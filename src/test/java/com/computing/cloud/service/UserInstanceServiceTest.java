package com.computing.cloud.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInstanceServiceTest {
	
	@Autowired
	private UserInstanceService service;
	
	@Test
	public void shouldCreateFiveUserInstances() {
		final List<UserInstance> userInstances = service.createInstances(5, InstanceStatus.OFF);
		assertEquals(5, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.OFF, userInstance.getStatus());
		}
	}

}