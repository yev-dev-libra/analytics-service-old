package com.libra.apollo.analytics.engine.command;

import com.libra.apollo.analytics.engine.context.AnalyticsContext;
import com.libra.apollo.analytics.engine.core.Operation;

public class DelegatorFactory {

	public static Delegator getCommandDelegator(AnalyticsContext analyticsContext) {

		final Operation operation = analyticsContext.getOperation();

		Delegator delegator = null;

		switch (operation) {
		
		case SCREEN_FOR_PORTFOLIOS: {
			Delegator portfolioScreenerDelegator = new ScreenerDelegator(analyticsContext);
			final Command portfolioEnrichment = new PortfolioEnrichmentCommand(analyticsContext);
			final Command calendarEnrichmentCommand = new CalendarCommand(analyticsContext);
			final Command screenerCommand = new ScreenerCommand(analyticsContext);
			
			portfolioScreenerDelegator.add(portfolioEnrichment);
			portfolioScreenerDelegator.add(calendarEnrichmentCommand);
			portfolioScreenerDelegator.add(screenerCommand);
			
			delegator = portfolioScreenerDelegator;
		}
			break;
		case SCREEN_FOR_STOCKS: {
			throw new UnsupportedOperationException();
		}
		
		}

		return delegator;
	}
}
