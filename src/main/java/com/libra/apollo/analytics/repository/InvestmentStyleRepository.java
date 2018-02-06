package com.libra.apollo.analytics.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.AnalyticsView;
import com.libra.apollo.analytics.entity.FieldParameter;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.Priority;
import com.libra.apollo.analytics.entity.QueryParameter;

@Repository
public interface InvestmentStyleRepository extends BaseRepository<InvestmentStyle, Long> {

	@EntityGraph(value = InvestmentStyle.SHALLOW_GRAPH_NAME, type = EntityGraph.EntityGraphType.FETCH)
	Optional<InvestmentStyle> findById(Long id);
	
	@EntityGraph(value = InvestmentStyle.DEEP_GRAPH_NAME, type = EntityGraph.EntityGraphType.FETCH)
	@Query("SELECT s FROM InvestmentStyle s JOIN s.analytics v  WHERE v.id = :id")
	List<InvestmentStyle> findAllByView(@Param("id") Long id); // TODO - it returns duplicates
	List<InvestmentStyle> findAllByView(AnalyticsView analyticsView);
	
	@Query("SELECT isp.queryParameter FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp WHERE ais.id = :id")
	List<QueryParameter> findQueryParametersById(@Param("id") Long id);

	@Query("SELECT isp.queryParameter FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp WHERE ais.id = :id")
	Iterable<QueryParameter> findIterableQueryParametersById(@Param("id") Long id);
	
	
	@Query("select new map(isp.priority, isp.queryParameter) FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp WHERE ais.id = :id")
	List<Map<QueryParameter,Priority>> findQueryParametersByIdWithPriority(@Param("id") Long id);
	
	@Query("SELECT isp.queryParameter FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp WHERE ais.id = :id")
	SortedSet<QueryParameter> findQueryParametersByIdSorted(@Param("id") Long id);

	@Query("SELECT isfp.fieldParameter FROM InvestmentStyle ais JOIN ais.investmentStyleFieldParameters isfp WHERE ais.id = :id")
	SortedSet<FieldParameter> findFieldParametersById(@Param("id")  Long id);
	
	@Query("SELECT count(isp.queryParameter.id) FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp WHERE ais.id = :id")
	long countQueryParametersById(@Param("id") Long id);

	
	
	
	

}
 