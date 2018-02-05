package com.libra.apollo.analytics.engine.command;

import java.util.Collection;
import java.util.Map;

import com.libra.apollo.analytics.engine.context.AnalyticsContext;
import com.libra.apollo.analytics.engine.context.PortfolioScreenerContext;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.service.PortfolioService;

public class PortfolioEnrichmentCommand implements Command {

	final private AnalyticsContext context;
	
	
	public PortfolioEnrichmentCommand(final AnalyticsContext context) {
		super();
		this.context = context;
	}


	@Override
	public void execute() {
		final PortfolioScreenerContext screenerContext = (PortfolioScreenerContext)context;
		
		final ScreenerRequest request = screenerContext.getRequest();
		
		final Collection<Long> portfolioIds = request.getPortfolioIds();
		
		final PortfolioService portfolioService = screenerContext.getPortfolioService();
		
		final Map<Long,Collection<Long>> stockPortfolios = portfolioService.getStockIdsWithPortfolios(portfolioIds);
		
		screenerContext.setStockPortfolios(stockPortfolios);
	}

}
