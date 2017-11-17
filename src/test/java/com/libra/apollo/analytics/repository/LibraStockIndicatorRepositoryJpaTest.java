package com.libra.apollo.analytics.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.same;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.InstrumentDataFieldType;
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.repository.specification.LibraStockIndicatorSpecification;

@Transactional
public class LibraStockIndicatorRepositoryJpaTest extends AbstractRepositoryTest {

	@Value("${spring.jpa.properties.hibernate.default_schema}")
	private String HIBERNATE_DEFAULT_SCHEMA;

	@Autowired
	private LibraStockIndicatorRepositoryJpa repository;

	@PersistenceContext
	private EntityManager entityManager;

	
	private static Long stockId = 131121L;
	
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
		Specification<LibraStockIndicator> stampDateSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stampDateEquals(previousDate);
		Specification<LibraStockIndicator> stockIdSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stockIdEquals(stockId);
		LibraStockIndicator stockIndicator = repository.findOne(Specifications.where(stampDateSpec).and(stockIdSpec));
		assertThat(stockIndicator,  is(IsNull.notNullValue()));
	}
	@Test
	public void shouldFindLibraStockIndicatorByStampDate_2017_11_14_AndSingleFiedlEqualsWithSpecificationQuery() {
		Specification<LibraStockIndicator> stampDateSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stampDateEquals(previousDate);
		InstrumentDataFieldType fieldType = InstrumentDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		double discountToFairValue = Double.valueOf("0.0357");
		Specification<LibraStockIndicator> stockIdSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().fieldEqualsTo(fieldType , discountToFairValue);
		LibraStockIndicator stockIndicator = repository.findOne(Specifications.where(stampDateSpec).and(stockIdSpec));
		assertThat(stockIndicator.getDiscountToFairValue().doubleValue(),  is(discountToFairValue));
	}
	
	@Test
	public void shouldFindLibraStockIndicatorsByStampDate_2017_11_14_AndSingleFiedlGreaterThanWithSpecificationQuery() {
		Specification<LibraStockIndicator> stampDateSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().stampDateEquals(previousDate);
		InstrumentDataFieldType fieldType = InstrumentDataFieldType.DISCOUNT_TO_FAIR_VALUE ;
		double discountToFairValue = Double.valueOf("0.0357");
		Specification<LibraStockIndicator> stockIdSpec = new LibraStockIndicatorSpecification<LibraStockIndicator>().fieldGreaterThanOrEqualTo(fieldType , discountToFairValue);
		List<LibraStockIndicator> stockIndicators = repository.findAll(Specifications.where(stampDateSpec).and(stockIdSpec));
		assertThat(stockIndicators, hasSize(120));
	}

}
