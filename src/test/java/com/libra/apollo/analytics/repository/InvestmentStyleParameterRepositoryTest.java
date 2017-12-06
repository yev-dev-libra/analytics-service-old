package com.libra.apollo.analytics.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.InvestmentStyleParameter;

public class InvestmentStyleParameterRepositoryTest extends AbstractRepositoryTest  {
	
	@Autowired
	private InvestmentStyleParameterRepository repository;
	
	@Test
	public void shouldRetrieveAllInvestmentStyle() {
		List<InvestmentStyleParameter> allInvStyles = repository.findAll();
		assertThat(2, greaterThan(1));
	}
	

}
