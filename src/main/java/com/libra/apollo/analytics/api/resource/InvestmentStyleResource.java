package com.libra.apollo.analytics.api.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.libra.apollo.analytics.entity.InvestmentStyle;

public class InvestmentStyleResource extends Resource<InvestmentStyle> {

	public InvestmentStyleResource(InvestmentStyle content, Link... links) {
		super(content, links);
	}

}
