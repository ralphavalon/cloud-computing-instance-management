package com.computing.cloud.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.computing.cloud.AbstractTest;

public class InstanceServiceTest extends AbstractTest {
	
	@Test
	public void builderContract() {
	    final Instance.InstanceBuilder instanceBuilderA = new Instance.InstanceBuilder();
	    final Instance.InstanceBuilder instanceBuilderB = new Instance.InstanceBuilder();
	    assertEquals(instanceBuilderA.toString(), instanceBuilderB.toString());
	}
	
}