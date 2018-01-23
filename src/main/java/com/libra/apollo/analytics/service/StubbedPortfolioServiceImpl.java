package com.libra.apollo.analytics.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class StubbedPortfolioServiceImpl implements PortfolioService {

	@Override
	public Collection<Long> getStockIdForPortfolios(Collection<Long> portfolioIds) {
		if(portfolioIds.contains(1L)) {
			return Arrays.asList(1L);
		}
		else
			return Collections.emptyList();
	}

	@Override
	public Map<Long, List<Long>> getStockIdsWithPortfolios(Collection<Long> portfolioIds) {
		if(portfolioIds.contains(1L)) {
			
			Map<Long,List<Long>> values = new HashMap<>();
			values.put(1L, Arrays.asList(1L,2L));
			
			return values;
		}
		else
			return Collections.emptyMap();
	}

}
