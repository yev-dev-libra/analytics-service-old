
package com.libra.apollo.analytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableEurekaClient
public class ApolloAnalyticsApplication {

	private static final Logger log = LoggerFactory.getLogger(ApolloAnalyticsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApolloAnalyticsApplication.class, args);
	}

}