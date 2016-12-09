package com.computing.cloud.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

import com.computing.cloud.AbstractTest;

public class UserServiceTest extends AbstractTest {
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(User.class)
	    .suppress(Warning.NONFINAL_FIELDS)
	    .suppress(Warning.STRICT_INHERITANCE)
	    .verify();
	}
	
}