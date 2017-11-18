package com.libra.apollo.analytics.entity.enums;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class OperationTest {

	@Test
	public void testEqualOperation() {
		Operation operation = Operation.EQUAL;

		InstrumentDataFieldType fieldType = InstrumentDataFieldType.FAIR_VALUE;
		String value = "0.2";

		Specification<String> equalSpec = operation.query(fieldType, value);

		Specification<String> spec = Specifications.where(equalSpec);

		assertThat(spec, is(IsNull.notNullValue()));
	}
}
