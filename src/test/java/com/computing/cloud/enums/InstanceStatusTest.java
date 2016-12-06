package com.computing.cloud.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InstanceStatusTest {

	private static void enumCodeCoverage(Class<? extends Enum<?>> enumClass) {
		try {
			for (Object o : (Object[]) enumClass.getMethod("values").invoke(
					null)) {
				enumClass.getMethod("valueOf", String.class).invoke(null,
						o.toString());
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void shouldReturnRightInstanceStatus() {
		assertEquals(InstanceStatus.OFF, InstanceStatus.getByStatus(false));
		assertEquals(InstanceStatus.ON, InstanceStatus.getByStatus(true));
	}
	
	@Test
	public void shouldReadValuesOfInstanceStatus() {
		enumCodeCoverage(InstanceStatus.class);
	}

}