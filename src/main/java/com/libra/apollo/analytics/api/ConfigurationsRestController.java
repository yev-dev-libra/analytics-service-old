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
import com.libra.apollo.analytics.entity.AnalyticsView;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;
import com.libra.apollo.analytics.entity.enums.RunType;
import com.libra.apollo.analytics.entity.jsonviews.ConfigurationJsonView;
import com.libra.apollo.analytics.service.ConfigurationService;
import com.libra.apollo.analytics.exceptions.EntityNotFoundException;
import com.libra.apollo.analytics.entity.enums.AnalyticsSearchFieldApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/config")
@Api(value = "CONFIGURATIONS", description = "Analytics Configurations")
public class ConfigurationsRestController {

	private static Logger logger = LoggerFactory.getLogger(ConfigurationsRestController.class);

	@Autowired
	private ConfigurationService configService;

	/**
	 * Return a full list of available apollo analytics or a subgroup of analytics if the parameter 'search' is used with the Public view.
	 * Use search=field:value to do a search for the field ([name|runType|analyticsType]) that contains 'value'
	 * 
	 * @return List of all available apollo anaylitics or a subgroup filtered using the parameter 'search'
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "Get a list of Apollo Analytics", notes = "Returns a list of available Analytics with views and Investment Styles")
	@JsonView(ConfigurationJsonView.Public.class)
	public ResponseEntity<List<ApolloAnalytics>> getAnalytics(
			@ApiParam(value="Use search=[name|runType|analyticsType]:value", name="search", allowableValues="name:Apollo Screener,runType:ON_DEMAND,analyticsType:APOLLO_SCREENER", required = false)
			@RequestParam(value = "search", required=false) String search) {

		return handleGetAnalytics(search);

	}
	
	/**
	 * Return a full list of available apollo analytics with its views and investment styles or a subgroup of analytics if the
	 * parameter 'search' is used with the PublicExtended view.
	 * Use search=field:value to do a search for the field ([name|runType|analyticsType]) that contains 'value'
	 * 
	 * @return List of all available apollo anaylitics or a subgroup filtered using the parameter 'search'
	 */
	@RequestMapping(value = "/extended", method = RequestMethod.GET)
	@ApiOperation(value = "Get a list of Apollo Analytics", notes = "Returns a list of available Analytics with views and Investment Styles")
	@JsonView(ConfigurationJsonView.PublicExtended.class)
	public ResponseEntity<List<ApolloAnalytics>> getAnalyticsExtended(
			@ApiParam(value="Use search=[name|runType|analyticsType]:value", name="search", allowableValues="name:Apollo Screener,runType:ON_DEMAND,analyticsType:APOLLO_SCREENER", required = false)
			@RequestParam(value = "search", required=false) String search) {

		return handleGetAnalytics(search);

	}
	
