package com.libra.apollo.analytics.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.libra.apollo.analytics.entity.Condition;
import com.libra.apollo.analytics.entity.InvestmentStyle;

public interface InvestmentStyleRepository extends BaseRepository<InvestmentStyle, Long> {

//	@EntityGraph(value = "InvestmentStyleCondition.priority", type = EntityGraphType.LOAD)
	Optional<InvestmentStyle> findById(Long id);
	
//	public List<Condition> fetchInvestmentStyleConditionsByPriority();
}
