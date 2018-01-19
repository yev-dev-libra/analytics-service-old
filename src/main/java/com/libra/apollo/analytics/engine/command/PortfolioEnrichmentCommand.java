package com.libra.apollo.analytics.engine.command;

import java.util.List;
import java.util.Map;

import com.libra.apollo.analytics.engine.context.ScreenerContext;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.service.PortfolioService;

public class PortfolioEnrichmentCommand implements Command {

	final private ScreenerContext context;
	
	
	public PortfolioEnrichmentCommand(final ScreenerContext context) {
		super();
		this.context = context;
	}


	@Override
	public void execute() {
		final ScreenerRequest request = context.getRequest();
		
		final List<Long> portfolioIds = request.getPortfolioIds();
		
		final PortfolioService portfolioService = context.getPortfolioService();
		
		final Map<Long,List<Long>> stockPortfolios = portfolioService.getStockIdsWithPortfolios(portfolioIds);
		
		context.setStockPortfolios(stockPortfolios);
	}

}
