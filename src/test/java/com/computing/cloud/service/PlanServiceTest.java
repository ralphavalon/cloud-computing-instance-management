package com.computing.cloud.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.computing.cloud.AbstractTest;
import com.computing.cloud.domain.Plan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanServiceTest extends AbstractTest {
	
	@Autowired
	private PlanService service;
	
	@Test
	public void shouldCalculatePricePerHour() throws Exception {
		Plan plan = service.findById(1L);
		BigDecimal pricePerHour = service.getPricePerHour(plan, small);
		assertEquals(new BigDecimal(0.024), pricePerHour);
		
		pricePerHour = service.getPricePerHour(plan, medium);
		assertEquals(new BigDecimal(0.052), pricePerHour);
		
		pricePerHour = service.getPricePerHour(plan, large);
		assertEquals(new BigDecimal(0.206), pricePerHour);
	}

	private void assertEquals(BigDecimal expectedValue, BigDecimal actualValue) {
		BigDecimal expected = expectedValue.setScale(6, RoundingMode.HALF_UP);
		BigDecimal actual = actualValue.setScale(6, RoundingMode.HALF_UP);
		String message = "Expected: " + expected + " Actual: " + actual;
		assertTrue(message, expected.equals(actual));
	}
	
}