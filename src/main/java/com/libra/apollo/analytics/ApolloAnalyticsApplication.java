
package com.libra.apollo.analytics;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.libra.apollo.analytics.repository.LibraStockIndicatorRepositoryJpa;

@SpringBootApplication
//@EnableEurekaClient
@EntityScan(basePackages = {"com.libra.apollo.analytics.entity"}) 
public class ApolloAnalyticsApplication {

	@Autowired
	private LibraStockIndicatorRepositoryJpa repository;

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger log = LoggerFactory.getLogger(ApolloAnalyticsApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ApolloAnalyticsApplication.class, args);
	}
	
//	@Profile(value = { "dev" })
//	@Bean
//	public CommandLineRunner setup(AnalyticsRepository apolloAnalyticsRepository) {
//		if(log.isDebugEnabled()) {
//			log.debug("Populating data");
//		}
//		LibraStockIndicator stockIndicators = repository.findOne(156261081L);
//		System.out.println(stockIndicators);
//		
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, -1);
//		Date date = new Date(cal.getTimeInMillis());
//		List<LibraStockIndicator> indicators = repository.findByStampDate(date);
//		System.out.println(indicators);
//		return null;
//	}
}