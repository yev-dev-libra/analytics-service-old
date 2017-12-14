package com.libra.apollo.analytics.engine;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.entity.enums.CompositionType;
import com.libra.apollo.analytics.specification.StampDateSpecification;
import com.libra.apollo.analytics.specification.ValueParameter;
import com.libra.apollo.analytics.specification.ValueParameterSpecification;
import com.libra.apollo.analytics.utils.Properties;

public class ValueDate implements Value {

	private final Date value;
	private final OperandDate operand;
	private final CompositionType compositionType = CompositionType.WHERE;
	private final ValueDataFieldType fieldType = ValueDataFieldType.STAMP_DATE;

	public ValueDate(Date value, OperandDate operand) {
		super();
		this.value = value;
		this.operand = operand;
	}

	@Override
	public Optional<Object> getValue() {
		return Optional.of(value);
	}

	@Override
	public CompositionType getCompositionType() {
		return compositionType;
	}

	@Override
	public ValueDataFieldType getFieldType() {
		return fieldType;
	}

	public OperandDate getOperand() {
		return operand;
	}

	public enum OperandDate implements ValueParameterSpecification<Date> {

		EQUAL("=") {

			@Override
			public Specification<Date> query(final ValueParameter value) {
				if (value.getClazz().equals(Date.class)) {

					LocalDate today = LocalDate.now(ZoneId.of(Properties.DEFAULT_TIME_ZONE_ID)).minus(1,ChronoUnit.DAYS);

					Date stampDate = (Date) value.getValue().get();
					return StampDateSpecification.stampDateEqual(stampDate);
				} else
					throw new UnsupportedOperationException();
			}
		},
		NOT_EQUAL("!=") {

			@Override
			public Specification<Date> query(ValueParameter value) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		GREATER_THAN(">") {

			@Override
			public Specification<Date> query(ValueParameter value) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		GREATER_THAN_OR_EQUAL(">=") {

			@Override
			public Specification<Date> query(ValueParameter value) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		LESS_THAN("<") {

			@Override
			public Specification<Date> query(ValueParameter value) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		LESS_THAN_OR_EQUAL("<=") {

			@Override
			public Specification<Date> query(ValueParameter value) {
				// TODO Auto-generated method stub
				return null;
			}

		};

		private String symbol;

		private OperandDate(String symbol) {
			this.symbol = symbol;
		}

		public String getSymbol() {
			return symbol;
		}

	}

	@Override
	public int compareTo(Value o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
