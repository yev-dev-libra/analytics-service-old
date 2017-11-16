package com.libra.apollo.analytics.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

	// @Autowired
	// @Qualifier("libraStockIndicatorRepository")
	// private LibraStockIndicatorRepository repository;

	@PersistenceContext
	private EntityManager entityManager;

	private Date previousDate;
	private static Long stockId = 100L;

	@Before
	public void setUp() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 10, 14);
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
		assertThat(indicators, hasSize(1));

	}

	@Test
	public void testDynamicQueryForLibraStockIndicatorsWithCriteria() {

		final List<Predicate> andPredicates = new ArrayList<Predicate>();
		final List<Predicate> orPredicates = new ArrayList<Predicate>();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<LibraStockIndicator> criteriaQuery = criteriaBuilder.createQuery(LibraStockIndicator.class); // result
		Root<LibraStockIndicator> root = criteriaQuery.from(LibraStockIndicator.class);

		Predicate stampDatePredicate = criteriaBuilder.equal(root.get("stampDate"),
				criteriaBuilder.parameter(Date.class, "stampDate"));
		Predicate stockIdPredicate = criteriaBuilder.equal(root.get("stockId"),
				criteriaBuilder.parameter(Long.class, "stockId"));

		Predicate predicate = criteriaBuilder.and(stampDatePredicate, stockIdPredicate);

		criteriaQuery.select(root);
		criteriaQuery.where(predicate);

		TypedQuery<LibraStockIndicator> query = entityManager.createQuery(criteriaQuery);

		query.setParameter("stampDate", previousDate);
		query.setParameter("stockId", stockId);

		List<LibraStockIndicator> indicators = query.getResultList();
		assertThat(indicators, hasSize(1));

	}
}
