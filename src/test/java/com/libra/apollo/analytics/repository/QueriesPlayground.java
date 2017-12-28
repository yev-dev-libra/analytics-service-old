package com.libra.apollo.analytics.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.LibraStockIndicator;

public class QueriesPlayground extends AbstractRepositoryTest{

	//can have multiple unit names
	@PersistenceContext 
	private EntityManager entityManager;
	
	@Test
	public void testQueryForInvestmentStylesWithCriteria() {
//		Session session = (Session) entityManager.getDelegate();
//		Criteria criteria = session.createCriteria(InvestmentStyle.class);
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<InvestmentStyle> criteriaQuery = criteriaBuilder.createQuery(InvestmentStyle.class); //result class
		Root<InvestmentStyle> root = criteriaQuery.from(InvestmentStyle.class);
		
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"),criteriaBuilder.parameter(Long.class, "id")));
		
		Query query = entityManager.createQuery(criteriaQuery);
		query.setParameter("id", 1L);
		
		InvestmentStyle style = (InvestmentStyle) query.getSingleResult();
		System.out.println(style);
		
	}
	
	@Test
	public void testQueryForIndicatorsWithCriteriaAndObjectArray() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> q = cb.createQuery(Object[].class); //result class
		Root<LibraStockIndicator> r = q.from(LibraStockIndicator.class);

		q.multiselect(r.get("stockId"), cb.greatest(r.get("stampDate")));
		q.groupBy(r.get("stockId"));
		q.where(cb.equal(r.get("stockId"),cb.parameter(Long.class, "stockId")) );
		q.groupBy(r.get("stockId"));
		
		Query query = entityManager.createQuery(q);
		query.setParameter("stockId", 1L);
		query.getResultList();
		
		
	}
	
	@Test
	public void testQueryForIndicatorsWithCriteriaAndTuples() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> q = cb.createQuery(Tuple.class); //result class
		Root<LibraStockIndicator> r = q.from(LibraStockIndicator.class);
		
		List<Selection<?>> selections = new ArrayList<Selection<?>>(); 
		selections.add(r.<Long>get("stockId").alias("stockId") );
		selections.add(cb.<Date>greatest(r.get("stampDate")) );
		selections.add(r.<BigDecimal>get("fairValue").alias("fairValue")  );
		
		q.multiselect(selections);
		
		q.groupBy(r.get("stockId"));
		
		List<Tuple> results = entityManager.createQuery(q).getResultList();
		
		for (Tuple tuple : results) {
			System.out.println( tuple.get("stockId") + " " + tuple.get(1));
		}
		
	}
	@Test
	public void testQueryForIndicatorsWithSelection() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> q = cb.createQuery(Tuple.class); //result class
		Root<LibraStockIndicator> r = q.from(LibraStockIndicator.class);
		
		List<Selection<?>> selections = new ArrayList<Selection<?>>(); 
		selections.add(r.get("stockId").alias("stockId") );
		selections.add(cb.<Date>greatest(r.get("stampDate")) );
		selections.add(r.get("fairValue").alias("fairValue")  );
		
		q.multiselect(selections);
		
		q.groupBy(r.get("stockId"));
		
		List<Tuple> results = entityManager.createQuery(q).getResultList();
		
		for (Tuple tuple : results) {
			System.out.println( tuple.get("stockId") + " " + tuple.get("fairValue", BigDecimal.class));
		}
		
	}
	
	@Test
	public void testQueryForIndicatorsWithSelectionAndLimitedById() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> q = cb.createQuery(Tuple.class); //result class
		Root<LibraStockIndicator> indicatorsRoot = q.from(LibraStockIndicator.class);
		
		List<Selection<?>> selections = new ArrayList<Selection<?>>(); 
		selections.add(indicatorsRoot.get("stockId").alias("stockId") );
		selections.add(cb.<Date>greatest(indicatorsRoot.get("stampDate")) );
		selections.add(indicatorsRoot.get("fairValue").alias("fairValue")  );
		
		q.multiselect(selections);
		
		q.where(cb.equal(indicatorsRoot.get("id"),cb.parameter(Long.class, "id")));
		
		//Adding dynamic parameters that should come from specifications
		
		q.groupBy(indicatorsRoot.get("stockId"));
		
		Query query = entityManager.createQuery(q);
		query.setParameter("id", 1L);
		
		List<Tuple> results = query.getResultList();
		
		for (Tuple tuple : results) {
			System.out.println( tuple.get("stockId") + " " + tuple.get("fairValue", BigDecimal.class));
		}
		
	}
	
	@Test
	public void testQueryForIndicatorsWithSelectionAndSpecification() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> q = cb.createQuery(Tuple.class); //result class
		Root<LibraStockIndicator> indicatorsRoot = q.from(LibraStockIndicator.class);
		
		List<Selection<?>> selections = new ArrayList<Selection<?>>(); 
		selections.add(indicatorsRoot.get("stockId").alias("stockId") );
		selections.add(cb.<Date>greatest(indicatorsRoot.get("stampDate")) );
		selections.add(indicatorsRoot.get("fairValue").alias("fairValue")  );
		
		q.multiselect(selections);
		
		
		final Collection<Long> stockIds = Arrays.asList(1L,2L,3L);
		
		Specification<LibraStockIndicator> spec = (root, query, criteriaBuilder) -> {
			final Path<Long> stocks = root.<Long>get("stockId");
			return stocks.in(stockIds);
		};
		
		q.where(spec.toPredicate(indicatorsRoot, q, cb));
		
		//Adding dynamic parameters that should come from specifications
		
		q.groupBy(indicatorsRoot.get("stockId"));
		
		Query query = entityManager.createQuery(q);
		
		List<Tuple> results = query.getResultList();
		
		for (Tuple tuple : results) {
			System.out.println( tuple.get("stockId") + " " + tuple.get("fairValue", BigDecimal.class));
		}
		
	}
		
	
}
