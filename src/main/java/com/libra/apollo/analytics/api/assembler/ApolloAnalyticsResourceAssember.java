package com.libra.apollo.analytics.api.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.libra.apollo.analytics.api.ConfigurationsRestController;
import com.libra.apollo.analytics.api.resource.ApolloAnalyticsResource;
import com.libra.apollo.analytics.entity.ApolloAnalytics;

@Component
public class ApolloAnalyticsResourceAssember extends ResourceAssemblerSupport<ApolloAnalytics,ApolloAnalyticsResource> {

	@Autowired
	EntityLinks entityLinks;
	
	public ApolloAnalyticsResourceAssember() {
		super(ConfigurationsRestController.class, ApolloAnalyticsResource.class);
	}

	@Override
	public ApolloAnalyticsResource toResource(ApolloAnalytics analytics) {
		ApolloAnalyticsResource resource = createResourceWithId(analytics.getId(), analytics);
		
		if(analytics.getAnalyticsViews() != null) {
		}
		
		return resource;
	}

}
