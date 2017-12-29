package com.libra.apollo.analytics.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.engine.core.Value;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.LibraStockIndicator;

@Repository
public interface LibraStockIndicatorRepositoryCustom {

	public List<LibraStockIndicator> findAllBySpecification(Specification<LibraStockIndicator> specification);
	public List<Map<ValueDataFieldType,Object>> findAllBySpecification(List<ValueDataFieldType> values, Specification<LibraStockIndicator> specification);
	public List<Map<ValueDataFieldType,Object>> findAllBySpecification(List<ValueDataFieldType> values, Specification<LibraStockIndicator> specification, Date runDate);
}
