package com.computing.cloud.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.computing.cloud.AbstractSystemTest;
import com.computing.cloud.to.response.PlanResponseTO;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlanControllerTest extends AbstractSystemTest {
	
		@Test
		public void apiShouldGetPlan() {
			PlanResponseTO response = given()
				.get(getUrl()+"plans/1")
				.then()
					.statusCode(HttpStatus.SC_OK)
					.extract().body().as(PlanResponseTO.class);
			
			assertNotNull( response );
			assertEquals(new Long(1), response.getId());
			assertEquals("Basic" , response.getName());
		}
		
		@Test
		public void apiShouldGetPlanList() {
			PlanResponseTO[] response = given()
				.get(getUrl()+"plans/")
				.then()
					.statusCode(HttpStatus.SC_OK)
					.extract().body().as(PlanResponseTO[].class);
			
			assertNotNull( response );
			assertEquals(1, response.length);
			PlanResponseTO planResponseTO = response[0];
			assertEquals(new Long(1), planResponseTO.getId());
			assertEquals("Basic" , planResponseTO.getName());
		}
		
}