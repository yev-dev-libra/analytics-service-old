package com.libra.apollo.analytics.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.libra.apollo.analytics.config.async.ExceptionHandlingAsyncTaskExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration implements AsyncConfigurer {

	private final Logger log = LoggerFactory.getLogger(AsyncConfiguration.class);

	@Value("${analytics.async.core-pool-size}")
	private int corePoolSize;

	@Value("${analytics.async.max-pool-size}")
	private int maxPoolSize;

	@Value("${analytics.async.queue-capacity}")
	private int queueCapacity;

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}
	
	@Override
	@Bean(name = "taskExecutor")
	public AsyncTaskExecutor getAsyncExecutor() {

		log.debug("Creating Async Task Executor");

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("my-app-Executor-");

		return new ExceptionHandlingAsyncTaskExecutor(executor);

	}

}
