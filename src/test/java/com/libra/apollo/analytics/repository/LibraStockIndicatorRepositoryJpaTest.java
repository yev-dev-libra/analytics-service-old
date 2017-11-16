package com.libra.apollo.analytics.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.LibraStockIndicator;

@Transactional
public class LibraStockIndicatorRepositoryJpaTest extends AbstractRepositoryTest {

	@Value("${spring.jpa.properties.hibernate.default_schema}")
	private String HIBERNATE_DEFAULT_SCHEMA;
	
	@Autowired
	private LibraStockIndicatorRepositoryJpa repository;

	@PersistenceContext
	private EntityManager entityManager;


	@Test
	public void testLibraStockIndicatorFindOne() {
		LibraStockIndicator stockIndicator = repository.getOne(1L);
		assertThat(stockIndicator,is(IsNull.notNullValue()));
	}

	@Test
	public void shouldFindLibraStockIndicatorByStockId() {
		List<LibraStockIndicator> stockIndicators = repository.findByStockId(100L);
		assertThat(stockIndicators,hasSize(1));
	}
	
	@Test
	public void shouldFindLibraStockIndicatorByStampDate_2017_11_14() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 10,15);
		cal.add(Calendar.DATE, -1); //Previous date
		Date date = new Date(cal.getTimeInMillis());
		List<LibraStockIndicator> stockIndicators = repository.findByStampDate(date);
		System.out.println(stockIndicators);
		assertThat(stockIndicators,hasSize(1));
	}
	
	@Test
	public void shouldFindLibraStockIndicatorByStampDate_2017_11_14_And_StockId() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 10,15);
		cal.add(Calendar.DATE, -1); //Previous date
		Date date = new Date(cal.getTimeInMillis());
		List<LibraStockIndicator> stockIndicators = repository.findByStockIdAndStampDate(100L, date);
		assertThat(stockIndicators,hasSize(1));
	}
	
	
	@Test
	public void shouldFindLibraStockIndicatorBySQL() {
		String sql = "SELECT COUNT(si.id) FROM LibraStockIndicator si";
		Query q = entityManager.createQuery(sql);
		long count = (long)q.getSingleResult();
		assertThat(count,is(equalTo(1L)));
	}
	
	

}
