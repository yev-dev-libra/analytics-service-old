package com.libra.apollo.analytics.entity.enums;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.Date;
import java.util.Calendar;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class OperationTest {


	@Test
	public void testEqualOperation() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 10, 14);
		Date previousDate = new Date(cal.getTimeInMillis()); 
		Operand operation = Operand.WHERE_STAMP_DATE_EQUAL;
		Specification<String> equalSpec = operation.query(previousDate);
		Specification<String> spec = Specifications.where(equalSpec);
		assertThat(spec, is(IsNull.notNullValue()));
	}
}
