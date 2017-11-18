package com.libra.apollo.analytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.enums.AnalyticsType;

@Repository
public interface AnalyticsRepository extends JpaRepository<ApolloAnalytics,Long> {

	List<ApolloAnalytics> findByType(AnalyticsType type);
	
}
