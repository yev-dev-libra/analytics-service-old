package com.libra.apollo.analytics.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.LibraStockIndicator;

public class LibraStockIndicatorRepositoryTest extends AbstractRepositoryTest {

	 @Autowired
	 @Qualifier("customLibraStockIndicatorRepository")
	 private CustomLibraStockIndicatorRepository repository;

	@PersistenceContext
	private EntityManager entityManager;

	private Date previousDate;
	private static Long stockId = 131121L;
;

	@Before
	public void setUp() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 01);
		previousDate = new Date(cal.getTimeInMillis());
	}

	@Test
	public void shouldFindLibraStockIndicatorByStampDate_2017_11_14_WithCriteria() {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<LibraStockIndicator> criteriaQuery = criteriaBuilder.createQuery(LibraStockIndicator.class); // result
																													// class
		Root<LibraStockIndicator> root = criteriaQuery.from(LibraStockIndicator.class);

		criteriaQuery.select(root);
		criteriaQuery.where(
				criteriaBuilder.equal(root.get("stampDate"), criteriaBuilder.parameter(Date.class, "stampDate")));

		TypedQuery<LibraStockIndicator> query = entityManager.createQuery(criteriaQuery);
		query.setParameter("stampDate", previousDate);

		List<LibraStockIndicator> indicators = query.getResultList();
		assertThat(indicators.isEmpty(), is(false));

	}

	//https://www.programcreek.com/java-api-examples/index.php?api=javax.persistence.criteria.Predicate
	@Test
	public void testDynamicQueryForLibraStockIndicatorsWithCriteria() {

		final List<Predicate> andPredicates = new ArrayList<Predicate>();
		final List<Predicate> orPredicates = new ArrayList<Predicate>();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<LibraStockIndicator> criteriaQuery = criteriaBuilder.createQuery(LibraStockIndicator.class); // result
		Root<LibraStockIndicator> root = criteriaQuery.from(LibraStockIndicator.class);
		
		Predicate stampDatePredicate = criteriaBuilder.equal(root.get("stampDate"), criteriaBuilder.parameter(Date.class, "stampDate"));
		Predicate stockIdPredicate = criteriaBuilder.equal(root.get("stockId"),criteriaBuilder.parameter(Long.class, "stockId"));

		Predicate predicate = criteriaBuilder.and(stampDatePredicate, stockIdPredicate);

		criteriaQuery.select(root);
		criteriaQuery.where(predicate);

		
		TypedQuery<LibraStockIndicator> query = entityManager.createQuery(criteriaQuery);

		query.setParameter("stampDate", previousDate);
		query.setParameter("stockId", stockId);

		List<LibraStockIndicator> indicators = query.getResultList();
		assertThat(indicators.isEmpty(), is(false));

	}
	
	//https://javabeat.net/jpa-2-metamodel-eclipse/
	@Test
	public void testDynamicQueryForLibraStockIndicatorsWithCriteriaTuple() {
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery( Tuple.class);
		
		Root<LibraStockIndicator> root = criteriaQuery.from(LibraStockIndicator.class);
//		Path<Long> idPath = root.get(  );
//		Path<String> nickNamePath = root.get( Person_.nickName);
//
//		criteria.multiselect( idPath, nickNamePath );
//		
//		Predicate stampDatePredicate = criteriaBuilder.equal(root.get("stampDate"), criteriaBuilder.parameter(Date.class, "stampDate"));
//		Predicate stockIdPredicate = criteriaBuilder.equal(root.get("stockId"),criteriaBuilder.parameter(Long.class, "stockId"));
//		
//		Predicate predicate = criteriaBuilder.and(stampDatePredicate, stockIdPredicate);
//		
//		criteriaQuery.select(root);
//		criteriaQuery.where(predicate);
//		
//		
//		TypedQuery<LibraStockIndicator> query = entityManager.createQuery(criteriaQuery);
//		
//		query.setParameter("stampDate", previousDate);
//		query.setParameter("stockId", stockId);
//		
//		List<LibraStockIndicator> indicators = query.getResultList();
//		assertThat(indicators.isEmpty(), is(false));
		
	}
	
	
	//https://stackoverflow.com/questions/31740508/jpa-dynamic-criteria-api-query
	//I can create a detached query and pass it over later on http://what-when-how.com/hibernate/advanced-query-options-hibernate/
	
}
