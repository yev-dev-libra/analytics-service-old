package com.libra.apollo.analytics.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.entity.ResultParameter;
import com.libra.apollo.analytics.entity.ScreeningResult;

@Repository
@Transactional(readOnly = true)
public class ScreeningRepositoryCustomImpl implements ScreeningRepositoryCustom {


	private static Logger logger = LoggerFactory.getLogger(ScreeningRepositoryCustomImpl.class);
	
	@PersistenceContext 
	private EntityManager em;
	
	@Override
	public List<ScreeningResult> findAllScreeningResultsBySpecification( Specification<LibraStockIndicator> specification) {
		if(logger.isDebugEnabled()) {
			logger.debug("");
		}
		throw new UnsupportedOperationException("Method has not been implemented yet");
	}

	@Override
	public List<Tuple> findAllScreeningResultsBySpecification(List<ResultParameter> resultParameters,Specification<LibraStockIndicator> specification) {
		if(logger.isDebugEnabled()) {
			logger.debug("");
		}
		throw new UnsupportedOperationException("Method has not been implemented yet");
	}

}
