package com.libra.apollo.analytics.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUtil;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;

import com.google.common.collect.Lists;
import com.libra.apollo.analytics.AbstractRepositoryDev;
import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.StockIndicator;
import com.libra.apollo.analytics.entity.StockPrice;
import com.libra.apollo.analytics.projection.ApolloIndicators;

public class QueriesPlaygrounDev extends AbstractRepositoryTest {

	// can have multiple unit names
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private StockIndicatorRepository stockIndicatorRepo;
	
//	@Autowired
//	private ApolloIndicatorsRepository apolloIndicatorsRepo;

	@Test
	public void testQueryForIndicatorsWithSelectionAndLimitedById() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> q = cb.createQuery(Tuple.class); // result class
		Root<StockIndicator> indicatorsRoot = q.from(StockIndicator.class);

		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(indicatorsRoot.get("stockId").alias("stockId"));
		selections.add(cb.<Date>greatest(indicatorsRoot.get("stampDate")));
		selections.add(indicatorsRoot.get("fairValue").alias("fairValue"));

		q.multiselect(selections);

		q.where(cb.equal(indicatorsRoot.get("id"), cb.parameter(Long.class, "id")));

		// Adding dynamic parameters that should come from specifications

		q.groupBy(indicatorsRoot.get("stockId"));

		Query query = entityManager.createQuery(q);
		query.setParameter("id", 1L);

		List<Tuple> results = query.getResultList();

		for (Tuple tuple : results) {
			System.out.println(tuple.get("stockId") + " " + tuple.get("fairValue", BigDecimal.class));
		}

	}

	@Test
	public void testQueryForStockIndicators() {
	}
	
	//	@Test
//	public void testContext() {
//		PersistenceUtil util = Persistence.getPersistenceUtil();
//		boolean isObjectLoaded = util.isLoaded(employee);
//		boolean isFieldLoaded = util.isLoaded(employee, "address");
//		entityManager.contains(entity)
//		em.refresh(employee); // managed objects can be reloaded
//	}

	@Test
	public void testQueryToJoinStockIndicatorsWithStockPrices() {
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2018, 01, 31);
		Date stampDate1 = new Date(cal1.getTimeInMillis());
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2018, 01, 31);
		Date stampDate2 = new Date(cal2.getTimeInMillis());

		Long stockId = 100L;
		
		StockIndicator si1 = new StockIndicator();
		si1.setStampDate(stampDate1);
		si1.setStockId(stockId);
		si1.setFairValue(2.0);
		
		
		StockPrice sp1 = new StockPrice();
		sp1.setStampDate(stampDate1);
		sp1.setStockId(stockId);
		sp1.setClosePrice(100.0);
		
		StockIndicator si2 = new StockIndicator();
		si2.setStampDate(stampDate2);
		si2.setStockId(stockId);
		si2.setFairValue(5.0);
		
		
		StockPrice sp2 = new StockPrice();
		sp2.setStampDate(stampDate2);
		sp2.setStockId(stockId);
		sp2.setClosePrice(200.0);
		
		entityManager.persist(si1);
		entityManager.persist(sp1);
		
		entityManager.persist(si2);
		entityManager.persist(sp2);
		
		String qlString = "SELECT si.id, si.stampDate, si.fairValue, sp.closePrice FROM StockIndicator si, StockPrice sp WHERE si.stockId = sp.stockId and si.stampDate = sp.stampDate AND si.stockId = :stockId ";
//		String qlString = "SELECT si.id, si.stampDate, si.fairValue FROM StockIndicator si WHERE si.stockId = :stockId";
		Query query = entityManager.createQuery(qlString );
		query.setParameter("stockId", 100L);
		
		List<Object[]> result = query.getResultList();
		
		for (Object[] objects : result) {
			for(Object o : objects) {
				System.out.println(o);
			}
		}
		
		
	}
	
	@Test
	public void testQueryToJoinStockIndicatorsWithStockPricesAndJPQL() {
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2018, 01, 31);
		Date stampDate1 = new Date(cal1.getTimeInMillis());
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2018, 01, 31);
		Date stampDate2 = new Date(cal2.getTimeInMillis());
		
		Long stockId = 100L;
		
		StockIndicator si1 = new StockIndicator();
		si1.setStampDate(stampDate1);
		si1.setStockId(stockId);
		si1.setFairValue(2.0);
		
		
		StockPrice sp1 = new StockPrice();
		sp1.setStampDate(stampDate1);
		sp1.setStockId(stockId);
		sp1.setClosePrice(100.0);
		
		StockIndicator si2 = new StockIndicator();
		si2.setStampDate(stampDate2);
		si2.setStockId(stockId);
		si2.setFairValue(5.0);
		
		
		StockPrice sp2 = new StockPrice();
		sp2.setStampDate(stampDate2);
		sp2.setStockId(stockId);
		sp2.setClosePrice(200.0);
		
		entityManager.persist(si1);
		entityManager.persist(sp1);
		
		entityManager.persist(si2);
		entityManager.persist(sp2);
		
		List<ApolloIndicators> projection = stockIndicatorRepo.findApolloIndicatorsByStockIdAndBetweenStampDates(stockId,stampDate1, stampDate1 );
		
		for (ApolloIndicators indicator : projection) {
			
			System.out.println(indicator.getCalcPctInFairValueRange());
		}
		
	}
	@Test
	public void testQueryToJoinStockIndicatorsWithStockPricesAndCriteria() {
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2018, 01, 31);
		Date stampDate1 = new Date(cal1.getTimeInMillis());
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2018, 01, 31);
		Date stampDate2 = new Date(cal2.getTimeInMillis());
		
		Long stockId = 100L;
		
		StockIndicator si1 = new StockIndicator();
		si1.setStampDate(stampDate1);
		si1.setStockId(stockId);
		si1.setFairValue(2.0);
		
		
		StockPrice sp1 = new StockPrice();
		sp1.setStampDate(stampDate1);
		sp1.setStockId(stockId);
		sp1.setClosePrice(100.0);
		
		StockIndicator si2 = new StockIndicator();
		si2.setStampDate(stampDate2);
		si2.setStockId(stockId);
		si2.setFairValue(5.0);
		
		
		StockPrice sp2 = new StockPrice();
		sp2.setStampDate(stampDate2);
		sp2.setStockId(stockId);
		sp2.setClosePrice(200.0);
		
		entityManager.persist(si1);
		entityManager.persist(sp1);
		
		entityManager.persist(si2);
		entityManager.persist(sp2);
		
		String qlString = "SELECT si.id, si.stampDate, si.fairValue, sp.closePrice FROM StockIndicator si, StockPrice sp WHERE si.stockId = sp.stockId and si.stampDate = sp.stampDate AND si.stockId = :stockId ";
//		String qlString = "SELECT si.id, si.stampDate, si.fairValue FROM StockIndicator si WHERE si.stockId = :stockId";
		Query query = entityManager.createQuery(qlString );
		query.setParameter("stockId", 100L);
		
		List<Object[]> result = query.getResultList();
		
		for (Object[] objects : result) {
			for(Object o : objects) {
				System.out.println(o);
			}
		}
		
		
	}

}
