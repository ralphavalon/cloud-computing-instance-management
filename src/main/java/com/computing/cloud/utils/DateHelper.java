package com.computing.cloud.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class DateHelper {

    private DateHelper() {}

    public static boolean isGreaterEqualThan(Date firstDate, Date secondDate) {
        if(DateUtils.isSameInstant(firstDate, secondDate) || firstDate.after(secondDate)){
            return true;
        }
        return false;
    }

    public static boolean isLessEqualThan(Date firstDate, Date secondDate) {
        if(DateUtils.isSameInstant(firstDate, secondDate) || firstDate.before(secondDate)){
            return true;
        }
        return false;
    }
    
    public static Calendar getCalendar(int day, int month, int year, int hour, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hour, min, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}
    
    public static String fromDateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static boolean isBetween(Date mainDate, Date greaterEqualDate, Date lessEqualDate) {
        return isGreaterEqualThan(mainDate, greaterEqualDate) && isLessEqualThan(mainDate, lessEqualDate);
    }
    
private static String DEFAULT_FORMAT = "dd/MM/yyyy";
	
	public static Optional<String> format(DateTime date) {
		try {
			return Optional.of(date.toString(DEFAULT_FORMAT));
		} catch( Exception e ) {
			return Optional.empty();
		}
	}
	
	public static Optional<String> format(DateTime date, String format) {
		try {
			return Optional.of(date.toString(format));
		} catch( Exception e ) {
			return Optional.empty();
		}
	}
	
	public static Optional<DateTime> toDate(String date) {
		try {
			return Optional.of(DateTimeFormat.forPattern(DEFAULT_FORMAT).parseDateTime(date));
		} catch( Exception e ) {
			return Optional.empty();
		}
	}
	public static Optional<DateTime> toDate(String date, String format) {
		try {
			return Optional.of(DateTimeFormat.forPattern(format).parseDateTime(date));
		} catch( Exception e ) {
			return Optional.empty();
		}
	}

}