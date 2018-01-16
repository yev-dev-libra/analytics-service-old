package com.libra.apollo.analytics.engine.command;

import java.util.List;
import java.util.Map;

import com.libra.apollo.analytics.engine.context.ScreeningContext;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.service.PortfolioService;

public class PortfolioEnrichmentCommand implements Command {

	final private ScreeningContext context;
	final private Command next;
	
	
	public PortfolioEnrichmentCommand(final ScreeningContext context, final Command next) {
		super();
		this.context = context;
		this.next = next;
	}

	@Override
	public Command next() {
		return next;
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
