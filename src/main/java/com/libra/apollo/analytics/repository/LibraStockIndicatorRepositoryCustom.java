package com.libra.apollo.analytics.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.Tuple;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;

@Repository
public interface LibraStockIndicatorRepositoryCustom {

	public List<LibraStockIndicator> findAllBySpecification(Specification<LibraStockIndicator> specification);
	public List<Tuple> findAllBySpecification(List<ValueDataFieldType> requestedFields, AnalyticsSpecifications<LibraStockIndicator> specification);
	public List<Tuple> findAllBySpecification(List<ValueDataFieldType> requestedFields, AnalyticsSpecifications<LibraStockIndicator> specification, Date runDate);
	public Stream<Tuple> streamAllBySpecification(List<ValueDataFieldType> requestedFields, AnalyticsSpecifications<LibraStockIndicator> specification, Date runDate);
}
