package com.libra.apollo.analytics.engine.result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.libra.apollo.analytics.engine.Megerable;
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;

public class ScreenerResult implements AnalyticsResult, Megerable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 468624570135610833L;
	
	private Stream<LibraStockIndicator> streamIndicators = Stream.empty() ;
	private final List<LibraStockIndicator> indicators;
	
	
	public ScreenerResult(List<LibraStockIndicator> indicators) {
		super();
		this.indicators = indicators;
	}

	private AnalyticsType type = AnalyticsType.APOLLO_SCREENER;

	private RunType runType = RunType.MANUAL;

	@Override
	public AnalyticsType getAnalyticsType() {
		return type;
	}

	@Override
	public RunType getRunType() {
		return runType;
	}
	
	//TODO return streams from Spring Data
	public void of(List<LibraStockIndicator> indicators) {
		
	}

}
