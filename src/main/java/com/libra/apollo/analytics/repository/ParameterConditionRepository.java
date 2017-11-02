package com.libra.apollo.analytics.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.ParameterCondition;

@Repository
@Transactional
public interface ParameterConditionRepository extends ConditionRepository<ParameterCondition> {

}
