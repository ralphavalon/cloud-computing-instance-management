package com.computing.cloud.domain;

import static org.junit.Assert.assertEquals;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

import com.computing.cloud.AbstractTest;

public class UserInstanceServiceTest extends AbstractTest {
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(UserInstance.class)
	    .suppress(Warning.NONFINAL_FIELDS)
	    .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
	    .suppress(Warning.STRICT_INHERITANCE)
	    .withIgnoredFields("status", "user", "operatingSystem", "instance")
	    .withPrefabValues(Instance.class, small, medium)
	    .verify();
	}
	
	@Test
	public void hashcodeContract() {
		final UserInstance userInstanceA = new UserInstance();
	    final UserInstance userInstanceB = new UserInstance();
	    assertEquals(userInstanceA, userInstanceB);
	    assertEquals(userInstanceA.hashCode(), userInstanceB.hashCode());
	    assertEquals(userInstanceA.toString(), userInstanceB.toString());
	}
	
}