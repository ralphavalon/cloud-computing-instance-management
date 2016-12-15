package com.computing.cloud.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.computing.cloud.AbstractSystemTest;
import com.computing.cloud.enums.InstanceStatus;
import com.computing.cloud.to.response.UserInstanceResponseTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode=ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserInstanceControllerTest extends AbstractSystemTest {
	
		@Test
		public void apiShouldGetUserInstanceListByUser() {
			UserInstanceResponseTO[] responseTOArray = given()
					.get(getUrl()+"/userInstances/user/2")
					.then()
						.statusCode(HttpStatus.SC_OK)
						.extract().body().as(UserInstanceResponseTO[].class);
				
				assertNotNull( responseTOArray );
				assertEquals(2, responseTOArray.length);
				
				UserInstanceResponseTO response = responseTOArray[0];
				assertEquals(new Long(5), response.getId());
				assertEquals(new Long(1) , response.getInstanceId());
				assertEquals("small" , response.getInstanceName());
				assertEquals("034a2197-69d7-4369-944b-a8803f448756" , response.getUserInstanceName());
				assertEquals("Windows Server 2003" , response.getOperatingSystem());
				assertEquals(InstanceStatus.OFF , response.getStatus());
				
				response = responseTOArray[1];
				assertEquals(new Long(6), response.getId());
				assertEquals(new Long(1) , response.getInstanceId());
				assertEquals("small" , response.getInstanceName());
				assertEquals("65cbf26f-6541-4d57-87db-d7ef5067e72a" , response.getUserInstanceName());
				assertEquals("Ubuntu 12.04" , response.getOperatingSystem());
				assertEquals(InstanceStatus.OFF , response.getStatus());
				
		}
		
		@Test
		public void apiShouldGetUserInstanceById() {
			UserInstanceResponseTO response = given()
					.get(getUrl()+"/userInstances/5")
					.then()
						.statusCode(HttpStatus.SC_OK)
						.extract().body().as(UserInstanceResponseTO.class);
				
				assertNotNull( response );
				assertEquals(new Long(5), response.getId());
				assertEquals(new Long(1) , response.getInstanceId());
				assertEquals("small" , response.getInstanceName());
				assertEquals("034a2197-69d7-4369-944b-a8803f448756" , response.getUserInstanceName());
				assertEquals("Windows Server 2003" , response.getOperatingSystem());
				assertEquals(InstanceStatus.OFF , response.getStatus());
			
		}
		
		@Test
		public void apiShouldCreateUserInstances() throws Exception {
			UserInstanceResponseTO[] responseTOArray = given()
				.contentType(ContentType.JSON)
				.body(getCreateUserInstanceRequestTO())
				.post(getUrl()+"/userInstances/")
				.then()
					.statusCode(HttpStatus.SC_CREATED)
					.extract().body().as(UserInstanceResponseTO[].class);
			
			assertNotNull( responseTOArray );
			assertEquals(3, responseTOArray.length);
			
			UserInstanceResponseTO firstCreated = responseTOArray[0];
			UserInstanceResponseTO secondCreated = responseTOArray[1];
			UserInstanceResponseTO thirdCreated = responseTOArray[2];
			
			assertEquals(new Long(7), firstCreated.getId());
			assertEquals(new Long(1), firstCreated.getInstanceId());
			assertEquals("small", firstCreated.getInstanceName());
			assertEquals("Ubuntu 12.04", firstCreated.getOperatingSystem());
			assertEquals(InstanceStatus.OFF, firstCreated.getStatus());
			
			assertEquals(new Long(8), secondCreated.getId());
			assertEquals(new Long(1), secondCreated.getInstanceId());
			assertEquals("small", secondCreated.getInstanceName());
			assertEquals("Ubuntu 12.04", secondCreated.getOperatingSystem());
			assertEquals(InstanceStatus.OFF, secondCreated.getStatus());
			
			assertEquals(new Long(9), thirdCreated.getId());
			assertEquals(new Long(1), thirdCreated.getInstanceId());
			assertEquals("small", thirdCreated.getInstanceName());
			assertEquals("Ubuntu 12.04", thirdCreated.getOperatingSystem());
			assertEquals(InstanceStatus.OFF, thirdCreated.getStatus());
			
			assertNotEquals(secondCreated, firstCreated.getUserInstanceName());
			assertNotEquals(thirdCreated, firstCreated.getUserInstanceName());
			assertNotEquals(secondCreated, thirdCreated.getUserInstanceName());
		}
		
		@Test
		public void apiShouldUpdateUserInstance() throws Exception {
			given()
				.contentType(ContentType.JSON)
				.body(getUpdateUserInstanceRequestTO())
				.patch(getUrl()+"/userInstances/1")
				.then()
					.statusCode(HttpStatus.SC_NO_CONTENT);
			
		}
		
		private String getCreateUserInstanceRequestTO() throws JsonProcessingException {
			Map<String, Object> createUserInstanceMap = new HashMap<String, Object>();
			createUserInstanceMap.put("userId", 2L);
			createUserInstanceMap.put("instanceId", 1L);
			createUserInstanceMap.put("operatingSystemId", 2L);
			createUserInstanceMap.put("status", InstanceStatus.OFF);
			createUserInstanceMap.put("quantity", 3);
			return new ObjectMapper().writeValueAsString(createUserInstanceMap);
		}
		
		private String getUpdateUserInstanceRequestTO() throws JsonProcessingException {
			Map<String, Object> updateUserInstanceMap = new HashMap<String, Object>();
			updateUserInstanceMap.put("status", InstanceStatus.ON);
			return new ObjectMapper().writeValueAsString(updateUserInstanceMap);
		}
		
}