package com.computing.cloud.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
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
import com.computing.cloud.to.response.UserResponseTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode=ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerTest extends AbstractSystemTest {
	
		@Test
		public void apiShouldGetUser() {
			UserResponseTO response = given()
				.get(getUrl()+"/users/1")
				.then()
					.statusCode(HttpStatus.SC_OK)
					.extract().body().as(UserResponseTO.class);
			
			assertNotNull( response );
			assertEquals(new Long(1), response.getId());
			assertEquals("userOne" , response.getUser());
			assertEquals("userOne@test.com" , response.getEmail());
		}
		
		@Test
		public void apiShouldGetUserList() {
			UserResponseTO[] responseTOArray = given()
				.get(getUrl()+"/users/")
				.then()
					.statusCode(HttpStatus.SC_OK)
					.extract().body().as(UserResponseTO[].class);
			
			assertNotNull( responseTOArray );
			assertEquals(3, responseTOArray.length);
			
			UserResponseTO responseTO = responseTOArray[0];
			assertEquals(new Long(1), responseTO.getId());
			assertEquals("userOne" , responseTO.getUser());
			assertEquals("userOne@test.com" , responseTO.getEmail());
			
			responseTO = responseTOArray[1];
			assertEquals(new Long(2), responseTO.getId());
			assertEquals("userTwo" , responseTO.getUser());
			assertEquals("userTwo@test.com" , responseTO.getEmail());
			
			responseTO = responseTOArray[2];
			assertEquals(new Long(3), responseTO.getId());
			assertEquals("userThree" , responseTO.getUser());
			assertEquals("userThree@test.com" , responseTO.getEmail());
		}
		
		@Test
		public void apiShouldCreateUser() throws Exception {
			UserResponseTO response = given()
				.contentType(ContentType.JSON)
				.body(getCreateUserRequestTO())
				.post(getUrl()+"/users/")
				.then()
					.statusCode(HttpStatus.SC_CREATED)
					.extract().body().as(UserResponseTO.class);
			
			assertNotNull( response );
			assertEquals(new Long(4), response.getId());
			assertEquals("userFour" , response.getUser());
			assertEquals("new_email@test.com" , response.getEmail());
		}
		
		@Test
		public void apiShouldUpdateUser() throws Exception {
			given()
				.contentType(ContentType.JSON)
				.body(getUpdateUserRequestTO())
				.put(getUrl()+"/users/1")
				.then()
					.statusCode(HttpStatus.SC_NO_CONTENT);
			
		}
		
		private String getCreateUserRequestTO() throws JsonProcessingException {
			Map<String, String> createUserMap = new HashMap<String, String>();
			createUserMap.put("username", "userFour");
			createUserMap.put("password", "mypass123");
			createUserMap.put("email", "new_email@test.com");
			createUserMap.put("creditCard", "5105105105105100");
			return new ObjectMapper().writeValueAsString(createUserMap);
		}
		
		private String getUpdateUserRequestTO() throws JsonProcessingException {
			Map<String, String> createUserMap = new HashMap<String, String>();
			createUserMap.put("username", "userOneUpdated");
			createUserMap.put("email", "emailOneUpdated@test.com");
			createUserMap.put("creditCard", "4012888888881881");
			return new ObjectMapper().writeValueAsString(createUserMap);
		}
		
}