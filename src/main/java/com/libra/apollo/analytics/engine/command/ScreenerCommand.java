package com.libra.apollo.analytics.engine.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.core.task.AsyncTaskExecutor;

import com.libra.apollo.analytics.engine.context.PortfolioScreenerContext;
import com.libra.apollo.analytics.engine.converter.AnalyticsConveterConveter;
import com.libra.apollo.analytics.engine.converter.Converter;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
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
		final Map<Long,List<Long>> stockPortfolios = context.getStockPortfolios();
		final Date runDate = context.getRequest().getRunDate();
		
//		int batchSize = Integer.valueOf(context.getProperties().get(PropertyName.BATCH_SIZE));
		
		
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
		
		List<ValueDataFieldType> queryParams = params.stream().map(f -> f.getFieldType()).collect(Collectors.toList());
		

		
		Collection<Long> stockIds = stockPortfolios.keySet();
		
		ScreenerResult results = new ScreenerResult.ScreenerResultBuilder()
				.setParameters(queryParams)
				.setRequestedFields(requestedFields)
				.build();
		
		List<Tuple> screeningResults = analyticsService.getScreeningResults(stockIds, params, requestedFields, runDate);
		
		Converter<List<Tuple>, Collection<List<?>>> convertedValues = AnalyticsConveterConveter.fromTupleToList(requestedFields);
		
		Collection<List<?>> convertedResults =  convertedValues.convert(screeningResults);
		
		
		if(results.isMergeEnabled()) {
			results.merge(convertedResults);
		}

	}

}
