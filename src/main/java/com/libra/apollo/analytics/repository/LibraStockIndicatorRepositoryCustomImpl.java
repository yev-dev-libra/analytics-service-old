package com.libra.apollo.analytics.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

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
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;
import com.libra.apollo.analytics.specification.StampDateSpecification;


@Repository
@Transactional(readOnly = true)
public class LibraStockIndicatorRepositoryCustomImpl implements LibraStockIndicatorRepositoryCustom  {

	private static Logger logger = LoggerFactory.getLogger(LibraStockIndicatorRepositoryCustomImpl.class);
	
	@PersistenceContext 
	private EntityManager entityManager;
	
	
	@Override
	public List<LibraStockIndicator> findAllBySpecification(final Specification<LibraStockIndicator> specification) {
		throw new UnsupportedOperationException();
	}

	/*
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Tuple> findAllBySpecification(final Collection<ValueDataFieldType> fields, final AnalyticsSpecifications<LibraStockIndicator> specification) {
		if(logger.isDebugEnabled()) {
			logger.debug("");
		}
		List<Map<ValueDataFieldType,Object>> returnValues = new ArrayList<>();
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		
		Root<LibraStockIndicator> indicatorsRoot = q.from(LibraStockIndicator.class);
		
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
		
		//Dynamic query construction to extract defined query parameters
		q.where(specification.toPredicate(indicatorsRoot, q, cb));
		
		final String stockIdFieldName = ValueDataFieldType.STOCK_ID.getFieldName();
		
		q.groupBy(indicatorsRoot.get(stockIdFieldName));
		
		Query query = entityManager.createQuery(q);
		
		return query.getResultList();
	}

	@Override
	public List<Tuple> findAllBySpecification(final Collection<ValueDataFieldType> fields, final AnalyticsSpecifications<LibraStockIndicator> specification, final Date stampDate) {
		if(logger.isDebugEnabled()) {
			logger.debug("");
		}
		
		Specification<LibraStockIndicator> equalsOrGreaterThanPrevBussDate = StampDateSpecification.stampDateGreaterThanOrEqual(stampDate );
		specification.and(equalsOrGreaterThanPrevBussDate);
		
		return findAllBySpecification(fields, specification);
	}

	@Override
	public Stream<Tuple> streamAllBySpecification(final Collection<ValueDataFieldType> fields, final AnalyticsSpecifications<LibraStockIndicator> specification, final Date runDate) {
		
		throw new UnsupportedOperationException();
//		if(logger.isDebugEnabled()) {
//			logger.debug("");
//		}
//		List<Map<ValueDataFieldType,Object>> returnValues = new ArrayList<>();
//		
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Tuple> q = cb.createQuery(Tuple.class); 
//		
//		Root<LibraStockIndicator> indicatorsRoot = q.from(LibraStockIndicator.class);
//		
//		List<Selection<?>> selections = new ArrayList<Selection<?>>(); 
//		
//		for(ValueDataFieldType field : fields ) {
//			
//			if(field.equals(ValueDataFieldType.MAX_STAMP_DATE)) {
//				
//				final String stampDateFieldName = ValueDataFieldType.STAMP_DATE.getFieldName();
//				
//				selections.add(cb.<Date>greatest(indicatorsRoot.get(stampDateFieldName)).alias(ValueDataFieldType.MAX_STAMP_DATE.getFieldName()) );
//			}
//			else {
//				selections.add(indicatorsRoot.get(field.getFieldName()).alias(field.getFieldName())  );
//			}
//			
//		}
//		
//		q.multiselect(selections);
//		
//		//Dynamic query construction to extract defined query parameters
//		q.where(specification.toPredicate(indicatorsRoot, q, cb));
//		
//		final String stockIdFieldName = ValueDataFieldType.STOCK_ID.getFieldName();
//		
//		q.groupBy(indicatorsRoot.get(stockIdFieldName));
//		
//		Query query = entityManager.createQuery(q);
//		
//		Stream<Tuple> s = query.getResultList().stream();
//		
//		Tuple tuple = s.findAny().get();
////		
////		for (Tuple tuple : results) {
////			
////			Map<ValueDataFieldType,Object> valuesDataMap = new HashMap<>(fields.size());
////			
////			for(ValueDataFieldType field : fields ) {
////				String fieldName = field.getFieldName();
////				valuesDataMap.put(field, tuple.get(fieldName));
////			}
////			
////			returnValues.add(valuesDataMap);
////		}
//		
//		return null;
	}

	
}
