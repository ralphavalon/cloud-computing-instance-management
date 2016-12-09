package com.computing.cloud.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateHelperTest {
	
	@Test
	public void shouldCheckIfDateIsBetweenToDatesTest() throws Exception {
		Date mainDate = DateHelper.getCalendar(12, Calendar.DECEMBER, 2016, 10, 00).getTime();
		Date startDate = DateHelper.getCalendar(12, Calendar.DECEMBER, 2016, 10, 00).getTime();
		Date endDate = DateHelper.getCalendar(13, Calendar.DECEMBER, 2016, 10, 00).getTime();
		assertTrue(DateHelper.isBetween(mainDate, startDate, endDate));
		
		mainDate = DateHelper.getCalendar(12, Calendar.DECEMBER, 2016, 9, 59).getTime();
		assertFalse(DateHelper.isGreaterEqualThan(mainDate, startDate));
		assertTrue(DateHelper.isLessEqualThan(mainDate, endDate));
		assertFalse(DateHelper.isBetween(mainDate, startDate, endDate));
		
		mainDate = DateHelper.getCalendar(12, Calendar.DECEMBER, 2016, 10, 01).getTime();
		assertTrue(DateHelper.isBetween(mainDate, startDate, endDate));
		
	}

}