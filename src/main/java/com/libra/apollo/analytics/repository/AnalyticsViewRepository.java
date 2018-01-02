package com.libra.apollo.analytics.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.libra.apollo.analytics.entity.AnalyticsView;
import com.libra.apollo.analytics.entity.ApolloAnalytics;

@Repository
public interface AnalyticsViewRepository extends JpaRepository<AnalyticsView, Long> {

	List<AnalyticsView> findAll();
	List<AnalyticsView> findAllByAnalytics(ApolloAnalytics apolloAnalytics);
	AnalyticsView findByAnalyticsAndId(ApolloAnalytics apolloAnalytics, Long viewId);
	List<AnalyticsView> findAllByAnalyticsIn(List<Long> ids);

}
