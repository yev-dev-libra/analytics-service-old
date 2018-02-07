package com.libra.apollo.analytics.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.springframework.data.repository.query.Param;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.StockIndicator;
import com.libra.apollo.analytics.projection.MaxDateForStock;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;
import com.libra.apollo.analytics.specification.StockIndicatorSpecification;
import com.libra.apollo.analytics.specification.StampDateSpecification;

public class StockIndicatorRepositoryTest extends AbstractRepositoryTest {

	@Value("${spring.jpa.properties.hibernate.default_schema}")
	private String HIBERNATE_DEFAULT_SCHEMA;

	@Autowired
	private StockIndicatorRepository repository;
	

	@PersistenceContext
	private EntityManager entityManager;

	private static final Long existedStockId = 1L;
	private static final Long existedStockIndicatorId = 1L;
	private static final List<Long> existedStockIds = new ArrayList<>(Arrays.asList(1L));
	
	private Date previousDate;
	private Date maxStampDate;
	
	@Before
	public void setUp() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 1);
		previousDate = new Date(cal.getTimeInMillis());
//		cal.add(Calendar.DATE, -1);
		
		Calendar maxStampDateCal = Calendar.getInstance();
		maxStampDateCal.set(2017, 11, 2);
		maxStampDate = new Date(maxStampDateCal.getTimeInMillis());
	}
	


	@Test
	public void shouldFindStockIndicatorByStockId() {
		List<StockIndicator> stockIndicators = repository.findByStockId(existedStockId);
		assertThat(stockIndicators, hasSize(1));
	}

	@Test
	public void shouldFindStockIndicatorByStampDate_2017_12_01() {
		List<StockIndicator> stockIndicators = repository.findByStampDate(previousDate);
		System.out.println(stockIndicators);
		assertThat(stockIndicators, hasSize(greaterThan(0)) );
	}


	@Test
	public void shouldFindStockIndicatorBySQL() {
		String sql = "SELECT COUNT(si.id) FROM StockIndicator si";
		Query q = entityManager.createQuery(sql);
		long count = (long) q.getSingleResult();
		assertThat(count, is(equalTo(1L)));
	}

	@Test
	public void shouldFindStockIndicatorByStampDate_2017_11_14_WithSpecificationQuery() {
		Specification<StockIndicator> stampDateSpec = StampDateSpecification.stampDateEqual(previousDate);
		Specification<StockIndicator> stockIdSpec = StockIndicatorSpecification.stockIdEquals(existedStockId);
		StockIndicator stockIndicator = repository.findOne(Specifications.where(stampDateSpec).and(stockIdSpec));
		assertThat(stockIndicator,  is(IsNull.notNullValue()));
	}
	
	
	
	@Test
	public void shouldCreateSubqueryWithMaxStampDate() {
		ValueDataFieldType fieldType = ValueDataFieldType.STOCK_ID ;
		Specification<StockIndicator> stockIdSpec = StockIndicatorSpecification.idEquals(fieldType, 1L);
		Specification<StockIndicator> stampDateSpec = StampDateSpecification.stampDateGreatest();
		Specification<StockIndicator> spec = Specifications.where(stampDateSpec).and(stockIdSpec);
		List<StockIndicator> stockIndicators = repository.findAll(spec);
		System.out.println("Stock Indicators Size " + stockIndicators.size());
	}

	@Test
	public void shouldFindStockIndicatorByStampDateWithCriteria() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StockIndicator> criteriaQuery = criteriaBuilder.createQuery(StockIndicator.class); 
		Root<StockIndicator> root = criteriaQuery.from(StockIndicator.class);
		criteriaQuery.select(root);
		criteriaQuery.where(
				criteriaBuilder.equal(root.get("stampDate"), criteriaBuilder.parameter(Date.class, "stampDate")));
		TypedQuery<StockIndicator> query = entityManager.createQuery(criteriaQuery);
		query.setParameter("stampDate", previousDate);
		List<StockIndicator> indicators = query.getResultList();
		assertThat(indicators.isEmpty(), is(false));
	}

	@Test
	public void shouldConstructDynamicQueryForStockIndicatorsWithCriteria() {
		
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
		Specification<StockIndicator> idsSpec = StockIndicatorSpecification.idsEquals(ValueDataFieldType.STOCK_ID, stockIds);
		
		final Date prevDateStampDate = previousDate;
		Specification<StockIndicator> equalsOrGreaterThanPrevBussDate = StampDateSpecification.stampDateGreaterThanOrEqual(prevDateStampDate );
		
		//STAR_RATING  >= 3.0
		Specification<StockIndicator> starRatingSpec = StockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.STAR_RATING, BigDecimal.valueOf(3));
		
		//FAIR_VALUE_CHANGE_1M >= 0
		Specification<StockIndicator> fvChange1m = StockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.FAIR_VALUE_CHANGE_1M, BigDecimal.valueOf(0));
		
		//INTRINSIC_VALUE_CHANGE_1M >= 0
		Specification<StockIndicator> ivChange1m = StockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.INTRINSIC_VALUE_CHANGE_1M, BigDecimal.valueOf(0));
		
		//PCT_IN_FAIR_VALUE_RANGE > 0
		Specification<StockIndicator> pctFVRangeGreaterThanZero = StockIndicatorSpecification.fieldGreaterThan(ValueDataFieldType.PCT_IN_FAIR_VALUE_RANGE, BigDecimal.valueOf(0));

		//PCT_IN_FAIR_VALUE_RANGE < 1
		Specification<StockIndicator> pctFVRangeLessThanOne = StockIndicatorSpecification.fieldLessThan(ValueDataFieldType.PCT_IN_FAIR_VALUE_RANGE, BigDecimal.valueOf(1));
		
		
		
		final AnalyticsSpecifications<StockIndicator> specification = new AnalyticsSpecifications<>(idsSpec);
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
	public void shouldConstructDynamicQueryForStockIndicatorsWithCriteriaAndBusinessDate() {
		
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
		Specification<StockIndicator> idsSpec = StockIndicatorSpecification.idsEquals(ValueDataFieldType.STOCK_ID, stockIds);
		
		final Date prevDateStampDate = previousDate;
		Specification<StockIndicator> equalsOrGreaterThanPrevBussDate = StampDateSpecification.stampDateGreaterThanOrEqual(prevDateStampDate );
		
		//STAR_RATING  >= 3.0
		Specification<StockIndicator> starRatingSpec = StockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.STAR_RATING, BigDecimal.valueOf(3));
		
		//FAIR_VALUE_CHANGE_1M >= 0
		Specification<StockIndicator> fvChange1m = StockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.FAIR_VALUE_CHANGE_1M, BigDecimal.valueOf(0));
		
		//INTRINSIC_VALUE_CHANGE_1M >= 0
		Specification<StockIndicator> ivChange1m = StockIndicatorSpecification.fieldGreaterThanOrEqualTo(ValueDataFieldType.INTRINSIC_VALUE_CHANGE_1M, BigDecimal.valueOf(0));
		
		//PCT_IN_FAIR_VALUE_RANGE > 0
		Specification<StockIndicator> pctFVRangeGreaterThanZero = StockIndicatorSpecification.fieldGreaterThan(ValueDataFieldType.PCT_IN_FAIR_VALUE_RANGE, BigDecimal.valueOf(0));
		
		//PCT_IN_FAIR_VALUE_RANGE < 1
		Specification<StockIndicator> pctFVRangeLessThanOne = StockIndicatorSpecification.fieldLessThan(ValueDataFieldType.PCT_IN_FAIR_VALUE_RANGE, BigDecimal.valueOf(1));
		
		
		
		final AnalyticsSpecifications<StockIndicator> specification = new AnalyticsSpecifications<>(idsSpec);
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
	
	@Test
	public void shouldReturnMaxStampDate() {
		Date maxStampDate = repository.maxDate();
		assertThat(maxStampDate, equalTo(maxStampDate));
	}
	
	@Test
	public void shouldReturnMaxStampDateForStockIdGrouped() {
		List<Long> ids = Arrays.asList(1L,2L,3L);
		
		List<MaxDateForStock> projections = repository.maxDateForStockAsProjection(ids, previousDate);
		
		Map<Long,Date> stockDate = projections.stream().collect(Collectors.toMap(MaxDateForStock::getStockId, MaxDateForStock::getMaxStampDate));
		
		Map<Date,List<MaxDateForStock>> dateStock1 = projections.stream().collect(Collectors.groupingBy(MaxDateForStock::getMaxStampDate));
		
		Map<Date,List<Long>> dateStock2 = projections.stream().collect(Collectors.groupingBy(MaxDateForStock::getMaxStampDate,Collectors.mapping(MaxDateForStock::getStockId,Collectors.toList())));
		
		Map<Date,Set<Long>> dateStock3 = projections.stream().collect(Collectors.groupingBy(MaxDateForStock::getMaxStampDate,Collectors.mapping(MaxDateForStock::getStockId,Collectors.toSet())));
		
		assertThat(projections.isEmpty(), is(false));
	}
	
	
}
