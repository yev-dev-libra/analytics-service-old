
package com.libra.apollo.analytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.libra.apollo.analytics.repository.AnalyticsRepository;

@SpringBootApplication
//@EnableEurekaClient
@EntityScan(basePackages = {"com.libra.apollo.analytics.entity"}) 
public class ApolloAnalyticsApplication {

	private static final Logger log = LoggerFactory.getLogger(ApolloAnalyticsApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ApolloAnalyticsApplication.class, args);
	}
	
	@Profile(value = { "test" })
	@Bean
	public CommandLineRunner setup(AnalyticsRepository apolloAnalyticsRepository) {
		if(log.isDebugEnabled()) {
			log.debug("Populating data");
		}
		return null;
	}
}