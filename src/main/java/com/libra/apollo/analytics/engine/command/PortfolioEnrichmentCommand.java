package com.libra.apollo.analytics.engine.command;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.libra.apollo.analytics.engine.context.PortfolioScreenerContext;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.service.PortfolioService;

public class PortfolioEnrichmentCommand implements Command {

	final private PortfolioScreenerContext context;
	
	
	public PortfolioEnrichmentCommand(final PortfolioScreenerContext context) {
		super();
		this.context = context;
	}


	@Override
	public void execute() {
		final ScreenerRequest request = context.getRequest();
		
		final Collection<Long> portfolioIds = request.getPortfolioIds();
		
		final PortfolioService portfolioService = context.getPortfolioService();
		
		final Map<Long,Collection<Long>> stockPortfolios = portfolioService.getStockIdsWithPortfolios(portfolioIds);
		
		context.setStockPortfolios(stockPortfolios);
	}

}
