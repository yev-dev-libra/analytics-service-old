package com.libra.apollo.analytics.engine.response;

import java.util.ArrayList;
import java.util.List;

import com.libra.apollo.analytics.entity.InvestmentStyle;

public class AnalyticsConfigurationResponse implements AnalyticsResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6327485650244147942L;

	private List<InvestmentStyle> investmentStyles = new ArrayList<>();
}
