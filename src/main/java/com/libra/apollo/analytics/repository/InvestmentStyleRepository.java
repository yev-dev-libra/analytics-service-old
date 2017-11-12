package com.libra.apollo.analytics.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.InvestmentStyle;

@Repository
@Transactional
public interface InvestmentStyleRepository extends BaseRepository<InvestmentStyle, Long> {

//	@EntityGraph(value = "InvestmentStyleCondition.priority", type = EntityGraphType.LOAD)
	Optional<InvestmentStyle> findById(Long id);
	
//	public List<Condition> fetchInvestmentStyleConditionsByPriority();
}
