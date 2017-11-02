package com.libra.apollo.analytics.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.SortDirectionCondition;

@Repository
@Transactional
public interface SortDirectionConditionRepository extends ConditionRepository<SortDirectionCondition> {

}
