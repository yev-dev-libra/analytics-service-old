package com.libra.apollo.analytics.entity;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.engine.core.Value;

@Entity
@Table(name="double_parameter", schema="analytics")
public class DoubleParameter extends QueryParameter {

	@Column(name = "operand", nullable = false)
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
	public int compareTo(Value o) {
		return this.getDoubleValue().compareTo(((DoubleParameter) o).getDoubleValue());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Specification<Double> getSpecification() {
		return this.getOperand().query(this);
	}
	
	

}
