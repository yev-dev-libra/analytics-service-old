package com.libra.apollo.analytics.entity.enums;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.ValueDataFieldType;
import com.libra.apollo.analytics.specification.ValueParameter;
import com.libra.apollo.analytics.specification.ValueParameterSpecification;

public enum OperandBigDecimal implements ValueParameterSpecification<BigDecimal> {

	EQUAL("="){

		@Override
		public Specification<BigDecimal> query(ValueDataFieldType fieldType, ValueParameter<BigDecimal> value) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	NOT_EQUAL("!=") {

		@Override
		public Specification<BigDecimal> query(ValueDataFieldType fieldType, ValueParameter<BigDecimal> value) {
			// TODO Auto-generated method stub
			return null;
		}


	},
	GREATER_THAN(">") {
		@Override
		public Specification<BigDecimal> query(ValueDataFieldType fieldType, ValueParameter<BigDecimal> value) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	GREATER_THAN_OR_EQUAL(">="){

		@Override
		public Specification<BigDecimal> query(ValueDataFieldType fieldType, ValueParameter<BigDecimal> value) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	LESS_THAN("<") {

		@Override
		public Specification<BigDecimal> query(ValueDataFieldType fieldType, ValueParameter<BigDecimal> value) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	LESS_THAN_OR_EQUAL("<="){

		@Override
		public Specification<BigDecimal> query(ValueDataFieldType fieldType, ValueParameter<BigDecimal> value) {
			// TODO Auto-generated method stub
			return null;
		}

		
	},
	DATE_EQUALS(""){

		@Override
		public Specification<BigDecimal> query(ValueDataFieldType fieldType, ValueParameter<BigDecimal> value) {
			// TODO Auto-generated method stub
			return null;
		}

		
	}
	;

	private String symbol;

	private OperandBigDecimal(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	} 


	
	
}
