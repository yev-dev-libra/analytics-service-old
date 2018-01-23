package com.libra.apollo.analytics.config;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.libra.apollo.analytics.service.AnalyticsService;


@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
//@EntityScan(basePackages = {"com.libra.apollo.analytics.entity"}) 
//@EnableJpaRepositories(basePackages = "com.libra.apollo.analytics.repository", repositoryImplementationPostfix = "CustomImpl")
@ComponentScan(basePackageClasses = AnalyticsService.class)
@Import({
	ApolloAnalyticsJpaConfogiration.class
})
public class ApolloAnalyticsAutoConfiguration {

}
