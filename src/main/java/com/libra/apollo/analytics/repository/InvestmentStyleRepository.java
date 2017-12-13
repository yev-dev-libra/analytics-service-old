package com.libra.apollo.analytics.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.engine.Value;
import com.libra.apollo.analytics.entity.InvestmentStyle;

@Repository
public interface InvestmentStyleRepository extends BaseRepository<InvestmentStyle, Long> {

	@EntityGraph(value = InvestmentStyle.SHALLOW_GRAPH_NAME, type = EntityGraph.EntityGraphType.FETCH)
	Optional<InvestmentStyle> findById(Long id);
	
	@EntityGraph(value = InvestmentStyle.DEEP_GRAPH_NAME, type = EntityGraph.EntityGraphType.FETCH)
	@Query("SELECT s FROM InvestmentStyle s JOIN s.analytics v  WHERE v.id = :id")
	List<InvestmentStyle> findAllByView(@Param("id") Long id);
	
	@Query("SELECT p FROM InvestmentStyle ais JOIN ais.investmentStyleParameters isp JOIN isp.parameter p WHERE ais.id = :id")
	List<Value> findQueryParametersById(@Param("id") Long id);
	

}
 