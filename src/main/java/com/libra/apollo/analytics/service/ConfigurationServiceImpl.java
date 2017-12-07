package com.libra.apollo.analytics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.engine.Operation;
import com.libra.apollo.analytics.engine.request.AnalyticsViewConfigurationRequest;
import com.libra.apollo.analytics.entity.AnalyticsView;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;
import com.libra.apollo.analytics.repository.AnalyticsRepository;
import com.libra.apollo.analytics.repository.AnalyticsViewRepository;
import com.libra.apollo.analytics.repository.InvestmentStyleRepository;

@Service
@Transactional(readOnly=true)
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired private InvestmentStyleRepository investmentStyleRepository;
	
	@Autowired private AnalyticsViewRepository analyticsViewRepository;
	
	@Autowired private AnalyticsRepository analyticsRepository;
	
	@Override
	public List<InvestmentStyle> getInvestmentStyles(Long analyticsViewId) {
		return investmentStyleRepository.findAllByView(analyticsViewId);
	}

	@Override
	public List<AnalyticsView> getInvestmentStyles(AnalyticsViewConfigurationRequest request) {
		final AnalyticsType analyticsType = request.getAnalyticsType();
		final Operation operation = request.getOperation();
		final RunType runType = request.getRunType();
		
//		analyticsRepository.findByFirst(type);
		
		return analyticsViewRepository.findAllByAnalytics();
	}

}
