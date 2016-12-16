package com.computing.cloud.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.computing.cloud.AbstractSystemTest;
import com.computing.cloud.authentication.Encrypter;
import com.computing.cloud.enums.Operation;
import com.computing.cloud.to.response.HistoryResponseTO;
import com.jayway.restassured.response.Header;

@RunWith(SpringJUnit4ClassRunner.class)
public class HistoryControllerTest extends AbstractSystemTest {

	@Test
	public void apiShouldGetHistoryListPerUser() {
		String externalId = "c86b4192-ad28-4d2d-8690-7ad109eaaaf5";
		Header token = new Header("token", Encrypter.encryptToken(externalId));
		Header userId = new Header("usercode", externalId);
		HistoryResponseTO[] response = given()
				.header(token)
				.header(userId)
				.get(getUrl() + "histories/user/2").then()
				.statusCode(HttpStatus.SC_OK).extract().body()
				.as(HistoryResponseTO[].class);

		assertNotNull(response);
		assertEquals(2, response.length);
		HistoryResponseTO historyResponseTO = response[0];
		assertEquals(new Long(5), historyResponseTO.getId());
		assertEquals(new Long(2), historyResponseTO.getUserId());
		assertEquals("userTwo", historyResponseTO.getUserName());
		assertEquals(new Long(5), historyResponseTO.getUserInstanceId());
		assertEquals("034a2197-69d7-4369-944b-a8803f448756",
				historyResponseTO.getUserInstanceName());
		assertEquals("2016-12-07 12:00:00", historyResponseTO.getLastUpdated());
		assertEquals(Operation.TURN_ON, historyResponseTO.getOperation());

		historyResponseTO = response[1];
		assertEquals(new Long(6), historyResponseTO.getId());
		assertEquals(new Long(2), historyResponseTO.getUserId());
		assertEquals("userTwo", historyResponseTO.getUserName());
		assertEquals(new Long(5), historyResponseTO.getUserInstanceId());
		assertEquals("034a2197-69d7-4369-944b-a8803f448756",
				historyResponseTO.getUserInstanceName());
		assertEquals("2016-12-07 13:01:00", historyResponseTO.getLastUpdated());
		assertEquals(Operation.TURN_OFF, historyResponseTO.getOperation());
	}

}