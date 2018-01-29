package com.libra.apollo.analytics.service;

import java.time.LocalDate;
import java.util.Date;

/*
 * This is not really a service as we don't have an underlying Holidays calendar implementation
 */
public interface CalendarService {

	Date getClosestMaxStampDate();
	LocalDate getClosestClosedLocalDate();
}
