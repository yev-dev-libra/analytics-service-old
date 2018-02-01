package com.libra.apollo.analytics.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libra.apollo.analytics.dto.PortfolioScreenerResultDTO;
import com.libra.apollo.analytics.dto.StockScreenerResultDTO;
import com.libra.apollo.analytics.engine.commands.Delegator;
import com.libra.apollo.analytics.engine.commands.DelegatorFactory;
import com.libra.apollo.analytics.engine.context.AnalyticsContext;
import com.libra.apollo.analytics.engine.context.AnalyticsContextFactory;
import com.libra.apollo.analytics.engine.converters.AnalyticsConveters;
import com.libra.apollo.analytics.engine.request.ScreenerRequest;
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
	
	
	@RequestMapping(value = "/investment-styles/{id}/groups", method = RequestMethod.GET, produces = {"application/json"})
	@ApiOperation(value = "Get a list of Portfolio Screen Results", notes = "Screen for results given a list of portfolios", response=PortfolioScreenerResultDTO.class)
	public ResponseEntity<PortfolioScreenerResultDTO> screenForPortfolios(@PathVariable("id") Long styleId, @RequestParam("ids") List<Long> groupIds, @RequestParam(value="date", required=false)  @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
		
		final Map<String,String> properties = new HashMap<>();
		final ScreenerRequest request = ScreenerRequest.of(styleId, groupIds,date);
		final AnalyticsContext analyticsContext =  AnalyticsContextFactory.getContext(analyticsService, configurationService, portfolioService, executorService, calendarService, properties, request);
		final Delegator delegator = DelegatorFactory.getCommandDelegator(analyticsContext);
		delegator.execute();
		
		final PortfolioScreenerResultDTO dto = AnalyticsConveters.fromScreenerResultToDto().convert(analyticsContext);
		
		return new ResponseEntity<PortfolioScreenerResultDTO>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/investment-styles/{id}/stocks", method = RequestMethod.GET, produces = {"application/json"})
	@ApiOperation(value = "Get a list of Stock Screen Results", notes = "Screen for results given a list of stocks", response=StockScreenerResultDTO.class)
	public ResponseEntity<StockScreenerResultDTO> screenForStocks(@PathVariable("id") Long styleId, @RequestParam("ids") List<Long> stockIds, @RequestParam(value="date", required=false)  @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
		
		
		StockScreenerResultDTO dto = new StockScreenerResultDTO();
		
		return new ResponseEntity<StockScreenerResultDTO>(dto, HttpStatus.OK);
	}
	
}
