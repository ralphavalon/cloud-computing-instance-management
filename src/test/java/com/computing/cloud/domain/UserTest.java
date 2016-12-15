package com.computing.cloud.domain;

import static org.junit.Assert.assertEquals;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

import com.computing.cloud.AbstractTest;

public class UserTest extends AbstractTest {
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(User.class)
	    .suppress(Warning.NONFINAL_FIELDS)
	    .suppress(Warning.STRICT_INHERITANCE)
	    .verify();
	}
	
	@Test
	public void builderAndHashcodeContract() {
	    final User.UserBuilder userBuilderA = new User.UserBuilder();
	    final User.UserBuilder userBuilderB = new User.UserBuilder();
		final User userA = userBuilderA.build();
	    final User userB = userBuilderB.build();
	    assertEquals(userA, userB);
	    assertEquals(userA.toString(), userB.toString());
	    assertEquals(userA.hashCode(), userB.hashCode());
	    assertEquals(userA.toString(), userB.toString());
	}
	
}