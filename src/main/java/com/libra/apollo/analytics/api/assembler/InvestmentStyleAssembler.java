package com.libra.apollo.analytics.api.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.libra.apollo.analytics.api.ConfigurationsRestController;
import com.libra.apollo.analytics.api.resource.InvestmentStyleResource;
import com.libra.apollo.analytics.entity.InvestmentStyle;

@Component
public class InvestmentStyleAssembler extends ResourceAssemblerSupport<InvestmentStyle,InvestmentStyleResource> {

	
	@Autowired
	private EntityLinks entityLinks;
	
	public InvestmentStyleAssembler() {
		super(ConfigurationsRestController.class, InvestmentStyleResource.class);
	}

	@Override
	public InvestmentStyleResource toResource(InvestmentStyle investmentStyle) {
		InvestmentStyleResource resource = createResourceWithId(investmentStyle.getId(), investmentStyle);
		
		return resource;
	}
	
	protected InvestmentStyleResource instantiateResource(InvestmentStyle entity) {
		return new InvestmentStyleResource(entity);
	}

}
