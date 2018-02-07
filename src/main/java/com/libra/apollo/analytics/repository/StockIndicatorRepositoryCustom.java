package com.libra.apollo.analytics.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.StockIndicator;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;

@Repository
public interface StockIndicatorRepositoryCustom {

	public List<StockIndicator> findAllBySpecification(Specification<StockIndicator> specification);
	public List<Tuple> findAllBySpecification(List<ValueDataFieldType> requestedFields, AnalyticsSpecifications<StockIndicator> specification);
	public List<Tuple> findAllBySpecification(List<ValueDataFieldType> requestedFields, AnalyticsSpecifications<StockIndicator> specification, Date runDate);
	
}
