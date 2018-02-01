package com.libra.apollo.analytics.engine.commands;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.libra.apollo.analytics.engine.context.AnalyticsContext;
import com.libra.apollo.analytics.engine.context.PortfolioScreenerContext;
import com.libra.apollo.analytics.service.CalendarService;

public class CalendarCommand implements Command {

	private final static Integer OFF_SET_DATE = -3;
	
	private final AnalyticsContext context;
	
	
	public CalendarCommand(final AnalyticsContext context) {
		super();
		this.context = context;
	}


	@Override
	public void execute() {
		
		final PortfolioScreenerContext screenerContext = (PortfolioScreenerContext)context;
		
		final CalendarService calendarService = screenerContext.getCalendarService();
		
		final Collection<Long> stockIds =screenerContext.getStockIds();
		
		final Date maxDate = calendarService.getMaxStampDateForStockIndicators();
		
		final Calendar maxDateCalendar = Calendar.getInstance();
		maxDateCalendar.setTime(maxDate);
		
		maxDateCalendar.add(Calendar.DATE, OFF_SET_DATE);
		
		Date greaterThanStampDate = new Date(maxDateCalendar.getTimeInMillis());
		
		Map<Date,Set<Long>> dateStocks =  calendarService.getMaxStampDateForStockIds(stockIds, greaterThanStampDate );
		
		screenerContext.setMaxStampDatePerStock(dateStocks);
		
		
	}

}
