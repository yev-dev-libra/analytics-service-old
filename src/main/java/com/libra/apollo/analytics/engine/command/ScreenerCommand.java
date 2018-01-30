package com.libra.apollo.analytics.engine.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.core.task.AsyncTaskExecutor;

import com.libra.apollo.analytics.engine.context.AnalyticsContext;
import com.libra.apollo.analytics.engine.context.PortfolioScreenerContext;
import com.libra.apollo.analytics.engine.converter.AnalyticsConveters;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.QueryParameter;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.ConfigurationService;

public class ScreenerCommand implements Command {

	final private AnalyticsContext context;
	
	
	public ScreenerCommand(AnalyticsContext context) {
		super();
		this.context = context;
	}


	@Override
	public void execute() {
		
		final PortfolioScreenerContext screenerContext = (PortfolioScreenerContext)context;
		
		final ConfigurationService configService = screenerContext.getConfigurationService();
		final AnalyticsService analyticsService = screenerContext.getAnalyticsService();
		final AsyncTaskExecutor executor = screenerContext.getExecutorService();
		
		final Long investmentStyleId = screenerContext.getRequest().getInvestmentStyleId();
		final Map<Long,Collection<Long>> stockPortfolios = screenerContext.getStockPortfolios();
		
		
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
		
		
		final List<Tuple> tupleResults = analyticsService.getScreeningResults(stockIds, params, requestedFields);
		 
		
		final List<EnumMap<ValueDataFieldType,?>> valueResults  = AnalyticsConveters.fromTupleToValuesDataFieldMap(requestedFields).convert(tupleResults);
		
		ScreenerResult result = null;
		
		if(screenerContext.isResultMergeable()) {
			result = new ScreenerResult.ScreenerResultBuilder()
					.setParameters(queryParams)
					.setRequestedFields(requestedFields)
					.setInvestmentStyle(investmentStyle)
					.setPortfolioIds(screenerContext.getPortfolioIds())
					.build();
			
			result.addValueResults(valueResults);
		} else {
			
			result = new ScreenerResult.ScreenerResultBuilder()
					.setParameters(queryParams)
					.setRequestedFields(requestedFields)
					.setInvestmentStyle(investmentStyle)
					.setResults(valueResults)
					.setPortfolioIds(screenerContext.getPortfolioIds())
					.build();
		}
		
		screenerContext.setResult(result);
	}

}
