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

import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.OperatingSystem;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.Instance.InstanceBuilder;
import com.computing.cloud.domain.User.UserBuilder;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;
import com.computing.cloud.enums.StorageType;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode=ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserInstanceServiceTest {
	
	@Autowired
	private UserInstanceService service;
	private static User userOne;
	private static User userTwo;
	private static Instance small;
	private static Instance medium;
	private static OperatingSystem windowsServer2003 = new OperatingSystem("Windows Server 2003");
	private static OperatingSystem ubuntu12_04 = new OperatingSystem("Ubuntu 12.04");
	
	@BeforeClass
	public static void setUp() {
		UserBuilder builder = User.builder();
		builder.username("userOne");
		userOne = builder.build();
		
		builder = User.builder();
		builder.username("userTwo");
		userTwo = builder.build();
		
		InstanceBuilder instanceBuilder = Instance.builder();
		instanceBuilder
			.id(1L)
			.name("small")
			.cpu(1)
			.memory(2)
			.storage(40)
			.storageType(StorageType.HDD);
		small = instanceBuilder.build();
		
		instanceBuilder = Instance.builder();
		instanceBuilder
			.id(2L)
			.name("medium")
			.cpu(2)
			.memory(4)
			.storage(100)
			.storageType(StorageType.HDD);
		medium = instanceBuilder.build();
	}
	
	@Test
	public void shouldNotCreateUserInstancesWithNegativeQuantityOrZero() {
		List<UserInstance> userInstances = service.createInstances(
				userOneInstance(InstanceStatus.OFF, small, windowsServer2003)
				, -1);
		assertEquals(0, userInstances.size());
		
		userInstances = service.createInstances(
				userOneInstance(InstanceStatus.OFF, small, windowsServer2003)
				, 0);
		assertEquals(0, userInstances.size());
	}

	@Test
	public void shouldCreateUserInstancesWithStatusOff() {
		final List<UserInstance> userInstances = service.createInstances(
				userOneInstance(InstanceStatus.OFF, medium, ubuntu12_04),
				5);
		assertEquals(5, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.OFF, userInstance.getStatus());
		}
	}
	


@Test
	public void shouldReturnUserInstancesWithStatusOff() {
		service.createInstances(userOneInstance(InstanceStatus.OFF, small, ubuntu12_04), 5);
		service.createInstances(userOneInstance(InstanceStatus.ON, small, ubuntu12_04), 10);
		List<UserInstance> userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.OFF);
		assertEquals(5, userInstances.size());
		
		service.createInstances(userOneInstance(InstanceStatus.OFF, small, ubuntu12_04), 3);
		service.createInstances(userOneInstance(InstanceStatus.ON, small, ubuntu12_04), 15);
		userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.OFF);
		assertEquals(8, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.OFF, userInstance.getStatus());
		}
	}
	
	@Test
	public void shouldNotReturnUserInstancesFromAnotherUser() {
		service.createInstances(userTwoInstance(InstanceStatus.OFF, small, windowsServer2003), 10);
		List<UserInstance> userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.OFF);
		assertEquals(0, userInstances.size());
		
		service.createInstances(userOneInstance(InstanceStatus.OFF, small, windowsServer2003), 5);
		userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.OFF);
		assertEquals(5, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.OFF, userInstance.getStatus());
			assertEquals(userOne.getUsername(), userInstance.getUser().getUsername());
		}
	}
	
	@Test
	public void shouldReturnUserInstances() {
		service.createInstances(userOneInstance(InstanceStatus.OFF, small, windowsServer2003), 5);
		service.createInstances(userOneInstance(InstanceStatus.ON, small, windowsServer2003), 10);
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
		service.createInstances(userOneInstance(InstanceStatus.OFF, small, windowsServer2003), 5);
		service.createInstances(userOneInstance(InstanceStatus.ON, small, windowsServer2003), 10);
		List<UserInstance> userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.ON);
		assertEquals(10, userInstances.size());
		
		service.createInstances(userOneInstance(InstanceStatus.OFF, small, windowsServer2003), 3);
		service.createInstances(userOneInstance(InstanceStatus.ON, small, windowsServer2003), 15);
		userInstances = service.getInstancesByUserAndStatus(userOne, InstanceStatus.ON);
		assertEquals(25, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.ON, userInstance.getStatus());
		}
	}
	
	@Test
	public void shouldCreateUserInstancesWithStatusOn() {
		final List<UserInstance> userInstances = service.createInstances(userOneInstance(InstanceStatus.ON, small, ubuntu12_04), 5);
		assertEquals(5, userInstances.size());
		
		for (UserInstance userInstance : userInstances) {
			assertEquals(InstanceStatus.ON, userInstance.getStatus());
		}
	}
	
	private UserInstance userOneInstance(InstanceStatus status, Instance instance, OperatingSystem os) {
		return new UserInstance(userOne, status, instance, os);
	}
	
	private UserInstance userTwoInstance(InstanceStatus status, Instance instance, OperatingSystem os) {
		return new UserInstance(userTwo, status, instance, os);
	}

}