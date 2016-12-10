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
import org.springframework.transaction.annotation.Transactional;

import com.computing.cloud.AbstractTest;
import com.computing.cloud.domain.History;
import com.computing.cloud.enums.Operation;
import com.computing.cloud.utils.DateHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode=ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class HistoryServiceTest extends AbstractTest {
	
	@Autowired
	private HistoryService service;
	
	private History historyThree;
	private History historyFour;
	
	@Before
	public void init() {
		
		Calendar secondOn = DateHelper.getCalendar(12, Calendar.DECEMBER, 2016, 10, 00);
		Calendar secondOff = DateHelper.getCalendar(13, Calendar.DECEMBER, 2016, 10, 00);
		
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
		
	}

	@Test
	public void shouldNotRetrieveOtherUserHistories() {
		final List<History> historiesByUser = service.getHistoriesByUser(userTwo);
		
		assertEquals(2, historiesByUser.size());
	}

	@Test
	public void shouldRetrieveUserHistory() {
		final List<History> historiesByUser = service.getHistoriesByUser(userOne);
		
		assertEquals(4, historiesByUser.size());
	}
	
	@Test
	public void shouldRetrieveUserInstanceHistory() {
		final List<History> historiesByUserInstance = service.getHistoriesByUserInstance(userOneInstance);
		
		assertEquals(4, historiesByUserInstance.size());
	}
	
	@Test
	public void shouldRetrieveHistoryByPeriod() {
		service.saveHistory(historyThree);
		service.saveHistory(historyFour);
		
		final Date startDate = DateHelper.getCalendar(12, Calendar.DECEMBER, 2016, 10, 00).getTime();
		final Date endDate = DateHelper.getCalendar(13, Calendar.DECEMBER, 2016, 10, 00).getTime();
		List<History> histories = service.getHistoriesByUserInPeriod(userOne, startDate, endDate);
		assertEquals(2, histories.size());
		
		assertEquals(historyThree, histories.get(0));
		assertEquals(historyFour, histories.get(1));
		
	}
	
}