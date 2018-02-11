package com.libra.apollo.analytics.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.entity.StockIndicator;
import com.libra.apollo.analytics.projection.ApolloIndicators;

//@Repository
//@Transactional(readOnly = true)
public class ApolloIndicatorsRepositoryImpl implements ApolloIndicatorsRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<ApolloIndicators> findApolloIndicatorsByStockIdAndBetweenStampDates(Long stockId, Date fromDate, Date toDate) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ApolloIndicators> q = cb.createQuery(ApolloIndicators.class); // result class
		Root<StockIndicator> indicatorsRoot = q.from(StockIndicator.class);

		return null;
	}

	@Override
	public List<ApolloIndicators> findApolloIndicatorsByStockIdsAndBetweenStampDates(Collection<Long> stockIdList,Date fromDate, Date toDate) {
		
		return null;
	}

}
