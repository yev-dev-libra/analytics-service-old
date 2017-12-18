package com.libra.apollo.analytics.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.engine.ValueDataFieldType;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.InvestmentStyleParameter;
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.entity.QueryParameter;
import com.libra.apollo.analytics.entity.enums.CompositionType;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;
import com.libra.apollo.analytics.specification.LibraStockIndicatorSpecification;
import com.libra.apollo.analytics.specification.StampDateSpecification;

public class LibraStockIndicatorRepositoryTest extends AbstractRepositoryTest {

	@Value("${spring.jpa.properties.hibernate.default_schema}")
	private String HIBERNATE_DEFAULT_SCHEMA;

	@Autowired
	private LibraStockIndicatorRepository repository;
	
	@Autowired
	private InvestmentStyleRepository invstRepository;

	@PersistenceContext
	private EntityManager entityManager;

	private static final Long existedStockId = 1L;
	private static final Long existedStockIndicatorId = 1L;
	private static final List<Long> existedStockIds = new ArrayList<>(Arrays.asList(1L));
	
	private Date previousDate;
	
	@Before
	public void setUp() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 1);
		previousDate = new Date(cal.getTimeInMillis());
//		cal.add(Calendar.DATE, -1);
	}
	


	@Test
	public void shouldFindLibraStockIndicatorByStockId() {
		List<LibraStockIndicator> stockIndicators = repository.findByStockId(existedStockId);
		assertThat(stockIndicators, hasSize(1));
	}

	@Test
	public void shouldFindLibraStockIndicatorByStampDate_2017_12_01() {
		List<LibraStockIndicator> stockIndicators = repository.findByStampDate(previousDate);
		System.out.println(stockIndicators);
		assertThat(stockIndicators, hasSize(greaterThan(0)) );
	}


	@Test
	public void shouldFindLibraStockIndicatorBySQL() {
		String sql = "SELECT COUNT(si.id) FROM LibraStockIndicator si";
		Query q = entityManager.createQuery(sql);
		long count = (long) q.getSingleResult();
		assertThat(count, is(equalTo(1L)));
	}

	@Test
	public void shouldFindLibraStockIndicatorByStampDate_2017_11_14_WithSpecificationQuery() {
		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateEqual(previousDate);
		Specification<LibraStockIndicator> stockIdSpec = LibraStockIndicatorSpecification.stockIdEquals(existedStockId);
		LibraStockIndicator stockIndicator = repository.findOne(Specifications.where(stampDateSpec).and(stockIdSpec));
		assertThat(stockIndicator,  is(IsNull.notNullValue()));
	}
	
	
//	@Test
//	public void shouldFindLibraStockIndicatorsByStampDateAndMultipleFieldsWithCompositionType() {
//		long startTime = System.nanoTime(); 
//		
//		CompositionType whereType = CompositionType.WHERE;
//		CompositionType andType = CompositionType.AND;
//		CompositionType orType = CompositionType.OR;
//		
//		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateLessOrGreater(previousDate);
////		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateEqual(previousDate);
//		
//		ValueDataFieldType disFVfieldType = ValueDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
//		Double discountToFairValue = Double.valueOf("0.0357");
//		
//		ValueDataFieldType ivfieldType = ValueDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
//		Double iv = Double.valueOf("0.2");
//		
//		
//		Specification<LibraStockIndicator> disFVSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(disFVfieldType , discountToFairValue);
//		Specification<LibraStockIndicator> ivFVSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(ivfieldType , iv);
//		
//		AnalyticsSpecifications<LibraStockIndicator> analyticsSpec = new AnalyticsSpecifications<LibraStockIndicator>(stampDateSpec);
//		analyticsSpec.and(disFVSpec);
//		analyticsSpec.and(ivFVSpec);
//		
//		List<LibraStockIndicator> stockIndicators = repository.findAll(analyticsSpec);
//		assertThat(stockIndicators, hasSize(greaterThan(0)) );
//		
//		long estimatedTime = System.nanoTime() - startTime;
//		
//		System.out.println(estimatedTime);
//	}
	
	@Test
	public void shouldCreateSubqueryWithMaxStampDate() {
		ValueDataFieldType fieldType = ValueDataFieldType.STOCK_ID ;
		Specification<LibraStockIndicator> stockIdSpec = LibraStockIndicatorSpecification.idEquals(fieldType, 1L);
		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateGreatest();
		Specification<LibraStockIndicator> spec = Specifications.where(stampDateSpec).and(stockIdSpec);
		List<LibraStockIndicator> stockIndicators = repository.findAll(spec);
		System.out.println("Stock Indicators Size " + stockIndicators.size());
	}

	@Test
	public void shouldFindLibraStockIndicatorByStampDateWithCriteria() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<LibraStockIndicator> criteriaQuery = criteriaBuilder.createQuery(LibraStockIndicator.class); 
		Root<LibraStockIndicator> root = criteriaQuery.from(LibraStockIndicator.class);
		criteriaQuery.select(root);
		criteriaQuery.where(
				criteriaBuilder.equal(root.get("stampDate"), criteriaBuilder.parameter(Date.class, "stampDate")));
		TypedQuery<LibraStockIndicator> query = entityManager.createQuery(criteriaQuery);
		query.setParameter("stampDate", previousDate);
		List<LibraStockIndicator> indicators = query.getResultList();
		assertThat(indicators.isEmpty(), is(false));
	}

	@Test
	public void shouldConstructDynamicQueryForLibraStockIndicatorsWithCriteria() {

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
		query.setParameter("stockId", existedStockId);

		List<LibraStockIndicator> indicators = query.getResultList();
		assertThat(indicators.isEmpty(), is(false));

	}

	@Test
	public void shouldFindLibraStockIndicatorJoinedByMaxStampDateAndGroupedByStockIdWithSpecification() {
		
		String stampDateFieldName = ValueDataFieldType.STAMP_DATE.getFieldName();
		String stockIdFieldName = ValueDataFieldType.STOCK_ID.getFieldName();

		Specification<LibraStockIndicator> maxStampDateSpec =  (root, query, cb) -> {
			
			 Subquery<Long> sq = query.subquery(Long.class);
			 Root<LibraStockIndicator> rootSubquery = sq.from(LibraStockIndicator.class);
			 sq.select(rootSubquery.get(stockIdFieldName));
			 sq.select(rootSubquery.get(stampDateFieldName));
			 return cb.equal(root.get(stockIdFieldName), sq);
			
		};
		List<LibraStockIndicator> indicators = repository.findAll(maxStampDateSpec);		
		assertThat(indicators.isEmpty(), is(false));
	}
}
