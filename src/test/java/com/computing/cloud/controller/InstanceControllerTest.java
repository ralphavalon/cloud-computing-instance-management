package com.computing.cloud.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.computing.cloud.AbstractSystemTest;
import com.computing.cloud.to.response.InstanceResponseTO;

@RunWith(SpringJUnit4ClassRunner.class)
public class InstanceControllerTest extends AbstractSystemTest {
	
		@Test
		public void apiShouldGetInstance() {
			InstanceResponseTO response = given()
				.get(getUrl()+"instances/1")
				.then()
					.statusCode(HttpStatus.SC_OK)
					.extract().body().as(InstanceResponseTO.class);
			
			assertNotNull( response );
			assertEquals(small.getId(), response.getId());
			assertEquals(small.getName() , response.getName());
			
			assertEquals(new Long(1) , response.getPlanId());
			assertEquals("Basic" , response.getPlanName());
			
			assertEquals(small.getCpu() , response.getCpu());
			assertEquals(small.getMemory() , response.getMemory());
			assertEquals(small.getStorage() , response.getStorage());
			assertEquals(small.getStorageType() , response.getStorageType());
		}
		
		@Test
		public void apiShouldGetInstanceList() {
			InstanceResponseTO[] responseTOArray = given()
				.get(getUrl()+"instances/")
				.then()
					.statusCode(HttpStatus.SC_OK)
					.extract().body().as(InstanceResponseTO[].class);
			
			assertNotNull( responseTOArray );
			assertEquals(3, responseTOArray.length);
			
			InstanceResponseTO response = responseTOArray[0];
			assertEquals(small.getId(), response.getId());
			assertEquals(small.getName() , response.getName());
			
			assertEquals(new Long(1) , response.getPlanId());
			assertEquals("Basic" , response.getPlanName());
			
			assertEquals(small.getCpu() , response.getCpu());
			assertEquals(small.getMemory() , response.getMemory());
			assertEquals(small.getStorage() , response.getStorage());
			assertEquals(small.getStorageType() , response.getStorageType());
			
			response = responseTOArray[1];
			assertEquals(medium.getId(), response.getId());
			assertEquals(medium.getName() , response.getName());
			
			assertEquals(new Long(1) , response.getPlanId());
			assertEquals("Basic" , response.getPlanName());
			
			assertEquals(medium.getCpu() , response.getCpu());
			assertEquals(medium.getMemory() , response.getMemory());
			assertEquals(medium.getStorage() , response.getStorage());
			assertEquals(medium.getStorageType() , response.getStorageType());
			
			response = responseTOArray[2];
			assertEquals(large.getId(), response.getId());
			assertEquals(large.getName() , response.getName());
			
			assertEquals(new Long(1) , response.getPlanId());
			assertEquals("Basic" , response.getPlanName());
			
			assertEquals(large.getCpu() , response.getCpu());
			assertEquals(large.getMemory() , response.getMemory());
			assertEquals(large.getStorage() , response.getStorage());
			assertEquals(large.getStorageType() , response.getStorageType());
		}
		
}