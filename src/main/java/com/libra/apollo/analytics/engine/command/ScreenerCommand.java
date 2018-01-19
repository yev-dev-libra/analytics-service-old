package com.libra.apollo.analytics.engine.command;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.libra.apollo.analytics.engine.context.PortfolioScreenerContext;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.QueryParameter;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.ConfigurationService;

public class ScreenerCommand implements Command {

	final private PortfolioScreenerContext context;
	
	
	public ScreenerCommand(PortfolioScreenerContext context) {
		super();
		this.context = context;
	}


	@Override
	public void execute() {
		
		final ConfigurationService configService = context.getConfigurationService();
		final AnalyticsService analyticsService = context.getAnalyticsService();
		
		final Long investmentStyleId = context.getRequest().getInvestmentStyleId();
		final Map<Long,List<Long>> stockPortfolios = context.getStockPortfolios();
		final Date runDate = context.getRequest().getRunDate();
		
		
		final List<QueryParameter> queryParams = configService.getInvestmentStylesQueryParameters(investmentStyleId);
		//TODO: request for queryResultsParams

		ValueDataFieldType[] paramFields = new ValueDataFieldType[queryParams.size()];
		
		for (int i = 0; i < queryParams.size(); i++) {
			paramFields[i] = queryParams.get(i).getFieldType();
			
		}
		
		//TODO: persist in the database
		ValueDataFieldType[] queryResultsParams = {
						ValueDataFieldType.MAX_STAMP_DATE, 
						ValueDataFieldType.STAR_RATING,
						ValueDataFieldType.FAIR_VALUE,
						ValueDataFieldType.INTRINSIC_VALUE,
						ValueDataFieldType.STOCK_ID
				};
		
		
		ScreenerResult result = new ScreenerResult(queryResultsParams, paramFields);

		Collection<Long> stockIds = stockPortfolios.keySet();
		
		analyticsService.getScreeningResults(stockIds, queryParams, result, runDate);

	}

}
