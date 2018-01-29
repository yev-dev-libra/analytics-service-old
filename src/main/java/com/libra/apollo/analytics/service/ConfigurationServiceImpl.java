package com.libra.apollo.analytics.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.libra.apollo.analytics.engine.request.AnalyticsConfigurationRequest;
import com.libra.apollo.analytics.entity.AnalyticsView;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.QueryParameter;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;
import com.libra.apollo.analytics.repository.AnalyticsRepository;
import com.libra.apollo.analytics.repository.AnalyticsViewRepository;
import com.libra.apollo.analytics.repository.InvestmentStyleRepository;

@Service
@Transactional(readOnly = true)
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	private InvestmentStyleRepository investmentStyleRepository;

	@Autowired
	private AnalyticsRepository analyticsRepository;
	
	@Autowired
	private AnalyticsViewRepository analyticsViewRepository;
	
	

	@Override
	public List<InvestmentStyle> getInvestmentStylesByView(AnalyticsView analyticsView) {
		return investmentStyleRepository.findAllByView(analyticsView);
	}
	
	@Override
	public List<String> getAnalyticsNames() {
		return analyticsRepository.getAnalyticsNames();
	}

	@Override
	public List<ApolloAnalytics> getAnalytics() {
		return analyticsRepository.findAll();
	}
	
	@Override
	public ApolloAnalytics getAnalyticsById(Long id) {
		return analyticsRepository.findById(id);
	}
	
	@Override
	public List<ApolloAnalytics> getAnalyticsByName(String name) {
		return analyticsRepository.findAllByName(name);
	}
	
	@Override
	public List<ApolloAnalytics> getAnalyticsByType(AnalyticsType type) {
		return analyticsRepository.findAllByType(type);
	}
	
	@Override
	public List<ApolloAnalytics> getAnalyticsByRunType(RunType runType) {
		return analyticsRepository.findAllByRunType(runType);
	}
	
	@Override
	public List<AnalyticsView> getAnalyticsViewsByAnalyticId(ApolloAnalytics apolloAnalytics) {
		return analyticsViewRepository.findAllByAnalytics(apolloAnalytics);
	}
	
	@Override
	public AnalyticsView getAnalyticsViewByAnalyticIdAndViewId(ApolloAnalytics apolloAnalytics, Long viewId) {
		return analyticsViewRepository.findByAnalyticsAndId(apolloAnalytics, viewId);
	}
	
	@Override
	public List<ApolloAnalytics> getAnalyticsViews(AnalyticsConfigurationRequest request) {

		final AnalyticsType type = request.getAnalyticsType();
		final RunType runType = request.getRunType();

		List<ApolloAnalytics> analytics = analyticsRepository.findAllByTypeAndByRunType(type, runType);

		return analytics;
	}

	@Override
	public InvestmentStyle getInvestmentStyleById(Long investmentStyleId) {
		return investmentStyleRepository.getOne(investmentStyleId);
	}

	@Override
	public List<QueryParameter> getInvestmentStylesQueryParameters(Long investmentStyleId) {
		return investmentStyleRepository.findQueryParametersById(investmentStyleId);
	}


}
