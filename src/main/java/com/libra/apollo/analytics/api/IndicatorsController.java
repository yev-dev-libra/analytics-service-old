package com.libra.apollo.analytics.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libra.apollo.analytics.entity.StockIndicator;
import com.libra.apollo.analytics.projection.ApolloIndicators;
import com.libra.apollo.analytics.repository.StockIndicatorRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/indicators")
@Api(value = "Indicators", description = "Apollo Indicators Api")
public class IndicatorsController {

	@Autowired
	private StockIndicatorRepository stockIndicatorRepository;
	
	@RequestMapping(value="/stock", method=RequestMethod.GET, produces= {"application/json"})
	@ApiOperation(value = "Get a list of Apollo Indicators", notes = "Get a list of Stock Indicators for a date range", response=ApolloIndicators.class, responseContainer = "List")
	public ResponseEntity<List<ApolloIndicators>> getStockIndicators(@ApiParam(value="Stock id: 1")  @RequestParam("id") Long id, @ApiParam(value="Date: '2018-01-01'")  @RequestParam(value="fromDate", required=false)  @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,  @ApiParam(value="Date: '2018-01-01'") @RequestParam(value="toDate", required=false)  @DateTimeFormat(pattern="yyyy-MM-dd")Date toDate){
		
		List<ApolloIndicators> indicators = stockIndicatorRepository.findApolloIndicatorsByStockIdAndBetweenStampDates(id, fromDate, toDate);
		
		return new ResponseEntity<List<ApolloIndicators>>(indicators, HttpStatus.OK);
	}
	
	@RequestMapping(value="/stocks", method=RequestMethod.GET, produces= {"application/json"})
	@ApiOperation(value = "Get a list of Apollo Indicators", notes = "Get a list of Stocks Indicators for a date range", response=ApolloIndicators.class, responseContainer = "List")
	public ResponseEntity<List<ApolloIndicators>> getStocksIndicators(@ApiParam(value="Stock ids: [1,2,3,...]")  @RequestParam("ids") List<Long> ids, @ApiParam(value="Date: '2018-01-01'")  @RequestParam(value="fromDate", required=false)  @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,  @ApiParam(value="Date: '2018-01-01'") @RequestParam(value="toDate", required=false)  @DateTimeFormat(pattern="yyyy-MM-dd")Date toDate){
		
		List<ApolloIndicators> indicators = stockIndicatorRepository.findApolloIndicatorsByStockIdsAndBetweenStampDates(ids, fromDate, toDate);
		
		return new ResponseEntity<List<ApolloIndicators>>(indicators, HttpStatus.OK);
	}
}
