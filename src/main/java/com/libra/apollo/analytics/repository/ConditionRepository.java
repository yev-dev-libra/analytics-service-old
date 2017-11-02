package com.libra.apollo.analytics.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.Condition;

@Repository
@Transactional
public interface ConditionRepository<T extends Condition> extends JpaRepository<T, Long> {

}
