package com.libra.apollo.analytics.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Tuple;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.AbstractRepositoryTest;
import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.entity.ResultParameter;
import com.libra.apollo.analytics.entity.ScreeningResult;

public class ScreeningRepositoryTest extends AbstractRepositoryTest {

	@Autowired ScreeningRepository repository;

	private Date previousDate;

	@Before
	public void setUp() {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 1);
		previousDate = new Date(cal.getTimeInMillis());
		// cal.add(Calendar.DATE, -1);
	}

	@Test
	public void shouldFindScreeningResultsByStampDate_2017_12_01() {
		List<ScreeningResult> results = repository.findByStampDate(previousDate);
		System.out.println(results);
		assertThat(results, hasSize(greaterThan(0)));
	}
	@Test
	public void shouldFindScreeningResultsByStampDate_2017_12_01_AndStockIds() {
		List<ScreeningResult> results = repository.findByStockIdInAndStampDate(Arrays.asList(1L,2L), previousDate);
		System.out.println(results);
		assertThat(results, hasSize(greaterThan(0)));
	}
	@Test
	public void shouldFindScreeningResultsBySpecification() {
		Specification<LibraStockIndicator> specification = null;
		List<ScreeningResult> results = repository.findAllScreeningResultsBySpecification(specification);
		System.out.println(results);
		assertThat(results, hasSize(greaterThan(0)));
	}
	@Test
	public void shouldFindScreeningResultsBySpecificationWithResultParameters() {
		Specification<LibraStockIndicator> specification = null;
		List<ResultParameter> resultParameters = null;
		List<Tuple> results = repository.findAllScreeningResultsBySpecification(resultParameters ,specification);
		System.out.println(results);
		assertThat(results, hasSize(greaterThan(0)));
	}
}
