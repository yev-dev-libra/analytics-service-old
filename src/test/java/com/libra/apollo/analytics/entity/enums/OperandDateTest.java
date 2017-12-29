package com.libra.apollo.analytics.entity.enums;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.libra.apollo.analytics.engine.core.Value;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.specification.ValueParameter;

public class OperandDateTest {

	@Test
	public void testEqualOperation() {

		final OperandBigDecimal operation = OperandBigDecimal.EQUAL;
		final BigDecimal bg = BigDecimal.valueOf(0);
		final ValueParameter value = new ValueParameter() {

			@Override
			public Optional<Object> getValue() {
				return Optional.of(bg);
			}

			@Override
			public Specification<BigDecimal> getSpecification() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public CompositionType getCompositionType() {
				return CompositionType.AND;
			}

			@Override
			public ValueDataFieldType getFieldType() {
				return ValueDataFieldType.FAIR_VALUE;
			}

			@Override
			public int compareTo(Value o) {
				// TODO Auto-generated method stub
				return 0;
			}

		};

		Specification<BigDecimal> fvSpec = operation.query(value);

		Specification<BigDecimal> spec = Specifications.where(fvSpec);

		assertThat(spec, is(IsNull.notNullValue()));
	}
}
