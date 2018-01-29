package com.libra.apollo.analytics.engine.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.core.task.AsyncTaskExecutor;

import com.libra.apollo.analytics.engine.context.PortfolioScreenerContext;
import com.libra.apollo.analytics.engine.converter.AnalyticsConveters;
import com.libra.apollo.analytics.engine.converter.Converter;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.QueryParameter;
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
		final AsyncTaskExecutor executor = context.getExecutorService();
		
		final Long investmentStyleId = context.getRequest().getInvestmentStyleId();
		final Map<Long,Collection<Long>> stockPortfolios = context.getStockPortfolios();
		
		
//		int batchSize = Integer.valueOf(context.getProperties().get(PropertyName.BATCH_SIZE));
		
		final InvestmentStyle investmentStyle = configService.getInvestmentStyleById(investmentStyleId);
		
		final List<QueryParameter> params = configService.getInvestmentStylesQueryParameters(investmentStyleId);
		//TODO: request for queryResultsParams

		
		//TODO: persist in the database
		List<ValueDataFieldType> requestedFields = Arrays.asList(
						ValueDataFieldType.MAX_STAMP_DATE, 
						ValueDataFieldType.STAR_RATING,
						ValueDataFieldType.FAIR_VALUE,
						ValueDataFieldType.INTRINSIC_VALUE,
						ValueDataFieldType.STOCK_ID
						);
		
		final List<ValueDataFieldType> queryParams = params.stream().map(f -> f.getFieldType()).collect(Collectors.toList());
		

		
		final Collection<Long> stockIds = stockPortfolios.keySet();
		
		
		final List<Tuple> screeningResults = analyticsService.getScreeningResults(stockIds, params, requestedFields);
		 
		
		final Collection<List<?>> convertedResults  = AnalyticsConveters.fromTupleToList(requestedFields).convert(screeningResults);
		
		ScreenerResult result = null;
		
		if(context.isResultMergeable()) {
			result = new ScreenerResult.ScreenerResultBuilder()
					.setParameters(queryParams)
					.setRequestedFields(requestedFields)
					.setInvestmentStyle(investmentStyle)
					.setPortfolioIds(context.getPortfolioIds())
					.build();
			
			result.addResults(convertedResults);
		} else {
			
			result = new ScreenerResult.ScreenerResultBuilder()
					.setParameters(queryParams)
					.setRequestedFields(requestedFields)
					.setInvestmentStyle(investmentStyle)
					.setResults(convertedResults)
					.setPortfolioIds(context.getPortfolioIds())
					.build();
		}
		
		this.context.setResult(result);
	}

}
