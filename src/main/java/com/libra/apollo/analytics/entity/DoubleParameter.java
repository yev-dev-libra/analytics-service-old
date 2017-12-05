package com.libra.apollo.analytics.entity;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.entity.enums.OperandDouble;
import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "DOUBLE_PARAMETER")
public class DoubleParameter extends Parameter {

	@Column(name = "operand", nullable = true)
	@Enumerated(EnumType.STRING)
	private OperandDouble operand;

	@Column(name = "double_value", precision = 10, scale = 2, nullable = true)
	private Double doubleValue;

	@Override
	public Optional<Object> getValue() {
		return Optional.ofNullable(doubleValue);
	}

	public OperandDouble getOperand() {
		return operand;
	}

	public void setOperand(OperandDouble operand) {
		this.operand = operand;
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
		return this.getOperand().query(getFieldType(), this);

	}

}
