package com.libra.apollo.analytics.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Selection;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.engine.Value;
import com.libra.apollo.analytics.engine.ValueDataFieldType;
import com.libra.apollo.analytics.entity.LibraStockIndicator;

@Repository
public interface LibraStockIndicatorRepositoryCustom {

	public List<LibraStockIndicator> findAllBySpecification(Specification<LibraStockIndicator> specification);
	public List<Map<ValueDataFieldType,Value>> findAllBySpecification(List<Selection<?>> selections, Specification<LibraStockIndicator> specification);
}
