package com.libra.apollo.analytics.entity.enums;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.ValueDataFieldType;
import com.libra.apollo.analytics.specification.StampDateSpecification;
import com.libra.apollo.analytics.specification.ValueParameter;
import com.libra.apollo.analytics.specification.ValueParameterSpecification;
import com.libra.apollo.analytics.utils.Properties;

public enum OperandDate implements ValueParameterSpecification<Date> {

	EQUAL("="){
		
		@Override
		public Specification<Date> query(ValueDataFieldType fieldType, ValueParameter<Date> value) {
			if(value.getClazz().equals(Date.class)) {
				
				LocalDate today = LocalDate.now ( ZoneId.of ( Properties.DEFAULT_TIME_ZONE_ID ) ).minus(1, ChronoUnit.DAYS);
				
				
				Date stampDate = (Date)value.getValue().get();
				return StampDateSpecification.stampDateEqual(stampDate);
			}
			else
				throw new UnsupportedOperationException();
		}
	},
	NOT_EQUAL("!=") {
		
		@Override
		public Specification<Date> query(ValueDataFieldType fieldType, ValueParameter<Date> value) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	GREATER_THAN(">") {

		@Override
		public Specification<Date> query(ValueDataFieldType fieldType, ValueParameter<Date> value) {
			// TODO Auto-generated method stub
			return null;
		}

		
	},
	GREATER_THAN_OR_EQUAL(">="){

		@Override
		public Specification<Date> query(ValueDataFieldType fieldType, ValueParameter<Date> value) {
			// TODO Auto-generated method stub
			return null;
		}


	},
	LESS_THAN("<") {

		@Override
		public Specification<Date> query(ValueDataFieldType fieldType, ValueParameter<Date> value) {
			// TODO Auto-generated method stub
			return null;
		}


	},
	LESS_THAN_OR_EQUAL("<="){

		@Override
		public Specification<Date> query(ValueDataFieldType fieldType, ValueParameter<Date> value) {
			// TODO Auto-generated method stub
			return null;
		}

	}
	;

	private String symbol;

	private OperandDate(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	} 

}
