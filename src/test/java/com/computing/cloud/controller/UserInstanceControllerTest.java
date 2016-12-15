package com.computing.cloud.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.computing.cloud.AbstractSystemTest;
import com.computing.cloud.enums.InstanceStatus;
import com.computing.cloud.to.response.UserInstanceResponseTO;

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
		
//		@Test
//		public void apiShouldCreateUserInstance() throws Exception {
//			UserInstanceResponseTO response = given()
//				.contentType(ContentType.JSON)
//				.body(getCreateUserInstanceRequestTO())
//				.post(getUrl()+"/userInstances/")
//				.then()
//					.statusCode(HttpStatus.SC_CREATED)
//					.extract().body().as(UserInstanceResponseTO.class);
//			
//			assertNotNull( response );
//			assertEquals(new Long(4), response.getId());
//			assertEquals("userInstanceFour" , response.getUserInstance());
//			assertEquals("new_email@test.com" , response.getEmail());
//			assertEquals(Boolean.TRUE, response.getActive());
//		}
//		
//		@Test
//		public void apiShouldUpdateUserInstance() throws Exception {
//			given()
//				.contentType(ContentType.JSON)
//				.body(getUpdateUserInstanceRequestTO())
//				.put(getUrl()+"/userInstances/1")
//				.then()
//					.statusCode(HttpStatus.SC_NO_CONTENT);
//			
//		}
//		
//		private String getCreateUserInstanceRequestTO() throws JsonProcessingException {
//			Map<String, String> createUserInstanceMap = new HashMap<String, String>();
//			createUserInstanceMap.put("userInstancename", "userInstanceFour");
//			createUserInstanceMap.put("password", "mypass123");
//			createUserInstanceMap.put("email", "new_email@test.com");
//			createUserInstanceMap.put("creditCard", "5105105105105100");
//			return new ObjectMapper().writeValueAsString(createUserInstanceMap);
//		}
//		
//		private String getUpdateUserInstanceRequestTO() throws JsonProcessingException {
//			Map<String, Object> createUserInstanceMap = new HashMap<String, Object>();
//			createUserInstanceMap.put("userInstancename", "userInstanceOneUpdated");
//			createUserInstanceMap.put("email", "emailOneUpdated@test.com");
//			createUserInstanceMap.put("creditCard", "4012888888881881");
//			createUserInstanceMap.put("status", Boolean.FALSE);
//			return new ObjectMapper().writeValueAsString(createUserInstanceMap);
//		}
		
}