package com.libra.apollo.analytics.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.libra.apollo.analytics.engine.command.Command;
import com.libra.apollo.analytics.engine.command.Delegator;
import com.libra.apollo.analytics.engine.command.PortfolioEnrichmentCommand;
import com.libra.apollo.analytics.engine.command.ScreenerCommand;
import com.libra.apollo.analytics.engine.command.ScreenerDelegator;
import com.libra.apollo.analytics.engine.context.PortfolioScreenerContext;
import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.service.PortfolioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/analytics")
@Api(value = "CONFIGURATIONS", description = "Analytics Rest Controller")
public class AnalyticsRestController {
	
	protected Logger logger = Logger.getLogger(AnalyticsRestController.class.getName());
	
	@Autowired
	private AnalyticsService analyticsService;
	
	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	private PortfolioService portfolioService;
	
	@ApiOperation(value = "Screen for results given a list of portfolios")
	@RequestMapping(value = "/screen/{styleId}/portfolios/{portfolioIds}", method = RequestMethod.GET)
	public ResponseEntity<ScreenerResult> screenForPortfolios(@PathVariable("styleId") Long styleId, @PathVariable("portfolioIds") String[] portfolioIds){
		
		final Map<String,String> properties = new HashMap<>();
		
		PortfolioScreenerContext analyticsContext = new PortfolioScreenerContext(analyticsService, configurationService, portfolioService, Operation.SCREEN_FOR_PORTFOLIO, properties);
		
		Delegator delegator = new ScreenerDelegator(analyticsContext);
		
		Command portfolioEnrichment = new PortfolioEnrichmentCommand(analyticsContext);
		
		Command screenerCommand = new ScreenerCommand(analyticsContext);
		
		delegator.add(portfolioEnrichment);
		delegator.add(screenerCommand);
		
		delegator.execute();
		
		ScreenerResult results = analyticsContext.getResult();
		
		return new ResponseEntity<ScreenerResult>(results, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Screen for results given a list of stocks")
	@RequestMapping(value = "/screen/{styleId}/stocks/{stocks}", method = RequestMethod.GET)
	public ResponseEntity<ScreenerResult> screenForStocks(@PathVariable("operation") String operation, @PathVariable("portfolioIds") String[] portfolioIds){
		
		final Map<String,String> properties = new HashMap<>();
		
		PortfolioScreenerContext analyticsContext = new PortfolioScreenerContext(analyticsService, configurationService, portfolioService, Operation.SCREEN_FOR_PORTFOLIO, properties);
		
		Delegator delegator = new ScreenerDelegator(analyticsContext);
		
		Command screenerCommand = new ScreenerCommand(analyticsContext);
		
		delegator.add(screenerCommand);
		
		delegator.execute();
		
		ScreenerResult results = analyticsContext.getResult();
		
		return new ResponseEntity<ScreenerResult>(results, HttpStatus.OK);
	}
}
