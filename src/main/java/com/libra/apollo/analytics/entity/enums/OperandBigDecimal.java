package com.libra.apollo.analytics.entity.enums;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.base.Preconditions;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.exceptions.MissingParameterValueException;
import com.libra.apollo.analytics.specification.LibraStockIndicatorSpecification;
import com.libra.apollo.analytics.specification.ValueParameter;
import com.libra.apollo.analytics.specification.ValueParameterSpecification;

public enum OperandBigDecimal implements ValueParameterSpecification<BigDecimal> {

	EQUAL("="){

		@Override
		public Specification<BigDecimal> query(ValueParameter value) {
			Preconditions.checkArgument(value.getClazz().equals(BigDecimal.class), "Passed parameter is not of the right type");
			Preconditions.checkArgument(value.getFieldType() != null, "ValueDataFieldType can not be null");
			
			final ValueDataFieldType fieldType = value.getFieldType();
			final BigDecimal decimalValue = (BigDecimal)value.getValue().orElseThrow( () -> new MissingParameterValueException() );
			return LibraStockIndicatorSpecification.fieldEqualsTo(fieldType, decimalValue);
		}
	},
	NOT_EQUAL("!=") {

		@Override
		public Specification<BigDecimal> query(ValueParameter value) {
			Preconditions.checkArgument(value.getClazz().equals(BigDecimal.class), "Passed parameter is not of the right type");
			Preconditions.checkArgument(value.getFieldType() != null, "ValueDataFieldType can not be null");
			
			final ValueDataFieldType fieldType = value.getFieldType();
			final BigDecimal decimalValue = (BigDecimal)value.getValue().orElseThrow( () -> new MissingParameterValueException() );
			return LibraStockIndicatorSpecification.fieldNotEqualsTo(fieldType, decimalValue);
		}


	},
	GREATER_THAN(">") {
		@Override
		public Specification<BigDecimal> query(ValueParameter value) {
			Preconditions.checkArgument(value.getClazz().equals(BigDecimal.class), "Passed parameter is not of the right type");
			Preconditions.checkArgument(value.getFieldType() != null, "ValueDataFieldType can not be null");
			
			final ValueDataFieldType fieldType = value.getFieldType();
			final BigDecimal decimalValue = (BigDecimal)value.getValue().orElseThrow( () -> new MissingParameterValueException() );
			return LibraStockIndicatorSpecification.fieldGreaterThan(fieldType, decimalValue);
		}

	},
	GREATER_THAN_OR_EQUAL(">="){

		@Override
		public Specification<BigDecimal> query(ValueParameter value) {
			Preconditions.checkArgument(value.getClazz().equals(BigDecimal.class), "Passed parameter is not of the right type");
			Preconditions.checkArgument(value.getFieldType() != null, "ValueDataFieldType can not be null");
			
			final ValueDataFieldType fieldType = value.getFieldType();
			final BigDecimal decimalValue = (BigDecimal)value.getValue().orElseThrow( () -> new MissingParameterValueException() );
			return LibraStockIndicatorSpecification.fieldGreaterThanOrEqualTo(fieldType, decimalValue);
		}

	},
	LESS_THAN("<") {

		@Override
		public Specification<BigDecimal> query(ValueParameter value) {
			Preconditions.checkArgument(value.getClazz().equals(BigDecimal.class), "Passed parameter is not of the right type");
			Preconditions.checkArgument(value.getFieldType() != null, "ValueDataFieldType can not be null");
			
			final ValueDataFieldType fieldType = value.getFieldType();
			final BigDecimal decimalValue = (BigDecimal)value.getValue().orElseThrow( () -> new MissingParameterValueException() );
			return LibraStockIndicatorSpecification.fieldLessThan(fieldType, decimalValue);
		}

	},
	LESS_THAN_OR_EQUAL("<="){

		@Override
		public Specification<BigDecimal> query(ValueParameter value) {
			Preconditions.checkArgument(value.getClazz().equals(BigDecimal.class), "Passed parameter is not of the right type");
			Preconditions.checkArgument(value.getFieldType() != null, "ValueDataFieldType can not be null");
			
			final ValueDataFieldType fieldType = value.getFieldType();
			final BigDecimal decimalValue = (BigDecimal)value.getValue().orElseThrow( () -> new MissingParameterValueException() );
			return LibraStockIndicatorSpecification.fieldLessThan(fieldType, decimalValue);
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
