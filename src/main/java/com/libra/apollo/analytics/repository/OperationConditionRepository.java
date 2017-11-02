package com.libra.apollo.analytics.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.OperationCondition;

@Repository
@Transactional
public interface OperationConditionRepository extends ConditionRepository<OperationCondition> {

}
