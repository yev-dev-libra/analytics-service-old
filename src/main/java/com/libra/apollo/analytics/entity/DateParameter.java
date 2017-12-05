package com.libra.apollo.analytics.entity;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.entity.enums.OperandDate;
import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "DATE_PARAMETER")
public class DateParameter extends Parameter {

	@Column(name = "operand")
	@Enumerated(EnumType.STRING)
	private OperandDate operand;

	@Column(name = "date_value", nullable = true)
	private Date dateValue;

	public OperandDate getOperand() {
		return operand;
	}

	public void setOperand(OperandDate operand) {
		this.operand = operand;
	}

	@Override
	public Optional<Object> getValue() {
		return Optional.ofNullable(dateValue);
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	@Override
	public int compareTo(ValueParameter o) {
		return this.getDateValue().compareTo(((DateParameter) o).getDateValue());
	}

	@Override
	public <T> Specification<T> getSpecification(ValueParameter parameter) {
		return this.getOperand().query(getFieldType(), this);
	}

}
