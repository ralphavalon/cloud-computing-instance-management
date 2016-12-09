package com.computing.cloud.service;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.computing.cloud.AbstractTest;
import com.computing.cloud.domain.History;
import com.computing.cloud.domain.Instance;
import com.computing.cloud.domain.OperatingSystem;
import com.computing.cloud.domain.UserInstance;
import com.computing.cloud.enums.InstanceStatus;
import com.computing.cloud.enums.Operation;
import com.computing.cloud.utils.DateHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode=ClassMode.BEFORE_EACH_TEST_METHOD)
public class HistoryServiceTest extends AbstractTest {
	
	@Autowired
	private HistoryService service;
	
	private History historyOne;
	private History historyTwo;
	private History historyThree;
	private History historyFour;
	private History historyFive;
	private History historySix;
	final UserInstance userOneInstance = userOneInstance(InstanceStatus.OFF, small, windowsServer2003);
	final UserInstance userTwoInstance = userTwoInstance(InstanceStatus.OFF, small, windowsServer2003);
	
	@Before
	public void init() {
		
		Calendar firstOn = DateHelper.getCalendar(01, Calendar.DECEMBER, 2016, 10, 00);
		Calendar firstOff = DateHelper.getCalendar(01, Calendar.DECEMBER, 2016, 11, 00);
		Calendar secondOn = DateHelper.getCalendar(12, Calendar.DECEMBER, 2016, 10, 00);
		Calendar secondOff = DateHelper.getCalendar(13, Calendar.DECEMBER, 2016, 10, 00);
		Calendar thirdOn = DateHelper.getCalendar(12, Calendar.DECEMBER, 2016, 11, 00);
		Calendar thirdOff = DateHelper.getCalendar(12, Calendar.DECEMBER, 2016, 12, 01);
		
		historyOne = History.builder()
				.user(userOne)
				.userInstance(userOneInstance)
				.lastUpdated(firstOn)
				.operation(Operation.TURN_ON)
				.build();
		
		historyTwo = History.builder()
				.user(userOne)
				.userInstance(userOneInstance)
				.lastUpdated(firstOff)
				.operation(Operation.TURN_OFF)
				.build();
		
		historyThree = History.builder()
				.user(userOne)
				.userInstance(userOneInstance)
				.lastUpdated(secondOn)
				.operation(Operation.TURN_ON)
				.build();
		
		historyFour = History.builder()
				.user(userOne)
				.userInstance(userOneInstance)
				.lastUpdated(secondOff)
				.operation(Operation.TURN_ON)
				.build();
		
		historyFive = History.builder()
				.user(userTwo)
				.userInstance(userTwoInstance)
				.lastUpdated(thirdOn)
				.operation(Operation.TURN_ON)
				.build();
		
		historySix = History.builder()
				.user(userTwo)
				.userInstance(userTwoInstance)
				.lastUpdated(thirdOff)
				.operation(Operation.TURN_OFF)
				.build();
		
		service.saveHistory(historyOne);
		service.saveHistory(historyTwo);
		service.saveHistory(historyThree);
		service.saveHistory(historyFour);
		service.saveHistory(historyFive);
		service.saveHistory(historySix);
		
	}

//	@Test
//	public void shouldNotRetrieveOtherUserHistories() {
//		final List<History> historiesByUser = service.getHistoriesByUser(userTwo);
//		
//		assertEquals(2, historiesByUser.size());
//	}
//
//	@Test
//	public void shouldRetrieveUserHistory() {
//		final List<History> historiesByUser = service.getHistoriesByUser(userOne);
//		
//		assertEquals(4, historiesByUser.size());
//	}
//	
//	@Test
//	public void shouldRetrieveUserInstanceHistory() {
//		final List<History> historiesByUser = service.getHistoriesByUserInstance(userOneInstance);
//		
//		assertEquals(4, historiesByUser.size());
//	}
	
	@Test
	public void shouldRetrieveHistoryByPeriod() {
		final Date startDate = DateHelper.getCalendar(12, Calendar.DECEMBER, 2016, 10, 00).getTime();
		final Date endDate = DateHelper.getCalendar(13, Calendar.DECEMBER, 2016, 10, 00).getTime();
		List<History> histories = service.getHistoriesByUserInPeriod(userOne, startDate, endDate);
		assertEquals(2, histories.size());
		
		assertEquals(historyThree, histories.get(0));
		assertEquals(historyFour, histories.get(1));
		
	}
	
	private UserInstance userOneInstance(InstanceStatus status, Instance instance, OperatingSystem os) {
		return new UserInstance(userOne, status, instance, os);
	}
	
	private UserInstance userTwoInstance(InstanceStatus status, Instance instance, OperatingSystem os) {
		return new UserInstance(userTwo, status, instance, os);
	}
	
}