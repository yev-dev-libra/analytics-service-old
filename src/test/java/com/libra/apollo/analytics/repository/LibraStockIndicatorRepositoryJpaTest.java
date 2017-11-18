package com.libra.apollo.analytics.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hamcrest.core.IsNull;
import org.hibernate.validator.constraints.CompositionType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.Parameter;
import com.libra.apollo.analytics.entity.InvestmentStyle;
import com.libra.apollo.analytics.entity.InvestmentStyleCondition;
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.entity.OperandCondition;
import com.libra.apollo.analytics.entity.FilterParameter;
import com.libra.apollo.analytics.entity.enums.InstrumentDataFieldType;
import com.libra.apollo.analytics.entity.enums.Operation;
import com.libra.apollo.analytics.repository.specification.LibraStockIndicatorSpecification;

@Transactional
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
		LibraStockIndicator stockIndicator = repository.getOne(1L);
		assertThat(stockIndicator, is(IsNull.notNullValue()));
	}

	@Test
	public void shouldFindLibraStockIndicatorByStockId() {
		List<LibraStockIndicator> stockIndicators = repository.findByStockId(100L);
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
		Specification<LibraStockIndicator> stampDateSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stampDateEqual(previousDate);
		Specification<LibraStockIndicator> stockIdSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stockIdEquals(existedStockId);
		LibraStockIndicator stockIndicator = repository.findOne(Specifications.where(stampDateSpec).and(stockIdSpec));
		assertThat(stockIndicator,  is(IsNull.notNullValue()));
	}
	
	@Test
	public void shouldFindLibraStockIndicatorByMultipleStockIdsStampDate_2017_11_14_WithSpecificationQuery() {
		final LibraStockIndicatorSpecification<LibraStockIndicator> analyticsSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>();
		
		Specification<LibraStockIndicator> stampDateSpec = analyticsSpec.stampDateEqual(previousDate);
		Specification<LibraStockIndicator> stockIdsSpec = analyticsSpec.stockIdsEquals(existedStockIds);
		
		final List<LibraStockIndicator> stockIndicators = repository.findAll(Specifications.where(stampDateSpec).and(stockIdsSpec));
		assertThat(stockIndicators,  hasSize(2));
	}
	@Test
	public void shouldFindLibraStockIndicatorByStampDate_2017_11_14_AndSingleFiedlEqualsWithSpecificationQuery() {
		Specification<LibraStockIndicator> stampDateSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stampDateEqual(previousDate);
		InstrumentDataFieldType fieldType = InstrumentDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		double discountToFairValue = Double.valueOf("0.0357");
		Specification<LibraStockIndicator> stockIdSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().fieldEqualsTo(fieldType , discountToFairValue);
		LibraStockIndicator stockIndicator = repository.findOne(Specifications.where(stampDateSpec).and(stockIdSpec));
		assertThat(stockIndicator.getDiscountToFairValue().doubleValue(),  is(discountToFairValue));
	}
	
	@Test
	public void shouldFindLibraStockIndicatorsByStampDate_2017_11_14_AndSingleFiedlGreaterThanWithSpecificationQuery() {
		Specification<LibraStockIndicator> stampDateSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stampDateEqual(previousDate);
		InstrumentDataFieldType fieldType = InstrumentDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		double discountToFairValue = Double.valueOf("0.0357");
		Specification<LibraStockIndicator> stockIdSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().fieldGreaterThanOrEqualTo(fieldType , discountToFairValue);
		
		Specification<LibraStockIndicator> spec = Specifications.where(stampDateSpec).and(stockIdSpec);
		
		List<LibraStockIndicator> stockIndicators = repository.findAll(spec);
		assertThat(stockIndicators, hasSize(120));
	}
	
	@Test
	public void shouldFindLibraStockIndicatorsByStampDate_2017_11_14_AndWithMultipleStocksWithSpecificationQuery() {
		Specification<LibraStockIndicator> stampDateSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stampDateEqual(previousDate);
		InstrumentDataFieldType fieldType = InstrumentDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		double discountToFairValue = Double.valueOf("0.0357");
		Specification<LibraStockIndicator> stockIdSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().fieldGreaterThanOrEqualTo(fieldType , discountToFairValue);
		
		Specification<LibraStockIndicator> spec = Specifications.where(stampDateSpec).and(stockIdSpec);
		
		List<LibraStockIndicator> stockIndicators = repository.findAll(spec);
		assertThat(stockIndicators, hasSize(120));
	}
	
	@Test
	public void shouldFindLibraStockIndicatorsByStampDate_2017_11_14_AndWithMultipleStocksWithEnumSpecification() {
		
		Specification<LibraStockIndicator> stampDateSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stampDateEqual(previousDate);
		
		InstrumentDataFieldType fieldType = InstrumentDataFieldType.FAIR_VALUE ;
		String value = "0.2";
		
		Operation operation = Operation.EQUAL;

		Specification<LibraStockIndicator> equalSpec = operation.query(fieldType, value);
		
		Specification<LibraStockIndicator> spec = Specifications.where(stampDateSpec).and(equalSpec);
		
		List<LibraStockIndicator> stockIndicators = repository.findAll(spec);
		assertThat(stockIndicators, hasSize(120));
	}
	
	@Test
	public void shouldFindLibraStockIndicatorsByStampDate_2017_11_14_AndSingleFiedlGreaterThanWithSpecificationQueryWithConversion() {
		Optional<InvestmentStyle> invstStyleOptional = invstRepository.findById(1L);
		InvestmentStyle invstStyle = invstStyleOptional.get();
		Set<InvestmentStyleCondition> conditions = invstStyle.getInvestmentStyleConditions();
		
		Map<String,List<Specification<LibraStockIndicator>>> specifications = new HashMap<>();
		
		for (InvestmentStyleCondition investmentStyleCondition : conditions) {
			
			final Parameter condition = investmentStyleCondition.getCondition();
			final Specification<LibraStockIndicator> stampDateSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stampDateEqual(previousDate);
			
			if(condition instanceof OperandCondition) {
				final Operation operator = ((OperandCondition) condition).getOperator();
				
				
			}
			else if(condition instanceof FilterParameter) {
				
			}
			else if(condition instanceof FilterParameter) {
				
			}
			else
				throw new UnsupportedOperationException();
			
		}
		
		
		for(Map.Entry<String, List<Specification<LibraStockIndicator>>> entry : specifications.entrySet()) {
			
		}
		
		
		Specification<LibraStockIndicator> stampDateSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stampDateEqual(previousDate);
		
		InstrumentDataFieldType fieldType = InstrumentDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		double discountToFairValue = Double.valueOf("0.0357");
		Specification<LibraStockIndicator> stockIdSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().fieldGreaterThanOrEqualTo(fieldType , discountToFairValue);

		Specification<LibraStockIndicator> spec = Specifications.where(stampDateSpec).and(stockIdSpec);
		
		List<LibraStockIndicator> stockIndicators = repository.findAll(spec);
		assertThat(stockIndicators, hasSize(120));
	}

}
