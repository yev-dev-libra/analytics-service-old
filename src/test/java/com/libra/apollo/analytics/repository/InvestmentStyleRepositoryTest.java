package com.libra.apollo.analytics.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.Condition;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.InvestmentStyleCondition;
import com.libra.apollo.analytics.entity.OperationCondition;
import com.libra.apollo.analytics.entity.ParameterCondition;

public class InvestmentStyleRepositoryTest extends AbstractRepositoryTest {

	@Autowired private InvestmentStyleRepository repository;
	
	
	@Ignore
	@Test
	public void shouldBeSortedByPriorities() {
		
	}
	
	@Test
	public void shouldRetrieveAllInvestmentStyle() {
		List<InvestmentStyle> allInvStyles = repository.findAll();
		assertThat(allInvStyles, hasSize(1));
	}
	
	@Test
	public void shouldConstructConditionsForInvestmentStyle() {
		Optional<InvestmentStyle> invstStyleOptional = repository.findById(1L);
		InvestmentStyle invstStyle = invstStyleOptional.get();
		SortedSet<InvestmentStyleCondition> conditions = invstStyle.getInvestmentStyleConditions();
		assertThat(conditions, hasSize(3));
		
		for(InvestmentStyleCondition invstCondition : conditions) {
			final Condition condition = invstCondition.getCondition();
			if(condition instanceof OperationCondition) {
				System.out.println((OperationCondition)condition);
			}
			else if(condition instanceof ParameterCondition) {
				System.out.println((ParameterCondition)condition);
			}
		}
	}
	
	
}
