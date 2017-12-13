package com.libra.apollo.analytics.entity;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.entity.enums.OperandBigDecimal;
import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
//@DiscriminatorValue(value = "DECIMAL_PARAMETER")
@Table(name="decimal_parameter", schema="analytics")
public class BigDecimalParameter extends QueryParameter {
	
	@Column(name="operand")
	@Enumerated(EnumType.STRING)
	private OperandBigDecimal operand;

	public OperandBigDecimal getOperand() {
		return operand;
	}

	public void setOperand(OperandBigDecimal operand) {
		this.operand = operand;
	}

	@Column(name = "decimal_value", nullable = true)
	private BigDecimal bigDecimalValue;

	@Override
	public Optional<Object> getValue() {
		return Optional.ofNullable(bigDecimalValue);
	}

	public BigDecimal getBigDecimalValue() {
		return bigDecimalValue;
	}

	public void setBigDecimalValue(BigDecimal bigDecimalValue) {
		this.bigDecimalValue = bigDecimalValue;
	}


	@SuppressWarnings("unchecked")
	@Override
	public  Specification<BigDecimal> getSpecification(ValueParameter parameter) {
		return this.getOperand().query(getFieldType(), this);

	}

	@Override
	public int compareTo(ValueParameter o) {
		return this.getBigDecimalValue().compareTo(((BigDecimalParameter) o).getBigDecimalValue());
	}



}
