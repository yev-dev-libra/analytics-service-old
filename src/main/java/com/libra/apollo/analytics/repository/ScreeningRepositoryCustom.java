package com.libra.apollo.analytics.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.StockIndicator;
import com.libra.apollo.analytics.entity.ResultParameter;
import com.libra.apollo.analytics.projection.ScreeningResult;

@Repository
public interface ScreeningRepositoryCustom {

	public List<ScreeningResult> findAllScreeningResultsBySpecification(Specification<StockIndicator> specification);
	public List<Tuple> findAllScreeningResultsBySpecification(List<ResultParameter> resultParameters, Specification<StockIndicator> specification);
}
