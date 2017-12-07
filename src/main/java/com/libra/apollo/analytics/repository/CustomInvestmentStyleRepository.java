package com.libra.apollo.analytics.repository;

import java.util.List;

import com.libra.apollo.analytics.engine.request.AnalyticsViewConfigurationRequest;
import com.libra.apollo.analytics.entity.InvestmentStyle;

public interface CustomInvestmentStyleRepository {

	
	List<InvestmentStyle> queryByConfigurationAnalyticsRequest(AnalyticsViewConfigurationRequest request);
}
