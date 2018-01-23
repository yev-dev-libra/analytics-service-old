package com.libra.apollo.analytics.engine.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		
		
		final List<QueryParameter> params = configService.getInvestmentStylesQueryParameters(investmentStyleId);
		//TODO: request for queryResultsParams

		
		//TODO: persist in the database
		List<ValueDataFieldType> requestParams = Arrays.asList(
						ValueDataFieldType.MAX_STAMP_DATE, 
						ValueDataFieldType.STAR_RATING,
						ValueDataFieldType.FAIR_VALUE,
						ValueDataFieldType.INTRINSIC_VALUE,
						ValueDataFieldType.STOCK_ID
						);
		
		List<ValueDataFieldType> queryParams = params.stream().map(f -> f.getFieldType()).collect(Collectors.toList());
		

		ScreenerResult results = new ScreenerResult.ScreenerResultBuilder()
				.setParameters(queryParams)
				.setRequestedFields(requestParams)
				.build();
		
		Collection<Long> stockIds = stockPortfolios.keySet();
		
		analyticsService.getScreeningResults(stockIds, params, results, runDate);

	}

}
