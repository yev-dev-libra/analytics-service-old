package com.libra.apollo.analytics.entity;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.entity.enums.OperandDate;
import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
//@DiscriminatorValue(value = "DATE_PARAMETER")
@Table(name="date_parameter", schema="analytics")
public class DateParameter extends QueryParameter<Date> {

	@Column(name = "operand")
	@Enumerated(EnumType.STRING)
	private OperandDate operand;

	@Column(name = "date_value", columnDefinition="DATE DEFAULT CURRENT_DATE", nullable = true)
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
	public int compareTo(ValueParameter<Date> o) {
		return this.getDateValue().compareTo(((DateParameter) o).getDateValue());
	}

	@Override
	public Specification<Date> getSpecification(ValueParameter<Date> parameter) {
		return this.getOperand().query(getFieldType(), this);
	}

}
