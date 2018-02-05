package com.libra.apollo.analytics.entity;

import org.junit.Test;

import com.libra.apollo.analytics.util.Properties;

public class InvestmentStyleTest  {

	@Test
	public void testInvestmentStyleOrder() {
		
		
		InvestmentStyle parentStyle1 = new InvestmentStyle();
		parentStyle1.getDefinition().setName("P1");
		parentStyle1.setPriority(Properties.FIRST_PRIORITY);
		
		InvestmentStyle parentStyle2 = new InvestmentStyle();
		parentStyle2.getDefinition().setName("P2");
		parentStyle2.setPriority(Properties.SECOND_PRIORITY);
		
		InvestmentStyle childeInvsStyle1 = new InvestmentStyle();
		childeInvsStyle1.getDefinition().setName("Ch1");
		childeInvsStyle1.setPriority(Properties.FIRST_PRIORITY);
		
		InvestmentStyle childeInvsStyle2 = new InvestmentStyle();
		childeInvsStyle2.getDefinition().setName("Ch2");
		childeInvsStyle2.setPriority(Properties.FIRST_PRIORITY);
		
	}
}
