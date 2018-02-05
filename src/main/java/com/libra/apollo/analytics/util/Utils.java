package com.libra.apollo.analytics.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Utils {

	public static String DEFAULT_APOLLO_ZONE_ID = "Europe/London";
	
	
	/**
	 * The methods calculates the previous working day. It only recognize Saturday
	 * and Sunday as non -working days.
	 * 
	 * @param date
	 *            Date as starting point for the calculation, cannot be null
	 * @return The previous working day
	 */
	public static LocalDate getPreviousWorkingDay(LocalDate date) {
		DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
		switch (dayOfWeek) {
		case MONDAY:
			return date.minus(3, ChronoUnit.DAYS);
		case SUNDAY:
			return date.minus(2, ChronoUnit.DAYS);
		default:
			return date.minus(1, ChronoUnit.DAYS);

		}
	}

}
