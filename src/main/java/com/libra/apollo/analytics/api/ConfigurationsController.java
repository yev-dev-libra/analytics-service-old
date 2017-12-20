package com.libra.apollo.analytics.api;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;
import com.libra.apollo.analytics.entity.jsonviews.AnalyticsJsonView;
import com.libra.apollo.analytics.service.ConfigurationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/config")
@Api(value = "CONFIGURATIONS", description = "Analytics Configurations")
public class ConfigurationsController {

	private static Logger logger = LoggerFactory.getLogger(ConfigurationsController.class);

	@Autowired
	private ConfigurationService configService;

	/**
	 * Return a full list of available apollo analytics with its views and investment styles or a subgroup of analytics if the
	 * parameter 'search' is used.
	 * Use search=field:value to do a search for the field ([name|runType|analyticsType]) that contains 'value'
	 * 
	 * @return List of all available apollo anaylitics or a subgroup filtered using the parameter 'search'
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "Get a list of Apollo Analytics", notes = "Returns a list of available Analytics with views and Investment Styles")
	@JsonView(AnalyticsJsonView.Public.class)
	public ResponseEntity<List<ApolloAnalytics>> getAnalytics(
			@ApiParam(value="Use search=[name|runType|analyticsType]:value", name="search", allowableValues="name:Apollo Screener,runType:ON_DEMAND,analyticsType:APOLLO_SCREENER", required = false)
			@RequestParam(value = "search", required=false) String search) {

		if (logger.isDebugEnabled()) {
			logger.debug("Retrieving all analytics. Search param[" + search + "]");
		}

		// If search is not used then return all analytics
		if(search==null) {
			return new ResponseEntity<List<ApolloAnalytics>>(configService.getAnalytics(), HttpStatus.OK);
		}
		
		// Using search with field:value, if search is not in the format field:value then return bad request
		String[] result = search.split("\\:");
		if(result.length!=2) {
			throw new IllegalArgumentException("Search parameter needs to be in the format [name|runType|analyticsType]:string like name:Apollo Screener");
		}
		
		String searchByField = result[0];
		String searchValue = result[1];
		
		switch (searchByField) {
			case "name":
				return new ResponseEntity<List<ApolloAnalytics>>(
						configService.getAnalyticsByName(searchValue), HttpStatus.OK);
			
			case "runType":
				if(RunType.getRunType(searchValue)==null) {
					throw new IllegalArgumentException("Search parameter 'runType' is not valid. Valid values: " + Arrays.toString(RunType.values()));
				}
				
				return new ResponseEntity<List<ApolloAnalytics>>(
						configService.getAnalyticsByRunType(RunType.getRunType(searchValue)), HttpStatus.OK);

			case "analyticsType":
				if(AnalyticsType.getAnalyticsType(searchValue)==null) {
					throw new IllegalArgumentException("Search parameter 'analyticsType' is not valid. Valid values: " + Arrays.toString(AnalyticsType.values()));
				}
				
				return new ResponseEntity<List<ApolloAnalytics>>(
						configService.getAnalyticsByType(AnalyticsType.getAnalyticsType(searchValue)), HttpStatus.OK);

			// It is only allowed the search by the previous values otherwise return bad request
			default:
				throw new IllegalArgumentException("Search parameter valid values are: name, runType and analyticsType");
		}

	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@JsonView(AnalyticsJsonView.Public.class)
	public ResponseEntity<List<ApolloAnalytics>> getAnalyticsById(@PathVariable Long id) {
		List<ApolloAnalytics> analytics = configService.getAnalyticsById(id);

		return new ResponseEntity<List<ApolloAnalytics>>(analytics, HttpStatus.OK);
	}
}
