
package com.libra.apollo.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EnableEurekaClient
@EntityScan(basePackages = {"com.libra.apollo.analytics.entity"}) 
public class ApolloAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApolloAnalyticsApplication.class, args);
	}
}