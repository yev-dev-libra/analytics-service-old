package com.libra.apollo.analytics.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.LibraStockIndicator;

@Repository
public interface LibraStockIndicatorRepositoryCustom {

	public List<LibraStockIndicator> findAllBySpecification(Specification<LibraStockIndicator> specification);
}
