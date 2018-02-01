package com.libra.apollo.analytics.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.entity.ResultParameter;
import com.libra.apollo.analytics.projections.ScreeningResult;

@Repository
public interface ScreeningRepositoryCustom {

	public List<ScreeningResult> findAllScreeningResultsBySpecification(Specification<LibraStockIndicator> specification);
	public List<Tuple> findAllScreeningResultsBySpecification(List<ResultParameter> resultParameters, Specification<LibraStockIndicator> specification);
}
