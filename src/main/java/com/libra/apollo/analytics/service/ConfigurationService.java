package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.List;
import com.libra.apollo.analytics.engine.request.AnalyticsConfigurationRequest;
import com.libra.apollo.analytics.entity.AnalyticsType;
import com.libra.apollo.analytics.entity.AnalyticsView;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.FieldParameter;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.QueryParameter;
import com.libra.apollo.analytics.entity.RunType;

public interface ConfigurationService {

	public List<ApolloAnalytics> getAnalytics();
	public ApolloAnalytics getAnalyticsById(Long id);
	public List<ApolloAnalytics> getAnalyticsByName(String name);
	public List<String> getAnalyticsNames();
	public List<ApolloAnalytics> getAnalyticsByType(AnalyticsType type);
	public List<ApolloAnalytics> getAnalyticsByRunType(RunType type);
	
	public List<AnalyticsView> getAnalyticsViewsByAnalyticId(ApolloAnalytics apolloAnalytics);
	public AnalyticsView getAnalyticsViewByAnalyticIdAndViewId(ApolloAnalytics apolloAnalytics, Long viewId);
	
	public List<ApolloAnalytics> getAnalyticsViews(AnalyticsConfigurationRequest request);
	public List<InvestmentStyle> getInvestmentStylesByView(AnalyticsView analyticsView);
	public InvestmentStyle getInvestmentStyleById(Long investmentStyleId);
	
	public Collection<QueryParameter> getInvestmentStyleQueryParameters(Long investmentStyleId);
	
	public Collection<FieldParameter> getInvestmentStyleFieldParameters(Long investmentStyleId);
	


}
