package com.libra.apollo.analytics.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.Priority;
import com.libra.apollo.analytics.entity.QueryParameter;

@Repository
public interface InvestmentStyleRepository extends BaseRepository<InvestmentStyle, Long> {

	@EntityGraph(value = InvestmentStyle.SHALLOW_GRAPH_NAME, type = EntityGraph.EntityGraphType.FETCH)
	Optional<InvestmentStyle> findById(Long id);
	
	@EntityGraph(value = InvestmentStyle.DEEP_GRAPH_NAME, type = EntityGraph.EntityGraphType.FETCH)
	@Query("SELECT s FROM InvestmentStyle s JOIN s.analytics v  WHERE v.id = :id")
	List<InvestmentStyle> findAllByView(@Param("id") Long id);
	
//	@Query("SELECT p FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp JOIN TREAT ( isp.parameter AS QueryParameter ) p WHERE ais.id = :id")
	@Query("SELECT isp.parameter FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp WHERE ais.id = :id")
	List<QueryParameter> findQueryParametersById(@Param("id") Long id);

	@Query("SELECT isp.parameter FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp WHERE ais.id = :id")
	Iterable<QueryParameter> findIterableQueryParametersById(@Param("id") Long id);
	
	@Query("select new map(isp.priority, isp.parameter) FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp WHERE ais.id = :id")
	List<Map<QueryParameter,Priority>> findQueryParametersByIdWithPriority(@Param("id") Long id);
	
//	@Query("select new list(isp.parameter) FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp WHERE ais.id = :id")
	@Query("SELECT isp.parameter FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp WHERE ais.id = :id")
	SortedSet<QueryParameter> findQueryParametersByIdSorted(@Param("id") Long id);
	
	@Query("SELECT count(isp.parameter.id) FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp WHERE ais.id = :id")
	long countQueryParametersById(@Param("id") Long id);
	
	
	

}
 