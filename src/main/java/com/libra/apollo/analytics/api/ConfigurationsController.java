package com.libra.apollo.analytics.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.libra.apollo.analytics.engine.request.ConfigurationAnalyticsRequest;
import com.libra.apollo.analytics.engine.response.AnalyticsConfigurationResponse;
import com.libra.apollo.analytics.service.Delegator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/v1/config")
@Api(value = "CONFIGURATIONS", description = "Analytics Configurations")
public class ConfigurationsController {

	private static Logger logger = LoggerFactory.getLogger(ConfigurationsController.class);
	
	@Autowired
	@Qualifier("investmentStylesConfigDelegatorStub")
	private Delegator delegator;
	
	@RequestMapping(value = "/styles", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of Investment Styles", notes = "Returns a list of available Inverstment Styles with description")
	public ResponseEntity<AnalyticsConfigurationResponse> getListOfInvestmentStyles(ConfigurationAnalyticsRequest configurationRequest){
		
		if(logger.isDebugEnabled()) {
			logger.debug("IN getInvestmentStylesConfiguration");
		}
		
		AnalyticsConfigurationResponse response = (AnalyticsConfigurationResponse)delegator.delegate(configurationRequest);
		
		
		return new ResponseEntity<AnalyticsConfigurationResponse>(response, HttpStatus.OK);
		
	}
}
