package com.libra.apollo.analytics.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EntityScan(basePackages = {"com.libra.apollo.analytics.entity"}) 
@EnableJpaRepositories(basePackages = "com.libra.apollo.analytics.repository", repositoryImplementationPostfix = "CustomImpl")
public class ApolloAnalyticsJpaConfogiration extends HibernateJpaAutoConfiguration {

	public ApolloAnalyticsJpaConfogiration(DataSource dataSource, JpaProperties jpaProperties, ObjectProvider<JtaTransactionManager> jtaTransactionManager, ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
		super(dataSource, jpaProperties, jtaTransactionManager, transactionManagerCustomizers);
	}

}
