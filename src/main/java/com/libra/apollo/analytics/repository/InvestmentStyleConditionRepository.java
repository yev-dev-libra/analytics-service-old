package com.libra.apollo.analytics.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.InvestmentStyleCondition;

@Repository
@Transactional
public interface InvestmentStyleConditionRepository extends CrudRepository<InvestmentStyleCondition, Long> {

}
