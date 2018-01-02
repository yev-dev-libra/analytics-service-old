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
import com.libra.apollo.analytics.exceptions.EntityNotFoundException;
import com.libra.apollo.analytics.entity.enums.AnalyticsSearchFieldApi;
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
	 * parameter 'search' is used with the Public view.
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
			throw new IllegalArgumentException("Search parameter needs to be in the format searchParameter:searchValue like name:Apollo Screener. searchParameter valid values: " + Arrays.toString(AnalyticsSearchFieldApi.values()));
		}
		
		String searchByField = result[0];
		String searchValue = result[1];
		
		AnalyticsSearchFieldApi searchParam = AnalyticsSearchFieldApi.getAnalyticsSearchFieldApi(searchByField);
		if(searchParam==null) {
			throw new IllegalArgumentException("Search parameter not valid. Valid values are: " + Arrays.toString(AnalyticsSearchFieldApi.values()));
		}
		
		switch (searchParam) {
			case NAME:
				List<ApolloAnalytics> nameSearchResult = configService.getAnalyticsByName(searchValue);
				if(nameSearchResult==null || nameSearchResult.isEmpty()) {
					throw new IllegalArgumentException("Search parameter '"+ AnalyticsSearchFieldApi.NAME + "' is not valid. Valid values: " + configService.getAnalyticsNames());
				}
				
				return new ResponseEntity<List<ApolloAnalytics>>(
						nameSearchResult, HttpStatus.OK);
			
			case RUN_TYPE:
				if(RunType.getRunType(searchValue)==null) {
					throw new IllegalArgumentException("Search parameter '"+ AnalyticsSearchFieldApi.RUN_TYPE + "' is not valid. Valid values: " + Arrays.toString(RunType.values()));
				}
				
				return new ResponseEntity<List<ApolloAnalytics>>(
						configService.getAnalyticsByRunType(RunType.getRunType(searchValue)), HttpStatus.OK);

			case ANALYTICS_TYPE:
				if(AnalyticsType.getAnalyticsType(searchValue)==null) {
					throw new IllegalArgumentException("Search parameter '"+ AnalyticsSearchFieldApi.ANALYTICS_TYPE + "' is not valid. Valid values: " + Arrays.toString(AnalyticsType.values()));
				}
				
				return new ResponseEntity<List<ApolloAnalytics>>(
						configService.getAnalyticsByType(AnalyticsType.getAnalyticsType(searchValue)), HttpStatus.OK);

			// It is only allowed the search by the previous values otherwise return bad request
			default:
				throw new IllegalArgumentException("Search parameter not valid. Valid values are: " + Arrays.toString(AnalyticsSearchFieldApi.values()));
		}

	}
	
	/**
	 * Return public view of the analytic with identifier 'id', if the analytic is not found then
	 * return not found code.
	 * 
	 * @param id	Apollo analytic identifier
	 * @return AnalyticsJsonView.Public that contains public information of the analytic
	 * @throws EntityNotFoundException  
	 */
	@RequestMapping(value="/{analyticId}", method = RequestMethod.GET)
	@JsonView(AnalyticsJsonView.Public.class)
	public ResponseEntity<List<ApolloAnalytics>> getAnalyticsById(@PathVariable Long analyticId) throws EntityNotFoundException {
		List<ApolloAnalytics> analytics = configService.getAnalyticsById(analyticId);

		// If analytic does not exist then return not found code
		if(analytics == null || analytics.isEmpty()) {
			throw new EntityNotFoundException(ApolloAnalytics.class, "analyticId", analyticId.toString());
	    }
		
		return new ResponseEntity<List<ApolloAnalytics>>(analytics, HttpStatus.OK);
	}
	
//	@RequestMapping(value="/{analyticId}/views", method = RequestMethod.GET)
//	@JsonView(AnalyticsJsonView.Public.class)
//	public ResponseEntity<List<ApolloAnalytics>> getAnalyticsViewsByAnalyticId(@PathVariable Long analyticId) throws EntityNotFoundException {
//
//		return new ResponseEntity<List<ApolloAnalytics>>(null, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value="/{analyticId}/view/{viewId}", method = RequestMethod.GET)
//	@JsonView(AnalyticsJsonView.Public.class)
//	public ResponseEntity<List<ApolloAnalytics>> getAnalyticsViewsByAnalyticIdAndViewId(
//			@PathVariable Long analyticId,
//			@PathVariable Long viewId) throws EntityNotFoundException {
//		
//		return new ResponseEntity<List<ApolloAnalytics>>(null, HttpStatus.OK);
//	}
	
}
