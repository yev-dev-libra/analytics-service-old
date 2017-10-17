package com.libra.apollo.analytics;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.libra.apollo.analytics.core.repositories")
@PropertySource("application.properties")
@EnableTransactionManagement
public class H2JpaConfig {

}
