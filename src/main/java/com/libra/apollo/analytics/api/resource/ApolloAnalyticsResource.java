package com.libra.apollo.analytics.api.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.libra.apollo.analytics.entity.ApolloAnalytics;

public class ApolloAnalyticsResource extends Resource<ApolloAnalytics> {

	ApolloAnalyticsResource(ApolloAnalytics analytics, Link... links) {
		super(analytics, links);
	}

}
