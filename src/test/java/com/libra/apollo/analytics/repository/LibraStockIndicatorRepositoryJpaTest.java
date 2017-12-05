package com.libra.apollo.analytics.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.greaterThan;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.TestPropertySource;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.engine.ValueDataFieldType;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.InvestmentStyleParameter;
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.entity.Parameter;
import com.libra.apollo.analytics.entity.enums.CompositionType;
import com.libra.apollo.analytics.specification.AnalyticsSpecifications;
import com.libra.apollo.analytics.specification.LibraStockIndicatorSpecification;
import com.libra.apollo.analytics.specification.StampDateSpecification;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class LibraStockIndicatorRepositoryJpaTest extends AbstractRepositoryTest {

	@Value("${spring.jpa.properties.hibernate.default_schema}")
	private String HIBERNATE_DEFAULT_SCHEMA;

	@Autowired
	private LibraStockIndicatorRepositoryJpa repository;
	
	@Autowired
	private InvestmentStyleRepository invstRepository;

	@PersistenceContext
	private EntityManager entityManager;

	
	private static final Long existedStockId = 131121L;
	private static final Long existedStockIndicatorId = 156355005L;
	private static final List<Long> existedStockIds = new ArrayList<>(Arrays.asList(131121L,131123L));
	
	private Date previousDate;
	
	@Before
	public void setUp() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 10, 14);
		previousDate = new Date(cal.getTimeInMillis());
	}
	
	@Test
	public void testLibraStockIndicatorFindOne() {
		LibraStockIndicator stockIndicator = repository.getOne(existedStockIndicatorId);
		assertThat(stockIndicator, is(IsNull.notNullValue()));
	}

	@Test
	public void shouldFindLibraStockIndicatorByStockId() {
		List<LibraStockIndicator> stockIndicators = repository.findByStockId(existedStockId);
		assertThat(stockIndicators, hasSize(1));
	}

	@Test
	public void shouldFindLibraStockIndicatorByStampDate_2017_11_14() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 10, 15);
		cal.add(Calendar.DATE, -1); // Previous date
		Date date = new Date(cal.getTimeInMillis());
		List<LibraStockIndicator> stockIndicators = repository.findByStampDate(date);
		System.out.println(stockIndicators);
		assertThat(stockIndicators, hasSize(1));
	}

	@Test
	public void shouldFindLibraStockIndicatorByStampDate_2017_11_14_And_StockId() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 10, 15);
		cal.add(Calendar.DATE, -1); // Previous date
		Date date = new Date(cal.getTimeInMillis());
		List<LibraStockIndicator> stockIndicators = repository.findByStockIdAndStampDate(100L, date);
		assertThat(stockIndicators, hasSize(1));
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
	public void shouldFindLibraStockIndicatorByMultipleStockIdsStampDate_2017_11_14_WithSpecificationQuery() {
		
		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateEqual(previousDate);
		Specification<LibraStockIndicator> stockIdsSpec = LibraStockIndicatorSpecification.stockIdsEquals(existedStockIds);
		
		final List<LibraStockIndicator> stockIndicators = repository.findAll(Specifications.where(stampDateSpec).and(stockIdsSpec));
		assertThat(stockIndicators,  hasSize(2));
	}
	@Test
	public void shouldFindLibraStockIndicatorByStampDate_2017_11_14_AndSingleFiedlEqualsWithSpecificationQuery() {
		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateEqual(previousDate);
		ValueDataFieldType fieldType = ValueDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		double discountToFairValue = Double.valueOf("0.0357");
		Specification<LibraStockIndicator> stockIdSpec = LibraStockIndicatorSpecification.fieldEqualsTo(fieldType , discountToFairValue);
		LibraStockIndicator stockIndicator = repository.findOne(Specifications.where(stampDateSpec).and(stockIdSpec));
		assertThat(stockIndicator.getDiscountToFairValue().doubleValue(),  is(discountToFairValue));
	}
	
	@Test
	public void shouldFindLibraStockIndicatorsByStampDate_2017_11_14_AndSingleFiedlGreaterThanWithSpecificationQuery() {
		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateEqual(previousDate);
		ValueDataFieldType fieldType = ValueDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		double discountToFairValue = Double.valueOf("0.0357");
		Specification<LibraStockIndicator> stockIdSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(fieldType , discountToFairValue);
		
		Specification<LibraStockIndicator> spec = Specifications.where(stampDateSpec).and(stockIdSpec);
		
		List<LibraStockIndicator> stockIndicators = repository.findAll(spec);
		assertThat(stockIndicators, hasSize(120));
	}
	
	
