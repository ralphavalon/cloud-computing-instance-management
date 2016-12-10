package com.computing.cloud.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.AbstractTest;
import com.computing.cloud.domain.Plan;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode=ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlanServiceTest extends AbstractTest {
	
	@Autowired
	private PlanService service;
	
	@Transactional
	@Test
	public void shouldCalculatePricePerHour() throws Exception {
		Plan plan = service.findById(1L);
		BigDecimal pricePerHour = service.getPricePerHour(plan, small);
		assertEqualsBigDecimal(new BigDecimal(0.024), pricePerHour);
		
		pricePerHour = service.getPricePerHour(plan, medium);
		assertEqualsBigDecimal(new BigDecimal(0.052), pricePerHour);
		
		pricePerHour = service.getPricePerHour(plan, large);
		assertEqualsBigDecimal(new BigDecimal(0.206), pricePerHour);
		
		assertNotNull(service.findAll().get(0));
	}
	
	@Transactional
	@Test
	public void shouldRetrieveAllPlans() throws Exception {
		final List<Plan> plans = service.findAll();
		
		assertNotNull(plans.toString());
	}

}