package com.libra.apollo.analytics.api;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.libra.apollo.analytics.dto.GenericMultiValuesDTO;
import com.libra.apollo.analytics.dto.GenericSingleValueDTO;
import com.libra.apollo.analytics.engine.request.AnalyticsViewConfigurationRequest;
import com.libra.apollo.analytics.engine.response.AnalyticsConfigurationResponse;
import com.libra.apollo.analytics.entity.AnalyticsView;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.service.Delegator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/config")
@Api(value = "CONFIGURATIONS", description = "Analytics Configurations")
public class ConfigurationsController {

	private static Logger logger = LoggerFactory.getLogger(ConfigurationsController.class);
	
	@Autowired
	private ConfigurationService configService;
	           
	@RequestMapping(value = "/views", method = RequestMethod.GET)
	@ApiOperation(value = "Get a list of views", notes = "Returns a list of available Views with Inverstment Styles")
	@JsonView(AnalyticsView.Internal.class)
	public ResponseEntity<List<AnalyticsView>> getViews(AnalyticsViewConfigurationRequest configurationRequest){
		
		if(logger.isDebugEnabled()) {
			logger.debug("IN getInvestmentStylesConfiguration");
		}
		
		
		
//		List<InvestmentStyle> entities = configService.getInvestmentStyles(configurationRequest);
		
//		return new ResponseEntity<List<AnalyticsView>>(entities, HttpStatus.OK);
		return null;
		
		
	}
}
