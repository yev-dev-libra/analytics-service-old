package com.libra.apollo.analytics.entity.enums;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.ValueDataFieldType;
import com.libra.apollo.analytics.specification.ValueParameter;
import com.libra.apollo.analytics.specification.ValueParameterSpecification;

public enum OperandDouble implements ValueParameterSpecification<Double> {

	EQUAL("=") {

		@Override
		public Specification<Double> query(ValueDataFieldType fieldType, ValueParameter<Double> value) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	NOT_EQUAL("!=") {

		@Override
		public Specification<Double> query(ValueDataFieldType fieldType, ValueParameter<Double> value) {
			// TODO Auto-generated method stub
			return null;
		}


	},
	GREATER_THAN(">") {

		@Override
		public Specification<Double> query(ValueDataFieldType fieldType, ValueParameter<Double> value) {
			// TODO Auto-generated method stub
			return null;
		}


	},
	GREATER_THAN_OR_EQUAL(">=") {

		@Override
		public Specification<Double> query(ValueDataFieldType fieldType, ValueParameter<Double> value) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	LESS_THAN("<") {

		@Override
		public Specification<Double> query(ValueDataFieldType fieldType, ValueParameter<Double> value) {
			// TODO Auto-generated method stub
			return null;
		}


	},
	LESS_THAN_OR_EQUAL("<=") {

		@Override
		public Specification<Double> query(ValueDataFieldType fieldType, ValueParameter<Double> value) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	DATE_EQUALS("") {

		@Override
		public Specification<Double> query(ValueDataFieldType fieldType, ValueParameter<Double> value) {
			// TODO Auto-generated method stub
			return null;
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
