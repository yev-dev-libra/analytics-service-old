package com.libra.apollo.analytics.repository;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.AnalyticsType;
import com.libra.apollo.analytics.entity.ApolloAnalytics;
import com.libra.apollo.analytics.entity.RunType;

@Repository
public interface AnalyticsRepository extends JpaRepository<ApolloAnalytics,Long> {

	List<ApolloAnalytics> findAll();
	
	ApolloAnalytics findById(Long id);
	
	@Query("Select a.definition.name from ApolloAnalytics a")
	List<String> getAnalyticsNames();
	
	@Query("Select a from ApolloAnalytics a where a.definition.name = :name")
	List<ApolloAnalytics> findAllByName(@Param("name") String name);
	
	List<ApolloAnalytics> findAllByType(AnalyticsType type);
	
	List<ApolloAnalytics> findAllByRunType(RunType type);
	

	@Query("Select a from ApolloAnalytics a where a.type = :type and a.runType = :runType")
	List<ApolloAnalytics> findByTypeAndByRunType(@Param("type") AnalyticsType type, @Param("runType") RunType runType);
	
	@EntityGraph(value = ApolloAnalytics.SHALLOW_GRAPH_NAME, type = EntityGraph.EntityGraphType.FETCH)
	@Query("Select a from ApolloAnalytics a where a.type = :type and a.runType = :runType")
	List<ApolloAnalytics> findAllByTypeAndByRunType(@Param("type") AnalyticsType type, @Param("runType") RunType runType);
	
	@EntityGraph(value = ApolloAnalytics.SHALLOW_GRAPH_NAME, type = EntityGraph.EntityGraphType.FETCH)
	@Query("Select a from ApolloAnalytics a where a.type = :type or a.runType = :runType")
	List<ApolloAnalytics> findAllByTypeOrByRunType(@Param("type") AnalyticsType type, @Param("runType") RunType runType);
	
}
