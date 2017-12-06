package com.libra.apollo.analytics.repository;

import java.util.Optional;
import java.util.SortedSet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.QueryParameter;

@Repository
public interface InvestmentStyleRepository extends BaseRepository<InvestmentStyle, Long> {

	Optional<InvestmentStyle> findOptionalById(Long id);
	
	@Query("SELECT aqp FROM InvestmentStyle as is JOIN InvestmentStyleParameter isp on ais.id = aisp.investment_style_id inner join analytics.analytics_query_parameter aqp on aisp.parameter_id = aqp.id  where ais.id = 1")
	SortedSet<QueryParameter> findQueryParametersById(Long id);
	
//	Set<Map<InvestmentStyle,List<QueryParameter>>> findAllParametersById(Long id);
}
