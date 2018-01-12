package com.libra.apollo.analytics.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;
import com.libra.apollo.analytics.specification.LibraStockIndicatorSpecification;
import com.libra.apollo.analytics.specification.StampDateSpecification;

public class LibraStockIndicatorRepositoryTest extends AbstractRepositoryTest {

	@Value("${spring.jpa.properties.hibernate.default_schema}")
	private String HIBERNATE_DEFAULT_SCHEMA;

	@Autowired
	private LibraStockIndicatorRepository repository;
	

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
		
		final List<ValueDataFieldType> fields  = Arrays.asList(
				ValueDataFieldType.MAX_STAMP_DATE, 
				ValueDataFieldType.STAR_RATING,
				ValueDataFieldType.FAIR_VALUE,
				ValueDataFieldType.INTRINSIC_VALUE,
				ValueDataFieldType.STOCK_ID
		);
		
		
		//PCT_IN_FAIR_VALUE_RANGE > 0
		//PCT_IN_FAIR_VALUE_RANGE < 1
		
		List<Long> stockIds = Arrays.asList(1L);
		
		//Passed stock ids
		Specification<LibraStockIndicator> idsSpec = LibraStockIndicatorSpecification.idsEquals(ValueDataFieldType.STOCK_ID, stockIds);
		
		final Date prevDateStampDate = previousDate;
		Specification<LibraStockIndicator> equalsOrGreaterThanPrevBussDate = StampDateSpecification.stampDateGreaterThanOrEqual(prevDateStampDate );
		
		//STAR_RATING  >= 3.0
		Specification<LibraStockIndicator> starRatingSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.STAR_RATING, BigDecimal.valueOf(3));
		
		//FAIR_VALUE_CHANGE_1M >= 0
		Specification<LibraStockIndicator> fvChange1m = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.FAIR_VALUE_CHANGE_1M, BigDecimal.valueOf(0));
		
		//INTRINSIC_VALUE_CHANGE_1M >= 0
		Specification<LibraStockIndicator> ivChange1m = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.INTRINSIC_VALUE_CHANGE_1M, BigDecimal.valueOf(0));
		
		//PCT_IN_FAIR_VALUE_RANGE > 0
		Specification<LibraStockIndicator> pctFVRangeGreaterThanZero = LibraStockIndicatorSpecification.fieldGreaterThan(ValueDataFieldType.PCT_IN_FAIR_VALUE_RANGE, BigDecimal.valueOf(0));

		//PCT_IN_FAIR_VALUE_RANGE < 1
		Specification<LibraStockIndicator> pctFVRangeLessThanOne = LibraStockIndicatorSpecification.fieldLessThan(ValueDataFieldType.PCT_IN_FAIR_VALUE_RANGE, BigDecimal.valueOf(1));
		
		
		
		final AnalyticsSpecifications<LibraStockIndicator> specification = new AnalyticsSpecifications<>(idsSpec);
		specification.and(equalsOrGreaterThanPrevBussDate);
		specification.and(starRatingSpec);
		specification.and(fvChange1m);
		specification.and(ivChange1m);
		specification.and(pctFVRangeGreaterThanZero);
		specification.and(pctFVRangeLessThanOne);
		
		List<Tuple> returnValues = repository.findAllBySpecification(fields, specification);
		
		assertThat(returnValues.isEmpty(), is(false));
		
		List<Map<ValueDataFieldType,Object>> values = new ArrayList<>();
		
		for (Tuple tuple : returnValues) {
			
			Map<ValueDataFieldType,Object> valuesDataMap = new HashMap<>(fields.size());
			
			for(ValueDataFieldType field : fields ) {
				String fieldName = field.getFieldName();
				valuesDataMap.put(field, tuple.get(fieldName));
			}
			
			values.add(valuesDataMap);
		}
		
		assertThat(values.isEmpty(), is(false));

	}
	@Test
	public void shouldConstructDynamicQueryForLibraStockIndicatorsWithCriteriaAndBusinessDate() {
		
		final List<ValueDataFieldType> fields  = Arrays.asList(
				ValueDataFieldType.MAX_STAMP_DATE, 
				ValueDataFieldType.STAR_RATING,
				ValueDataFieldType.FAIR_VALUE,
				ValueDataFieldType.INTRINSIC_VALUE,
				ValueDataFieldType.STOCK_ID
		);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, 10, 30);
		Date randomBusinessDate =  new Date(calendar.getTimeInMillis());
		
		//PCT_IN_FAIR_VALUE_RANGE > 0
		//PCT_IN_FAIR_VALUE_RANGE < 1
		
		List<Long> stockIds = Arrays.asList(1L);
		
		//Passed stock ids
		Specification<LibraStockIndicator> idsSpec = LibraStockIndicatorSpecification.idsEquals(ValueDataFieldType.STOCK_ID, stockIds);
		
		final Date prevDateStampDate = previousDate;
		Specification<LibraStockIndicator> equalsOrGreaterThanPrevBussDate = StampDateSpecification.stampDateGreaterThanOrEqual(prevDateStampDate );
		
		//STAR_RATING  >= 3.0
		Specification<LibraStockIndicator> starRatingSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.STAR_RATING, BigDecimal.valueOf(3));
		
		//FAIR_VALUE_CHANGE_1M >= 0
		Specification<LibraStockIndicator> fvChange1m = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.FAIR_VALUE_CHANGE_1M, BigDecimal.valueOf(0));
		
		//INTRINSIC_VALUE_CHANGE_1M >= 0
		Specification<LibraStockIndicator> ivChange1m = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.INTRINSIC_VALUE_CHANGE_1M, BigDecimal.valueOf(0));
		
		//PCT_IN_FAIR_VALUE_RANGE > 0
		Specification<LibraStockIndicator> pctFVRangeGreaterThanZero = LibraStockIndicatorSpecification.fieldGreaterThan(ValueDataFieldType.PCT_IN_FAIR_VALUE_RANGE, BigDecimal.valueOf(0));
		
		//PCT_IN_FAIR_VALUE_RANGE < 1
		Specification<LibraStockIndicator> pctFVRangeLessThanOne = LibraStockIndicatorSpecification.fieldLessThan(ValueDataFieldType.PCT_IN_FAIR_VALUE_RANGE, BigDecimal.valueOf(1));
		
		
		
		final AnalyticsSpecifications<LibraStockIndicator> specification = new AnalyticsSpecifications<>(idsSpec);
		specification.and(equalsOrGreaterThanPrevBussDate);
		specification.and(starRatingSpec);
		specification.and(fvChange1m);
		specification.and(ivChange1m);
		specification.and(pctFVRangeGreaterThanZero);
		specification.and(pctFVRangeLessThanOne);
		
		List<Tuple> returnValues = repository.findAllBySpecification(fields, specification, randomBusinessDate);
		
		assertThat(returnValues.isEmpty(), is(false));
		
		List<Map<ValueDataFieldType,Object>> values = new ArrayList<>();
		
		for (Tuple tuple : returnValues) {
			
			Map<ValueDataFieldType,Object> valuesDataMap = new HashMap<>(fields.size());
			
			for(ValueDataFieldType field : fields ) {
				String fieldName = field.getFieldName();
				valuesDataMap.put(field, tuple.get(fieldName));
			}
			
			values.add(valuesDataMap);
		}
		
		assertThat(values.isEmpty(), is(false));
		
	}
	
}
