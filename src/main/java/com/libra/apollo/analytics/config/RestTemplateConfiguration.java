package com.libra.apollo.analytics.config;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import com.libra.apollo.analytics.client.ApolloRetryPolicy;
import com.libra.apollo.analytics.client.RetryRestTemplate;

//@Profile("production")
@Configuration
public class RestTemplateConfiguration {
	
	@Value("${apollo.retry.backOffPeriod:5000}")
    private String backOffPeriod;

    @Value("${apollo.retry.maxAttempts:3}")
    private String maxAttempts;

    private static final ConcurrentHashMap<String, ExceptionHandler> SUPPORTED_EXCEPTIONS = new ConcurrentHashMap<>();

    /**
     * Configure and return the retry template.
     */
    public RetryTemplate getRetryTemplate(){
        //Create RetryTemplate
        final RetryTemplate retryTemplate = new RetryTemplate();
        
        //Create Fixed back policy
        final FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        
        //Set backOffPeriod
        fixedBackOffPolicy.setBackOffPeriod(Long.valueOf(backOffPeriod));
        
        //Set the backoff policy
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        //Create Simple Retry Policy
        final ApolloRetryPolicy retryPolicy = new ApolloRetryPolicy(Integer.valueOf(maxAttempts), Collections.<Class<? extends Throwable>, Boolean> singletonMap(RestClientException.class, true), false);

        //Set retry policy
        retryTemplate.setRetryPolicy(retryPolicy);
        
        //Return the RetryTemplate
        return retryTemplate;
    }

    /**
     * Configure and return the Rest Template.
     */
    @Bean(name = "ApolloRestTemplate")
    public RetryRestTemplate getRestTemplate() {

        //Creates the restTemplate instance
        final RetryRestTemplate retryRestTemplate = new RetryRestTemplate();

        //Create Custom Exception Handler

        //Set Supported Exceptions

        //Set the custom exception handler ar default        

        //Set Retry Template
        retryRestTemplate.setRetryTemplate(getRetryTemplate());

        //Return the template instance
        return retryRestTemplate;
    }
}
