package com.computing.cloud.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.computing.cloud.AbstractSystemTest;
import com.computing.cloud.to.response.UserResponseTO;

@RunWith(SpringJUnit4ClassRunner.class)
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
		
}