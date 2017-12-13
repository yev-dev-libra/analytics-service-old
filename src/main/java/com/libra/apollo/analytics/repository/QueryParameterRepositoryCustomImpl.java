package com.libra.apollo.analytics.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.libra.apollo.analytics.entity.QueryParameter;

public class QueryParameterRepositoryCustomImpl implements QueryParameterRepositoryCustom {

	private static Logger logger = LoggerFactory.getLogger(LibraStockIndicatorRepositoryCustomImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public void saveBigDecimalQueryParameter(QueryParameter parameter) {
		em.persist(parameter);

	}

	@Override
	public void saveDoubleQueryParameter(QueryParameter parameter) {
		em.persist(parameter);

	}

	@Override
	public void saveDataQueryParameter(QueryParameter parameter) {
		em.persist(parameter);

	}

}
