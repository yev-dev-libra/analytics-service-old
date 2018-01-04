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
		if(logger.isDebugEnabled()) {
			logger.debug("Saving BigDecimal parameter [" + parameter+ "]");
		}
		em.persist(parameter);

	}

	@Override
	public void saveDoubleQueryParameter(QueryParameter parameter) {
		if(logger.isDebugEnabled()) {
			logger.debug("Saving Double parameter [" + parameter+ "]");
		}
		em.persist(parameter);

	}

	@Override
	public void saveDateQueryParameter(QueryParameter parameter) {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Saving Double parameter [" + parameter+ "]");
		}
		em.persist(parameter);

	}

}
