package com.computing.cloud;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.OperatingSystem;
import com.computing.cloud.domain.StorageType;
import com.computing.cloud.domain.User;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.domain.Instance.InstanceBuilder;
import com.computing.cloud.domain.User.UserBuilder;
import com.computing.cloud.enums.InstanceStatus;

public abstract class AbstractTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	protected static User userOne;
	protected static User userTwo;
	protected static User userThree;
	protected static Instance small;
	protected static Instance medium;
	protected static Instance large;
	protected static OperatingSystem windowsServer2003 = new OperatingSystem("Windows Server 2003");
	protected static OperatingSystem ubuntu12_04 = new OperatingSystem("Ubuntu 12.04");
	protected static StorageType hdd = new StorageType("HDD");
	protected static StorageType ssd = new StorageType("SSD");
	protected static UserInstance userOneInstance = new UserInstance(userOne, InstanceStatus.OFF, small, ubuntu12_04);
	protected static UserInstance userTwoInstance = new UserInstance(userTwo, InstanceStatus.OFF, small, windowsServer2003);
	
	protected void expect(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
        thrown.expectMessage(message);
	}
	
	@BeforeClass
	public static void setUp() {
		hdd.setId(1L);
		ssd.setId(2L);
		
		windowsServer2003.setId(1L);
		ubuntu12_04.setId(2L);
		
		userOneInstance.setId(1L);
		userTwoInstance.setId(5L);
		
		UserBuilder builder = User.builder();
		builder
			.id(1L)
			.username("userOne")
			.password("passOne")
			.email("userOne@test.com")
			.creditCard("5555555555554444");
		userOne = builder.build();
		
		builder = User.builder();
		builder
			.id(2L)
			.username("userTwo")
			.password("passTwo")
			.email("userTwo@test.com")
			.creditCard("5105105105105100");
		userTwo = builder.build();
		
		builder = User.builder();
		builder
			.id(3L)
			.username("userThree")
			.password("passThree")
			.email("userThree@test.com")
			.creditCard("4111111111111111");
		userThree = builder.build();
		
		InstanceBuilder instanceBuilder = Instance.builder();
		instanceBuilder
			.id(1L)
			.name("small")
			.cpu(1)
			.memory(2)
			.storage(40)
			.storageType(hdd);
		small = instanceBuilder.build();
		
		instanceBuilder = Instance.builder();
		instanceBuilder
			.id(2L)
			.name("medium")
			.cpu(2)
			.memory(4)
			.storage(100)
			.storageType(hdd);
		medium = instanceBuilder.build();
		
		instanceBuilder = Instance.builder();
		instanceBuilder
			.id(3L)
			.name("large")
			.cpu(2)
			.memory(8)
			.storage(250)
			.storageType(ssd);
		large = instanceBuilder.build();
	}
	
	protected void assertEqualsBigDecimal(BigDecimal expectedValue, BigDecimal actualValue) {
		BigDecimal expected = expectedValue.setScale(6, RoundingMode.HALF_UP);
		BigDecimal actual = actualValue.setScale(6, RoundingMode.HALF_UP);
		String message = "Expected: " + expected + " Actual: " + actual;
		assertTrue(message, expected.equals(actual));
	}

}
