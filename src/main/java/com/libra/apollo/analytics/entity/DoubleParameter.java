package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "DOUBLE_PARAMETER")
public class DoubleParameter extends Parameter {

	@Column(name = "double_value", precision = 10, scale = 2, nullable = true)
	private Double doubleValue;

	@Override
	public Object getValue() {
		return doubleValue;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	@Override
	public int compareTo(ValueParameter o) {
		return this.getDoubleValue().compareTo(((DoubleParameter) o).getDoubleValue());
	}

	@Override
	public <T> Specification<T> getSpecification(ValueParameter parameter) {
		return super.getOperand().queryByBigDouble(getFieldType(), this);

	}

	@Override
	public <T> Specification<T> getSpecification() {
		throw new UnsupportedOperationException();
	}

}
