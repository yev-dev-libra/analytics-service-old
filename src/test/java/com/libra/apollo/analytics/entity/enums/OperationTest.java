package com.libra.apollo.analytics.entity.enums;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.libra.apollo.analytics.specification.ValueParameter;

public class OperationTest {


	@Test
	public void testEqualOperation() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 10, 14);
		final Date previousDate = new Date(cal.getTimeInMillis()); 
		final Operand operation = Operand.EQUAL;
		final InstrumentDataFieldType fieldType = InstrumentDataFieldType.STAMP_DATE;
		
		ValueParameter previousDateValue = new ValueParameter() {

			@Override
			public int compareTo(ValueParameter o) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getValue() {
				return previousDate;
			}

			@Override
			public <T> Specification<T> getSpecification() {
				return operation.queryByDate(fieldType);
			}

			@Override
			public <T> Specification<T> getSpecification(ValueParameter parameter) {
				throw new UnsupportedOperationException();
			}
			
		};
		Specification<Date> dateSpec = operation.queryByDate(InstrumentDataFieldType.STAMP_DATE,previousDateValue );
		Specification<Date> spec = Specifications.where(dateSpec);
		assertThat(spec, is(IsNull.notNullValue()));
	}
}
