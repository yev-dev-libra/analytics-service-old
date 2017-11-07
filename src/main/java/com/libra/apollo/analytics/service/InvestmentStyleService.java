package com.libra.apollo.analytics.service;

import com.libra.apollo.analytics.entity.InvestmentStyle;

public interface InvestmentStyleService {

	public boolean isPriorityPresent(Integer priorityId);
	public boolean isInvestmentStylePresent(String name);
	public InvestmentStyle findInvestmentStyleByName(String name);
}
