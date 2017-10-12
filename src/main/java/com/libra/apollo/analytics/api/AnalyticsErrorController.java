package com.libra.apollo.analytics.api;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class AnalyticsErrorController implements ErrorController {

	private static final String PATH = "/error";
	
    @RequestMapping(value=PATH)
    public String error() {
        return "";
    }
 
    @Override
    public String getErrorPath() {
        return PATH;
    }

}
