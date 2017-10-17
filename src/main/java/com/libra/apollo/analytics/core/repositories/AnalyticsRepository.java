package com.libra.apollo.analytics.core.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.core.entities.Analytics;

@Repository
@Transactional
public interface AnalyticsRepository extends JpaRepository<Analytics,Long> {

}
