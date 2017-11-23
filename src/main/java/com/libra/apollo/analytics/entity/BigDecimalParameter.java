package com.libra.apollo.analytics.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "DECIMAL_PARAMETER")
public class BigDecimalParameter extends Parameter {

	@Column(name="decimal_value", nullable=true)
	private BigDecimal bigDecimalValue;

	@Override
	public Object getValue() {
		return bigDecimalValue;
	}

	public BigDecimal getBigDecimalValue() {
		return bigDecimalValue;
	}

	public void setBigDecimalValue(BigDecimal bigDecimalValue) {
		this.bigDecimalValue = bigDecimalValue;
	}

	@Override
	public int compareTo(ValueParameter o) {
		return this.getBigDecimalValue().compareTo(((BigDecimalParameter) o).getBigDecimalValue());
	}

	@Override
	public <T> Specification<T> getSpecification(ValueParameter parameter) {
		return super.getOperand().queryByBigDecimal(getFieldType(), this);
		
	}

	@Override
	public <T> Specification<T> getSpecification() {
		throw new UnsupportedOperationException();
	}

}
