package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/*
 * This is not really a service as we don't have an underlying Holidays calendar implementation
 */
public interface CalendarService {

	Date getMaxStampDateForStockIndicators();
	Date getMaxStampDateForStock(Long stockId);
	Map<Date,Set<Long>> getMaxStampDateForStockIds(Collection<Long> stockIds, Date greaterThanStampDate);
	Map<Long,Date> getStockDateForStockIdAndDate(Collection<Long> stockIds, Date greaterThanStampDate);
	Map<Date,Long> getDateStockForStockIdAndDate(Collection<Long> stockIds, Date greaterThanStampDate);
}
