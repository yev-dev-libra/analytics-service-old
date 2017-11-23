package com.libra.apollo.analytics.entity.enums;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.specification.LibraStockIndicatorSpecification;
import com.libra.apollo.analytics.specification.StampDateSpecification;
import com.libra.apollo.analytics.specification.ValueParameter;

public enum Operand {

	EQUAL("="){

		@Override
		public <T> Specification<T> queryByDate(InstrumentDataFieldType fieldType) {
			//TODO: return proveious buisness date
			throw new UnsupportedOperationException();
		}
		
		@Override
		public <T> Specification<T> queryByDate(InstrumentDataFieldType fieldType, ValueParameter value) {
			if(value.getClazz().equals(Date.class)) {
				Date stampDate = (Date)value.getValue();
				return StampDateSpecification.stampDateEqual(stampDate);
			}
			else
				throw new UnsupportedOperationException();
		}
		@Override
		public <T> Specification<T> queryById(InstrumentDataFieldType fieldType, ValueParameter value) {
			if(value.getClazz().equals(Long.class)) {
				final Long stockId = (Long) value.getValue();
				return LibraStockIndicatorSpecification.idEquals(fieldType, stockId);
			}
			else
				throw new UnsupportedOperationException();
		}

		@Override
		public <T> Specification<T> queryByIds(InstrumentDataFieldType fieldType, ValueParameter value) {
			
			return null;
		}


		@Override
		public <T> Specification<T> queryByBigDecimal(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> Specification<T> queryByBigDouble(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}


		
	},
	NOT_EQUAL("!=") {

		@Override
		public <T> Specification<T> queryByDate(InstrumentDataFieldType fieldType) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T> Specification<T> queryByDate(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public <T> Specification<T> queryById(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> Specification<T> queryByIds(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public <T> Specification<T> queryByBigDecimal(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> Specification<T> queryByBigDouble(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}


	},
	GREATER_THAN(">") {

		@Override
		public <T> Specification<T> queryByDate(InstrumentDataFieldType fieldType) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T> Specification<T> queryByDate(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T> Specification<T> queryById(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> Specification<T> queryByIds(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}
		

		@Override
		public <T> Specification<T> queryByBigDecimal(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> Specification<T> queryByBigDouble(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}



	
	},
	LESS_THAN("<") {

		@Override
		public <T> Specification<T> queryById(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> Specification<T> queryByIds(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}

		
		@Override
		public <T> Specification<T> queryByDate(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> Specification<T> queryByDate(InstrumentDataFieldType fieldType) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T> Specification<T> queryByBigDecimal(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> Specification<T> queryByBigDouble(InstrumentDataFieldType fieldType, ValueParameter value) {
			// TODO Auto-generated method stub
			return null;
		}

		


	};

	private String symbol;

	private Operand(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	} 

	public abstract <T> Specification<T> queryByDate(final InstrumentDataFieldType fieldType);
	public abstract <T> Specification<T> queryByDate(final InstrumentDataFieldType fieldType, final ValueParameter value);
	public abstract <T> Specification<T> queryById(final InstrumentDataFieldType fieldType, final ValueParameter value);
	public abstract <T> Specification<T> queryByIds(final InstrumentDataFieldType fieldType, final ValueParameter value);
	public abstract <T> Specification<T> queryByBigDecimal(final InstrumentDataFieldType fieldType, final ValueParameter value);
	public abstract <T> Specification<T> queryByBigDouble(final InstrumentDataFieldType fieldType, final ValueParameter value);
	
	
}
