package com.libra.apollo.analytics.entity.enums;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.libra.apollo.analytics.engine.ValueDataFieldType;
import com.libra.apollo.analytics.specification.ValueParameter;

public class OperandDateTest {

	@Test
	public void testEqualOperation() {

		Calendar cal = Calendar.getInstance();
		cal.set(2017, 10, 14);
		final Date date = cal.getTime();

		final OperandDate operation = OperandDate.EQUAL;
		final ValueDataFieldType fieldType = ValueDataFieldType.STAMP_DATE;

		final ValueParameter value = new ValueParameter() {

			@Override
			public Optional<Object> getValue() {
				return Optional.of(date);
			}

			@Override
			public int compareTo(ValueParameter o) {
				return 0;
			}

			@Override
			public <T> Specification<T> getSpecification(ValueParameter parameter) {
				return null;
			}
		};

		Specification<Date> dateSpec = operation.query(fieldType, value);

		Specification<Date> spec = Specifications.where(dateSpec);
		assertThat(spec, is(IsNull.notNullValue()));
	}
}
