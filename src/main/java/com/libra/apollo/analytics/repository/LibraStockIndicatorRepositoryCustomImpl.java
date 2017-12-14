package com.libra.apollo.analytics.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.entity.LibraStockIndicator;


@Repository
@Transactional(readOnly = true)
public class LibraStockIndicatorRepositoryCustomImpl implements LibraStockIndicatorRepositoryCustom  {

	private static Logger logger = LoggerFactory.getLogger(LibraStockIndicatorRepositoryCustomImpl.class);
	
	@PersistenceContext 
	private EntityManager em;
	
	
	@Override
	public List<LibraStockIndicator> findAllBySpecification(final Specification<LibraStockIndicator> specification) {
		if(logger.isDebugEnabled()) {
			logger.debug("");
		}
		
		return null;
	}

}