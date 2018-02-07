package com.libra.apollo.analytics.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.StockIndicator;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;
import com.libra.apollo.analytics.specification.StampDateSpecification;


@Repository
@Transactional(readOnly = true)
public class StockIndicatorRepositoryCustomImpl implements StockIndicatorRepositoryCustom  {

	private static Logger logger = LoggerFactory.getLogger(StockIndicatorRepositoryCustomImpl.class);
	
	@PersistenceContext 
	private EntityManager entityManager;
	
	
	@Override
	public List<StockIndicator> findAllBySpecification(final Specification<StockIndicator> specification) {
		throw new UnsupportedOperationException();
	}

	/*
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Tuple> findAllBySpecification(final List<ValueDataFieldType> fields, final AnalyticsSpecifications<StockIndicator> specification) {
		if(logger.isDebugEnabled()) {
			logger.debug("");
		}
		
		//TODO: get query for a max date
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		
		Root<StockIndicator> indicatorsRoot = q.from(StockIndicator.class);
		
		List<Selection<?>> selections = new ArrayList<Selection<?>>(); 
		
		for(ValueDataFieldType field : fields ) {
			
			if(field.equals(ValueDataFieldType.MAX_STAMP_DATE)) {
				
				final String stampDateFieldName = ValueDataFieldType.STAMP_DATE.getFieldName();
				
				selections.add(cb.<Date>greatest(indicatorsRoot.get(stampDateFieldName)).alias(ValueDataFieldType.MAX_STAMP_DATE.getFieldName()) );
			}
			else {
				selections.add(indicatorsRoot.get(field.getFieldName()).alias(field.getFieldName())  );
			}
			
		}
		
		q.multiselect(selections);
		
		q.where(specification.toPredicate(indicatorsRoot, q, cb));
		
		final String stockIdFieldName = ValueDataFieldType.STOCK_ID.getFieldName();
		
		q.groupBy(indicatorsRoot.get(stockIdFieldName));
		
		Query query = entityManager.createQuery(q);
		
		return query.getResultList();
	}

	@Override
	public List<Tuple> findAllBySpecification(final List<ValueDataFieldType> fields, final AnalyticsSpecifications<StockIndicator> specification, final Date stampDate) {
		if(logger.isDebugEnabled()) {
			logger.debug("");
		}
		
		Specification<StockIndicator> stampDateLessOrGreater = StampDateSpecification.stampDateLessOrGreater(stampDate );
		specification.and(stampDateLessOrGreater);
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		
		Root<StockIndicator> indicatorsRoot = q.from(StockIndicator.class);
		
		List<Selection<?>> selections = new ArrayList<Selection<?>>(); 
		
		for(ValueDataFieldType field : fields ) {
			selections.add(indicatorsRoot.get(field.getFieldName()).alias(field.getFieldName())  );
		}
		
		q.multiselect(selections);
		
		q.where(specification.toPredicate(indicatorsRoot, q, cb));
		
		Query query = entityManager.createQuery(q);
		
		return query.getResultList();
		
	}


	
}
