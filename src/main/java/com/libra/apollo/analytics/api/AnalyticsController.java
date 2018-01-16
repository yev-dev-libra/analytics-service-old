package com.libra.apollo.analytics.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.libra.apollo.analytics.engine.command.Command;
import com.libra.apollo.analytics.engine.command.ScreenerDelegator;
import com.libra.apollo.analytics.engine.context.AnalyticsContext;
import com.libra.apollo.analytics.engine.context.ScreeningContext;
import com.libra.apollo.analytics.engine.core.Operation;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.service.AnalyticsService;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.service.PortfolioService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/analysis")
@Api(value = "CONFIGURATIONS", description = "Analytics Controller")
public class AnalyticsController {
	
	protected Logger logger = Logger.getLogger(AnalyticsController.class.getName());
	
	@Autowired
	private AnalyticsService analyticsService;
	
	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	private PortfolioService portfolioService;
	
	
	@RequestMapping(value = "/screen", method = RequestMethod.POST)
	public ResponseEntity<ScreenerResult> screen(){
		
		final Map<String,String> properties = new HashMap<>();
		
		AnalyticsContext context = new ScreeningContext(analyticsService, configurationService, portfolioService, Operation.SCREEN, properties);
		
		Command delegator = new ScreenerDelegator(context);
		
		delegator.execute();
		
		ScreenerResult results = context.getResult();
		
		return new ResponseEntity<ScreenerResult>(results, HttpStatus.OK);
	}
}
