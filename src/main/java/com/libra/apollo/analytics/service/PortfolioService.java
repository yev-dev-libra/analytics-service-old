package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface PortfolioService {

	Collection<Long> getStockIdForPortfolios(Collection<Long> portfolioIds);
	Map<Long,List<Long>> getStockIdsWithPortfolios(Collection<Long> portfolioIds);
}
