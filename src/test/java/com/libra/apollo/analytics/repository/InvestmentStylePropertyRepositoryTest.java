package com.libra.apollo.analytics.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.InvestmentStyleProperty;

public class InvestmentStylePropertyRepositoryTest extends AbstractRepositoryTest  {
	
	@Autowired
	private InvestmentStyleConditionRepository repository;
	
	@Test
	public void shouldRetrieveAllInvestmentStyle() {
		List<InvestmentStyleProperty> allInvStyles = repository.findAll();
		assertThat(allInvStyles, hasSize(3));
	}
	

}