//	@Test
//	public void shouldFindLibraStockIndicatorsByStampDate_2017_11_14_AndWithMultipleStocksWithEnumSpecification() {
//		
//		
//		InstrumentDataFieldType fieldType = InstrumentDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
//		double discountToFairValue = Double.valueOf("0.0357");
//		
//		Specification<LibraStockIndicator> disFVSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(fieldType , discountToFairValue);
//
//		Operand operation = Operand.WHERE_EQUALS;
//		Specification<LibraStockIndicator> stampDateSpec = operation.query(InstrumentDataFieldType.STAMP_DATE,previousDate);
//		
//		Specification<LibraStockIndicator> spec = Specifications.where(stampDateSpec).and(disFVSpec);
//		
//		List<LibraStockIndicator> stockIndicators = repository.findAll(spec);
//		assertThat(stockIndicators, hasSize(120));
//	}
//	
	@Test
	public void shouldFindLibraStockIndicatorsByStampDate_2017_11_14_And_With_DISCOUNT_TO_FAIR_VALUE() {
		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateEqual(previousDate);
		
		ValueDataFieldType fieldType = ValueDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		double discountToFairValue = Double.valueOf("0.0357");
		
		Specification<LibraStockIndicator> disFVSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(fieldType , discountToFairValue);
		
		AnalyticsSpecifications<LibraStockIndicator> analyticsSpec = new AnalyticsSpecifications<LibraStockIndicator>(stampDateSpec);
		analyticsSpec.and(disFVSpec);
		
		List<LibraStockIndicator> stockIndicators = repository.findAll(analyticsSpec);
		assertThat(stockIndicators, hasSize(120));
	}
	
	@Test
	public void shouldFindLibraStockIndicatorsByStampDateAndMultipleFields() {
		long startTime = System.nanoTime(); 
		
		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateLessOrGreater(previousDate);
//		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateEqual(previousDate);
		
		ValueDataFieldType disFVfieldType = ValueDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		Double discountToFairValue = Double.valueOf("0.0357");
		
		ValueDataFieldType ivfieldType = ValueDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		Double iv = Double.valueOf("0.2");
		
		
		Specification<LibraStockIndicator> disFVSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(disFVfieldType , discountToFairValue);
		Specification<LibraStockIndicator> ivFVSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(ivfieldType , iv);
		
		AnalyticsSpecifications<LibraStockIndicator> analyticsSpec = new AnalyticsSpecifications<LibraStockIndicator>(stampDateSpec);
		analyticsSpec.and(disFVSpec);
		analyticsSpec.and(ivFVSpec);
		
		List<LibraStockIndicator> stockIndicators = repository.findAll(analyticsSpec);
		assertThat(stockIndicators, hasSize(greaterThan(0)) );
		
		long estimatedTime = System.nanoTime() - startTime;
		
		System.out.println(estimatedTime);
	}
	@Test
	public void shouldFindLibraStockIndicatorsByStampDateAndMultipleFieldsWithCompositionType() {
		long startTime = System.nanoTime(); 
		
		CompositionType whereType = CompositionType.WHERE;
		CompositionType andType = CompositionType.AND;
		CompositionType orType = CompositionType.OR;
		
		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateLessOrGreater(previousDate);
//		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateEqual(previousDate);
		
		ValueDataFieldType disFVfieldType = ValueDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		Double discountToFairValue = Double.valueOf("0.0357");
		
		ValueDataFieldType ivfieldType = ValueDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		Double iv = Double.valueOf("0.2");
		
		
		Specification<LibraStockIndicator> disFVSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(disFVfieldType , discountToFairValue);
		Specification<LibraStockIndicator> ivFVSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(ivfieldType , iv);
		
		AnalyticsSpecifications<LibraStockIndicator> analyticsSpec = new AnalyticsSpecifications<LibraStockIndicator>(stampDateSpec);
		analyticsSpec.and(disFVSpec);
		analyticsSpec.and(ivFVSpec);
		
		List<LibraStockIndicator> stockIndicators = repository.findAll(analyticsSpec);
		assertThat(stockIndicators, hasSize(greaterThan(0)) );
		
		long estimatedTime = System.nanoTime() - startTime;
		
		System.out.println(estimatedTime);
	}
	
	@Test
	public void shouldFindLibraStockIndicatorsByStampDate() {
		Optional<InvestmentStyle> invstStyleOptional = invstRepository.findById(1L);
		InvestmentStyle invstStyle = invstStyleOptional.get();
		Set<InvestmentStyleParameter> properties = invstStyle.getInvestmentStyleProperties();
		
		
		for (InvestmentStyleParameter investmentStyleProperty : properties) {
			final Parameter property = investmentStyleProperty.getParameter();
			
		}
		
		
		Specification<LibraStockIndicator> stampDateSpec = StampDateSpecification.stampDateEqual(previousDate);
		
		ValueDataFieldType fieldType = ValueDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		double discountToFairValue = Double.valueOf("0.0357");
		Specification<LibraStockIndicator> stockIdSpec = LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(fieldType , discountToFairValue);

		Specification<LibraStockIndicator> spec = Specifications.where(stampDateSpec).and(stockIdSpec);
		
		List<LibraStockIndicator> stockIndicators = repository.findAll(spec);
		assertThat(stockIndicators, hasSize(120));
	}

}
