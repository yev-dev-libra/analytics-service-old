package com.libra.apollo.analytics.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.libra.apollo.analytics.dto.PortfolioScreenerResultDTO;
import com.libra.apollo.analytics.dto.StockScreenerResultDTO;
import com.libra.apollo.analytics.engine.command.Command;
import com.libra.apollo.analytics.engine.command.Delegator;
import com.libra.apollo.analytics.engine.command.DelegatorFactory;
import com.libra.apollo.analytics.engine.command.PortfolioEnrichmentCommand;
import com.libra.apollo.analytics.engine.command.ScreenerCommand;
import com.libra.apollo.analytics.engine.command.ScreenerDelegator;
import com.libra.apollo.analytics.engine.context.AnalyticsContext;
import com.libra.apollo.analytics.engine.context.AnalyticsContextFactory;
import com.libra.apollo.analytics.engine.context.PortfolioScreenerContext;
import com.libra.apollo.analytics.engine.converter.AnalyticsConveters;
import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.CalendarService;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.service.PortfolioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/screen")
@Api(value = "Screen", description = "Analytics Screener Api")
public class AnalyticsScreenerRestController {
	
	protected Logger logger = Logger.getLogger(AnalyticsScreenerRestController.class.getName());
	
	@Autowired
	private AnalyticsService analyticsService;
	
	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	private PortfolioService portfolioService;
	
	@Autowired
	private AsyncTaskExecutor executorService;
	
	@Autowired
	private CalendarService calendarService;
	
	
	@RequestMapping(value = "/investment-style/{styleId}/portfolios/{portfolioIds}", method = RequestMethod.GET)
	@ApiOperation(value = "Screen for results given a list of portfolios")
	public ResponseEntity<PortfolioScreenerResultDTO> screenForPortfolios(@PathVariable("styleId") Long styleId, @PathVariable("portfolioIds") List<Long> portfolioIds){
		
		final Map<String,String> properties = new HashMap<>();
		
		final ScreenerRequest request = ScreenerRequest.of(styleId, portfolioIds);
		
		final AnalyticsContext analyticsContext =  AnalyticsContextFactory.getContext(analyticsService, configurationService, portfolioService, executorService, calendarService, properties, request);
		
		final Delegator delegator = DelegatorFactory.getCommandDelegator(analyticsContext);
		
		
		delegator.execute();
		
		final ScreenerResult result = analyticsContext.getResult();
		
		final PortfolioScreenerResultDTO dto = AnalyticsConveters.fromScreenerResultToDto().convert(analyticsContext);
		
		return new ResponseEntity<PortfolioScreenerResultDTO>(dto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Screen for results given a list of stocks")
	@RequestMapping(value = "/investment-style/{styleId}/portfolio/{portfolioId}", method = RequestMethod.GET)
	public ResponseEntity<StockScreenerResultDTO> screenForStocks(@PathVariable("styleId") Long styleId, @PathVariable("portfolioId") Long portfolioId){
		
		final Map<String,String> properties = new HashMap<>();
		
		final ScreenerRequest resuest = ScreenerRequest.of(styleId, Arrays.asList(portfolioId));
		
		//TODO: create separate context
		PortfolioScreenerContext analyticsContext = new PortfolioScreenerContext(analyticsService, configurationService, portfolioService, executorService, calendarService, Operation.SCREEN_FOR_PORTFOLIOS, properties, resuest);
		
		Delegator delegator = new ScreenerDelegator(analyticsContext);
		
		Command screenerCommand = new ScreenerCommand(analyticsContext);
		
		delegator.add(screenerCommand);
		
		delegator.execute();
		
		ScreenerResult result = analyticsContext.getResult();
		
		StockScreenerResultDTO dto = null;
		
		return new ResponseEntity<StockScreenerResultDTO>(dto, HttpStatus.OK);
	}
	
}
