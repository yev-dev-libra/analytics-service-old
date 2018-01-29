package com.libra.apollo.analytics.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.apollo.analytics.repository.LibraStockIndicatorRepository;
import com.libra.apollo.analytics.utils.Utils;

@Service
public class CalendarServiceImpl implements CalendarService {

	
	@Autowired
	private LibraStockIndicatorRepository libraStockIndicatorRepository;
	
	@Override
	public Date getClosestMaxStampDate() {
		
//		LocalDate localDate = getClosestClosedLocalDate();
//		
//		ZoneId zoneId = ZoneId.of(Utils.DEFAULT_APOLLO_ZONE_ID);
//		
//		Instant instant = localDate.atStartOfDay(zoneId).toInstant();
//		
//		Date date = Date.from(instant);
		
		Date date = libraStockIndicatorRepository.maxDate();
		
		return date;
	}

	@Override
	public LocalDate getClosestClosedLocalDate() {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1L);
		return Utils.getPreviousWorkingDay(yesterday);
	}

	
}
