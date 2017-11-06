package com.libra.apollo.analytics.entity;

import org.junit.Test;

import com.libra.apollo.analytics.utils.Properties;

public class InvestmentStyleTest  {

	@Test
	public void testInvestmentStyleOrder() {
		
		
		InvestmentStyle parentStyle1 = new InvestmentStyle();
		parentStyle1.getDefinition().setCode("P1");
		parentStyle1.setPriority(Properties.FIRST_PRIORITY);
		
		InvestmentStyle parentStyle2 = new InvestmentStyle();
		parentStyle2.getDefinition().setCode("P2");
		parentStyle2.setPriority(Properties.SECOND_PRIORITY);
		
		InvestmentStyle childeInvsStyle1 = new InvestmentStyle();
		childeInvsStyle1.getDefinition().setCode("Ch1");
		childeInvsStyle1.setPriority(Properties.FIRST_PRIORITY);
		
		InvestmentStyle childeInvsStyle2 = new InvestmentStyle();
		childeInvsStyle2.getDefinition().setCode("Ch2");
		childeInvsStyle2.setPriority(Properties.FIRST_PRIORITY);
		
	}
}