	private ResponseEntity<List<ApolloAnalytics>> handleGetAnalytics(String search) {
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
	 * @return ConfigurationJsonView.Public that contains public information of the analytic
	 * @throws EntityNotFoundException  
	 */
	@RequestMapping(value="/{analyticId}", method = RequestMethod.GET)
	@JsonView(ConfigurationJsonView.Public.class)
	public ResponseEntity<ApolloAnalytics> getAnalyticsById(@PathVariable Long analyticId) throws EntityNotFoundException {
		ApolloAnalytics analytics = configService.getAnalyticsById(analyticId);

		// If analytic does not exist then return not found code
		if(analytics == null) {
			throw new EntityNotFoundException(ApolloAnalytics.class, "analyticId", analyticId.toString());
	    }
		
		return new ResponseEntity<ApolloAnalytics>(analytics, HttpStatus.OK);
	}
	
	/**
	 * Return all the specific analyticId's views using ConfigurationJsonView.Public
	 * 
	 * @param analyticId Apollo analytic identifier
	 * @return ConfigurationJsonView.Public that contains public information of the analytic's views
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value="/{analyticId}/views", method = RequestMethod.GET)
	@JsonView(ConfigurationJsonView.Public.class)
	public ResponseEntity<List<AnalyticsView>> getAnalyticsViewsByAnalyticId(@PathVariable Long analyticId) throws EntityNotFoundException {
		ApolloAnalytics apolloAnalytics = configService.getAnalyticsById(analyticId);

		// If apolloAnalytics does not exist then return not found code
		if(apolloAnalytics == null) {
			throw new EntityNotFoundException(ApolloAnalytics.class, "analyticId", analyticId.toString());
	    }
		
		List<AnalyticsView> analyticViews = configService.getAnalyticsViewsByAnalyticId(apolloAnalytics);

		// If analyticViews does not exist then return not found code
		if(analyticViews == null || analyticViews.isEmpty()) {
			throw new EntityNotFoundException(ApolloAnalytics.class, "analyticId", analyticId.toString());
	    }
		
		return new ResponseEntity<List<AnalyticsView>>(analyticViews, HttpStatus.OK);
	}
	
	/**
	 * Return public view of the specific analytic with analyticId and view with identifier 'viewId', if the analytic or the view is not found then
	 * return not found code.
	 * 
	 * @param analyticId Apollo analytic identifier
	 * @param viewId View identifier
	 * @return ConfigurationJsonView.Public that contains public information of the specific view
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value="/{analyticId}/views/{viewId}", method = RequestMethod.GET)
	@JsonView(ConfigurationJsonView.Public.class)
	public ResponseEntity<AnalyticsView> getAnalyticsViewsByAnalyticIdAndViewId(
			@PathVariable Long analyticId,
			@PathVariable Long viewId) throws EntityNotFoundException {
		ApolloAnalytics apolloAnalytics = configService.getAnalyticsById(analyticId);

		// If apolloAnalytics does not exist then return not found code
		if(apolloAnalytics == null) {
			throw new EntityNotFoundException(ApolloAnalytics.class, "analyticId", analyticId.toString());
	    }
		
		AnalyticsView analyticView = configService.getAnalyticsViewByAnalyticIdAndViewId(apolloAnalytics, viewId);

		// If analyticView does not exist then return not found code
		if(analyticView == null) {
			throw new EntityNotFoundException(AnalyticsView.class, "viewId", viewId.toString());
	    }
		
		return new ResponseEntity<AnalyticsView>(analyticView, HttpStatus.OK);
	}
	
	/**
	 * Return public view of all the investment styles with the specific analytic with analyticId and view with identifier 'viewId', if the analytic or the view is not found then
	 * return not found code.
	 * 
	 * @param analyticId Apollo analytic identifier
	 * @param viewId View identifier
	 * @return ConfigurationJsonView.Public List that contains public information of the all the investment styles
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value="/{analyticId}/views/{viewId}/investment-styles", method = RequestMethod.GET)
	@JsonView(ConfigurationJsonView.Public.class)
	public ResponseEntity<List<InvestmentStyle>> getInvestmentStylesByAnalyticIdAndViewId(
			@PathVariable Long analyticId,
			@PathVariable Long viewId) throws EntityNotFoundException {
		ApolloAnalytics apolloAnalytics = configService.getAnalyticsById(analyticId);

		// If apolloAnalytics does not exist then return not found code
		if(apolloAnalytics == null) {
			throw new EntityNotFoundException(ApolloAnalytics.class, "analyticId", analyticId.toString());
	    }
		
		AnalyticsView analyticView = configService.getAnalyticsViewByAnalyticIdAndViewId(apolloAnalytics, viewId);

		// If analyticView does not exist then return not found code
		if(analyticView == null) {
			throw new EntityNotFoundException(AnalyticsView.class, "viewId", viewId.toString());
	    }
		
		List<InvestmentStyle> investmentStyles = configService.getInvestmentStylesByView(analyticView);
		
		// If investmentStyles does not exist then return not found code
		if(investmentStyles == null || investmentStyles.isEmpty()) {
			throw new EntityNotFoundException(AnalyticsView.class, "viewId", viewId.toString());
	    }
		
		return new ResponseEntity<List<InvestmentStyle>>(investmentStyles, HttpStatus.OK);
	}
	
}
