package com.computing.cloud.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode=ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserInstanceServiceTest {
	
	@Autowired
	private UserInstanceService service;
	private static User userOne = new User();
	private static User userTwo = new User();
	
	@BeforeClass
	public static void setUp() {
		userOne.setUsername("userOne");
		userTwo.setUsername("userTwo");
	}
	
	@Test
	public void shouldNotCreateUserInstancesWithNegativeQuantityOrZero() {
		List<UserInstance> userInstances = service.createInstances(userOne, -1, InstanceStatus.OFF);
		assertEquals(0, userInstances.size());
		
		userInstances = service.createInstances(userOne, 0, InstanceStatus.OFF);
		assertEquals(0, userInstances.size());
	}
	
	@Test
	public void shouldCreateUserInstancesWithStatusOff() {
		final List<UserInstance> userInstances = service.createInstances(userOne, 5, InstanceStatus.OFF);
		assertEquals(5, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.OFF, userInstance.getStatus());
		}
	}
	
	@Test
	public void shouldReturnUserInstancesWithStatusOff() {
		service.createInstances(userOne, 5, InstanceStatus.OFF);
		service.createInstances(userOne, 10, InstanceStatus.ON);
		List<UserInstance> userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.OFF);
		assertEquals(5, userInstances.size());
		
		service.createInstances(userOne, 3, InstanceStatus.OFF);
		service.createInstances(userOne, 15, InstanceStatus.ON);
		userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.OFF);
		assertEquals(8, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.OFF, userInstance.getStatus());
		}
	}
	
	@Test
	public void shouldNotReturnUserInstancesFromAnotherUser() {
		service.createInstances(userOne, 5, InstanceStatus.OFF);
		service.createInstances(userTwo, 10, InstanceStatus.OFF);
		List<UserInstance> userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.OFF);
		assertEquals(5, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.OFF, userInstance.getStatus());
			assertEquals(userOne.getUsername(), userInstance.getUser().getUsername());
		}
	}
	
	@Test
	public void shouldReturnUserInstances() {
		service.createInstances(userOne, 5, InstanceStatus.OFF);
		service.createInstances(userOne, 10, InstanceStatus.ON);
		List<UserInstance> userInstances = service.getInstancesByUser(userOne);
		assertEquals(15, userInstances.size());
	}
	
	@Test
	public void shouldReturnEmptyListIfNoUserInstances() {
		List<UserInstance> userInstances = service.getInstancesByUser(userOne);
		assertEquals(0, userInstances.size());
	}
	
	@Test
	public void shouldReturnEmptyListIfNoUserInstancesWithAnyStatus() {
		List<UserInstance> userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.OFF);
		assertEquals(0, userInstances.size());
		
		userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.ON);
		assertEquals(0, userInstances.size());
	}
	
	@Test
	public void shouldReturnUserInstancesWithStatusOn() {
		service.createInstances(userOne, 5, InstanceStatus.OFF);
		service.createInstances(userOne, 10, InstanceStatus.ON);
		List<UserInstance> userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.ON);
		assertEquals(10, userInstances.size());
		
		service.createInstances(userOne, 3, InstanceStatus.OFF);
		service.createInstances(userOne, 15, InstanceStatus.ON);
		userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.ON);
		assertEquals(25, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.ON, userInstance.getStatus());
		}
	}
	
	@Test
	public void shouldCreateUserInstancesWithStatusOn() {
		final List<UserInstance> userInstances = service.createInstances(userOne, 5, InstanceStatus.ON);
		assertEquals(5, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.ON, userInstance.getStatus());
		}
	}

}