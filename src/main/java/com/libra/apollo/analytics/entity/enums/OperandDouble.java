package com.libra.apollo.analytics.entity.enums;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.specification.ValueParameter;
import com.libra.apollo.analytics.specification.ValueParameterSpecification;

public enum OperandDouble implements ValueParameterSpecification<Double> {

	EQUAL("=") {

		@Override
		public Specification<Double> query(ValueParameter value) {
			throw new UnsupportedOperationException();
		}

	},
	NOT_EQUAL("!=") {

		@Override
		public Specification<Double> query(ValueParameter value) {
			throw new UnsupportedOperationException();
		}

	},
	GREATER_THAN(">") {

		@Override
		public Specification<Double> query(ValueParameter value) {
			throw new UnsupportedOperationException();
		}

	},
	GREATER_THAN_OR_EQUAL(">=") {

		@Override
		public Specification<Double> query(ValueParameter value) {
			throw new UnsupportedOperationException();
		}

	},
	LESS_THAN("<") {

		@Override
		public Specification<Double> query(ValueParameter value) {
			throw new UnsupportedOperationException();
		}

	},
	LESS_THAN_OR_EQUAL("<=") {

		@Override
		public Specification<Double> query(ValueParameter value) {
			throw new UnsupportedOperationException();
		}

	};

	private String symbol;

	private OperandDouble(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

}
