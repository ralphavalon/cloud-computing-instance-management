package com.computing.cloud.domain;

import static org.junit.Assert.assertEquals;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

import com.computing.cloud.AbstractTest;

public class StorageTypeServiceTest extends AbstractTest {
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(StorageType.class)
	    .suppress(Warning.NONFINAL_FIELDS)
	    .suppress(Warning.STRICT_INHERITANCE)
	    .verify();
	}
	
	@Test
	public void hashcodeContract() {
		final StorageType storageTypeA = new StorageType();
	    final StorageType storageTypeB = new StorageType();
	    assertEquals(storageTypeA, storageTypeB);
	    assertEquals(storageTypeA.hashCode(), storageTypeB.hashCode());
	    assertEquals(storageTypeA.toString(), storageTypeB.toString());
	}
	
}