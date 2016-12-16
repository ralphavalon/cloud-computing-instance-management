package com.computing.cloud.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
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
	
	@Test
	public void shouldFormatDatetimeToString() {
		DateTime date = new DateTime(2015, 11, 30, 0, 0);
		assertEquals( "30/11/2015", DateHelper.format(date).get() );
	}
	
	@Test
	public void shouldNotFormatInvalidDate() {
		assertFalse( DateHelper.format(null).isPresent() );
	}
	
	@Test
	public void shouldParseStringToDate() {
		assertEquals( new DateTime(2015,11,30,0,0,0), DateHelper.toDate("30/11/2015").get() );
	}
	
	@Test
	public void shouldNotParseEmptyStringToDate() {
		assertFalse( DateHelper.toDate("").isPresent() );
	}
	
	@Test
	public void shouldFormatGivenDateFormat() {
		DateTime date = new DateTime(2015, 11, 30, 14, 0, 39);
		assertEquals( "30/11/2015 14:00:39", DateHelper.format(date, "dd/MM/yyyy HH:mm:ss").get() );
	}
	
	@Test
	public void shouldParseGivenDateFormat() {
		String date = "20/10/2016 14:50:39";
		assertEquals( new DateTime(2016,10,20,14,50,39), DateHelper.toDate(date, "dd/MM/yyyy HH:mm:ss").get() );
	}

}