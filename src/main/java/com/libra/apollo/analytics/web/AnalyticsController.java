package com.libra.apollo.analytics.web;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/analytics")
public class AnalyticsController {
	
	protected Logger logger = Logger.getLogger(AnalyticsController.class.getName());
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<String> hello(){
		return new ResponseEntity<String>("Hello!", HttpStatus.OK);
	}
}
