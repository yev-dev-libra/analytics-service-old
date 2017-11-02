package com.libra.apollo.analytics.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.AnalyticsType;
import com.libra.apollo.analytics.entity.ApolloAnalytics;

@Repository
@Transactional
public interface AnalyticsRepository extends JpaRepository<ApolloAnalytics,Long> {

	List<ApolloAnalytics> findByType(AnalyticsType type);
	
}
