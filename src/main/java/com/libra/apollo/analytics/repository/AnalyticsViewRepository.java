package com.libra.apollo.analytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.AnalyticsView;

@Repository
public interface AnalyticsViewRepository extends JpaRepository<AnalyticsView, Long> {

	List<AnalyticsView> findAllByAnalytics(Long id);
	
	List<AnalyticsView> findAllByAnalyticsIn(List<Long> ids);

}
